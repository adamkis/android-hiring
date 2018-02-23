package at.allaboutapps.a3hiring

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import at.allaboutapps.a3hiring.api.RestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by adam on 2018. 02. 23..
 */

class MainActivity : AppCompatActivity() {

    @Inject lateinit var restApi: RestApi
    private var callDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.netComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)
        loadData()
    }

    private fun loadData(){
        callDisposable = restApi.getClubs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { mSearchView.setProgressIndicator(true) }
//            .doAfterTerminate { mSearchView.setProgressIndicator(false) }
            .subscribe(
                { response ->
                    response.forEach {
                        Timber.d(it.toString())
                    }
                },
                { t ->  }
            )
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //TODO: handle sort item click

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        callDisposable?.dispose()
        super.onDestroy()
    }

}
