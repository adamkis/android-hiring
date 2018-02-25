package at.allaboutapps.a3hiring.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club

/**
 * Created by adam on 2018. 02. 23..
 */

class ClubDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_detail)

        val club: Club? = intent.getParcelableExtra(ARG_CLUB)

        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = club?.name
        setupBackButton()

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, PizzaDetailFragment.newInstance(pizza, ingredientsHM, pizzasResponse)).commit()
//        }
    }

    companion object {
        private val ARG_CLUB = "ARG_CLUB"
        fun getStartIntent(context: Context, club: Club): Intent {
            return Intent(context, ClubDetailActivity::class.java)
                .apply {
                    putExtra(ARG_CLUB, club)
                }
        }
    }


    private fun setupBackButton(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}