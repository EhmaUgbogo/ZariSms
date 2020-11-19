package com.ehmaugbogo.zarisms.views.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.navigateTo
import com.ehmaugbogo.zarisms.util.setMainTitle
import com.ehmaugbogo.zarisms.util.showToast
import com.ehmaugbogo.zarisms.views.ui.MailFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().setMainTitle(getString(R.string.dash_board))

        main_recycler.adapter = MainAdapter {
            if (it.destination == 0) {
                showToast("Feature not currently available");return@MainAdapter
            }

            requireActivity().navigateTo(it.destination)
            // findNavController().navigate(it.destination)
        }

        Handler().postDelayed({
            progressBar.isVisible = false
        }, 600)

    }


}
