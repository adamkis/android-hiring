package at.allaboutapps.a3hiring.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club
import at.allaboutapps.a3hiring.ui.fragment.ClubDetailFragment
import at.allaboutapps.a3hiring.ui.fragment.SearchFragment

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

        var clubDetailFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? ClubDetailFragment
        if (clubDetailFragment == null) {
            clubDetailFragment = ClubDetailFragment.newInstance(club)
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, clubDetailFragment).commit()
        }
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