package at.allaboutapps.a3hiring.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import at.allaboutapps.a3hiring.App
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club
import at.allaboutapps.a3hiring.helper.toIntOrNull
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_search.view.*
import javax.inject.Inject

/**
 * Created by adam on 2018. 02. 23..
 */

class SearchResultAdapter(var clubs: ArrayList<Club>?,
                          private val context: Context
    ) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>(){

    @Inject lateinit var glideReqManager: RequestManager
    private val clickSubject = PublishSubject.create<Pair<Club, View>>()
    val clickEvent: Observable<Pair<Club, View>> = clickSubject

    init {
        App.glideComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_search, parent, false)
        return SearchResultViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder?, position: Int) {
        holder?.bind(clubs?.get(position))
    }

    override fun getItemCount(): Int = clubs?.size ?: 0

    inner class SearchResultViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view){

        init {
            itemView.setOnClickListener {
                clubs?.let { clickSubject.onNext(Pair<Club, View>(it[layoutPosition], view)) }
            }
        }

        fun bind(club: Club?){
            view.clubName.text = club?.name
            view.clubCountry.text = club?.country
            (club?.value?.toIntOrNull())?.let {
                view.clubValue.text = context.resources.getQuantityString(R.plurals.millions, it)
            } ?: run {
                view.clubValue.text = context.getString(R.string.millions, club?.value)
            }
            glideReqManager.load(club?.image)
                    .apply(RequestOptions().placeholder(R.drawable.club_placeholder))
                    .into(view.clubImage)

        }

    }

}