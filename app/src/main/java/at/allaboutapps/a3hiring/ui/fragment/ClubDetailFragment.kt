package at.allaboutapps.a3hiring.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club
import kotlinx.android.synthetic.main.fragment_club_detail.*

/**
 * Created by adam on 2018. 02. 25..
 */

class ClubDetailFragment : BaseFragment() {

    private var club: Club? = null

    companion object {
        private val ARG_CLUB = "ARG_CLUB"
        fun newInstance(club: Club?): ClubDetailFragment {
            val fragment = ClubDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_CLUB, club)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { club = it.getParcelable(ARG_CLUB) } ?:
            run { arguments?.let { club = it.getParcelable(ARG_CLUB) } }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_club_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        club?.let {
            clubCountry.text = it.country
            clubData.text = it.value.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(ARG_CLUB, club)
        super.onSaveInstanceState(outState)
    }

}