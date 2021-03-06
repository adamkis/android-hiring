package at.allaboutapps.a3hiring.ui.fragment

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import at.allaboutapps.a3hiring.App
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.RestApi
import at.allaboutapps.a3hiring.api.models.Club
import at.allaboutapps.a3hiring.api.models.Club.Companion.COMPARE_BY_NAME_ASC
import at.allaboutapps.a3hiring.helper.FilePersistenceHelper.HEADER_IMAGE_KEY
import at.allaboutapps.a3hiring.helper.TransitionHelper
import at.allaboutapps.a3hiring.ui.activity.ClubDetailActivity
import at.allaboutapps.a3hiring.ui.adapter.SearchResultAdapter
import com.example.run.helper.logThrowable
import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.net.UnknownHostException
import java.util.*
import javax.inject.Inject


/**
 * Created by adam on 2018. 02. 23..
 */

class SearchFragment : BaseFragment() {

    @Inject lateinit var restApi: RestApi
    private var callDisposable: Disposable? = null
    private var clickDisposable: Disposable? = null
    private var paperDisposable: Disposable? = null

    private var clubs: ArrayList<Club>? = null

    companion object {
        private val ARG_CLUBS = "ARG_CLUBS"
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    // MARK: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        App.netComponent.inject(this)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLoadingAndError(view.findViewById(R.id.loading), view as CoordinatorLayout)
        setupRecyclerView()
        if( savedInstanceState != null ){
            clubs = savedInstanceState.getParcelableArrayList(ARG_CLUBS)
            clubs?.let { showResults(it) }
            showLoading(false)
        }
        else{
            loadData()
        }
    }

    private fun setupRecyclerView(){
        val itemDecorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(activity!!, R.drawable.club_list_divider)!!)
        searchResultRV.addItemDecoration(itemDecorator)
        searchResultRV.layoutManager = LinearLayoutManager(activity as Context, LinearLayout.VERTICAL, false)
    }

    private fun showResults(clubs: ArrayList<Club>, comparator: Comparator<Club>? = null) {
        comparator?.let { Collections.sort(clubs, it) }
        searchResultRV.adapter = SearchResultAdapter(clubs, activity as Context)
        clickDisposable = (searchResultRV.adapter as SearchResultAdapter).clickEvent
                .subscribe({
                    startDetailActivityWithTransition(activity as Activity,
                            it.second.findViewById(R.id.clubImage),
                            it.first)
                })
    }

    fun sort(comparator: Comparator<Club>){
        Collections.sort((searchResultRV.adapter as SearchResultAdapter).clubs, comparator)
        searchResultRV.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        callDisposable?.dispose()
        clickDisposable?.dispose()
        paperDisposable?.dispose()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(ARG_CLUBS, clubs)
        super.onSaveInstanceState(outState)
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun startDetailActivityWithTransition(activity: Activity, firstViewToAnimate: View, club: Club) {
        val animationBundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                *TransitionHelper.createSafeTransitionParticipants(activity,
                        false,
                        android.support.v4.util.Pair(firstViewToAnimate, activity.getString(R.string.transition_club_image))
                ))
                .toBundle()
        paperDisposable =  Observable.just(
                Paper.book().write(HEADER_IMAGE_KEY, ((firstViewToAnimate as ImageView).drawable as BitmapDrawable).bitmap))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { startActivity(ClubDetailActivity.getStartIntent(activity, club), animationBundle) },
                    { logThrowable(it) }
                )
    }


    //MARK: Presenter

    private fun loadData(){
        callDisposable = restApi.getClubs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doAfterTerminate { showLoading(false) }
            .subscribe(
                { response ->
                    clubs = response
                    showResults(response, COMPARE_BY_NAME_ASC)
                },
                { t -> handleError(t) }
            )
    }


    private fun handleError(t: Throwable){
        when(t){
            is UnknownHostException -> {
                showError(R.string.network_error)
            }
            is NullPointerException -> {
                showError(R.string.could_not_load_data)
            }
            else -> {
                showError(R.string.error)
            }
        }
        logThrowable(t)
    }

}
