package at.allaboutapps.a3hiring.ui.fragment

import android.support.annotation.StringRes
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import at.allaboutapps.a3hiring.R

/**
 * Created by adam on 2018. 02. 23..
 */

abstract class BaseFragment : Fragment(){

    private var loadingView: View? = null
    private var coordinatorLayout: CoordinatorLayout? = null

    protected fun setUpLoadingAndError(loadingView: View, rootView: CoordinatorLayout){
        this.loadingView = loadingView
        coordinatorLayout = rootView
    }

    protected fun showLoading(show: Boolean){
        loadingView?.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showError(@StringRes stringRes: Int){
        coordinatorLayout?.let {
            val snackBar = Snackbar.make(it, getString(stringRes), Snackbar.LENGTH_LONG)
            snackBar.setAction(getString(R.string.dismiss)) { snackBar.dismiss() }
            snackBar.show()
        }
    }

}