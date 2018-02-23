package at.allaboutapps.a3hiring.ui.fragment

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import at.allaboutapps.a3hiring.App
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.RestApi
import at.allaboutapps.a3hiring.api.models.Club
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by adam on 2018. 02. 23..
 */

class SearchFragment : BaseFragment() {

    @Inject lateinit var restApi: RestApi
    private var callDisposable: Disposable? = null

    private var clubs: Array<Club>? = null

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

        if( savedInstanceState != null ){
            clubs = savedInstanceState.getParcelableArray(ARG_CLUBS) as Array<Club>?
            clubs?.let { showResults(it) }
            showLoading(false)
        }
        else{
            loadData()
        }

    }

    private fun showResults(clubs: Array<Club>) {
        clubs.forEach {
            Timber.d(it.toString())
        }
    }

    override fun onDestroy() {
        callDisposable?.dispose()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArray(ARG_CLUBS, clubs)
        super.onSaveInstanceState(outState)
    }

    //MARK: Presenter

    private fun loadData(){
        callDisposable = restApi.getClubs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { mSearchView.setProgressIndicator(true) }
//            .doAfterTerminate { mSearchView.setProgressIndicator(false) }
                .subscribe(
                        { response ->
                            clubs = response
                            showResults(response)
                        },
                        { t ->  }
                )
    }


    private fun handleError(t: Throwable){
//        when(t){
//            is UnknownHostException -> {
//                showError(R.string.network_error)
//            }
//            is NullPointerException -> {
//                showError(R.string.could_not_load_data)
//            }
//            else -> {
//                showError(R.string.error)
//            }
//        }
//        logThrowable(t)
    }

}
