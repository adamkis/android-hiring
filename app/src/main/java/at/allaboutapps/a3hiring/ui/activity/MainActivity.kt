package at.allaboutapps.a3hiring.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club.Companion.COMPARE_BY_NAME_ASC
import at.allaboutapps.a3hiring.api.models.Club.Companion.COMPARE_BY_VALUE_DESC
import at.allaboutapps.a3hiring.ui.fragment.SearchFragment


/**
 * Created by adam on 2018. 02. 23..
 */

class MainActivity : AppCompatActivity() {

    var searchFragment: SearchFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)
        searchFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? SearchFragment
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, searchFragment).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId ){
            R.id.menuSortByName -> searchFragment?.sort(COMPARE_BY_NAME_ASC)
            R.id.menuSortByValue -> searchFragment?.sort(COMPARE_BY_VALUE_DESC)
        }
        return super.onOptionsItemSelected(item)
    }

}
