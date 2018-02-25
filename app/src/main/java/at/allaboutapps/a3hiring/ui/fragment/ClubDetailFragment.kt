package at.allaboutapps.a3hiring.ui.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import at.allaboutapps.a3hiring.R
import at.allaboutapps.a3hiring.api.models.Club
import at.allaboutapps.a3hiring.helper.getSpannedText
import kotlinx.android.synthetic.main.fragment_club_detail.*
import kotlinx.android.synthetic.main.item_search.view.*

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
            (club?.value as? Int)?.let {
                clubData.text = getSpannedText(resources.getQuantityString(R.plurals.club_detail_data, it, club?.name, club?.country, it))
            } ?: run {
                clubData.text = getSpannedText(getString(R.string.club_detail_data, club?.name, club?.country, club?.value))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(ARG_CLUB, club)
        super.onSaveInstanceState(outState)
    }

}