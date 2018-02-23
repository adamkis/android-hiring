package at.allaboutapps.a3hiring.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.ui.fragment.SearchFragment


/**
 * Created by adam on 2018. 02. 23..
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)
        var groupsFragment: SearchFragment? = supportFragmentManager
                .findFragmentById(R.id.fragmentContainer) as? SearchFragment
        if (groupsFragment == null) {
            groupsFragment = SearchFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, groupsFragment).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId ){
            R.id.menuSortByName -> Toast.makeText(this, "Name", Toast.LENGTH_SHORT).show()
            R.id.menuSortByValue -> Toast.makeText(this, "Value", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}
