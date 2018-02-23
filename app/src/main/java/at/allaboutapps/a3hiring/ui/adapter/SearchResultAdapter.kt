package at.allaboutapps.a3hiring.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import at.allaboutapps.a3hiring.App
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_search.view.*
import javax.inject.Inject

/**
 * Created by adam on 2018. 02. 23..
 */

class SearchResultAdapter(private val clubs: Array<Club>?,
                          private val context: Context
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>(){

    @Inject lateinit var glideReqManager: RequestManager

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

        fun bind(club: Club?){
            view.clubName.text = club?.name
        }

    }

}