package com.ehmaugbogo.zarisms.views.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.util.load
import com.ehmaugbogo.zarisms.util.navigateTo
import com.ehmaugbogo.zarisms.util.showToast
import com.ehmaugbogo.zarisms.util.showView
import com.ehmaugbogo.zarisms.views.ui.main.sign_in.UserStore
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_recycler.adapter = MainAdapter {
            if (it.destination == 0) {
                showToast("Feature not currently available");return@MainAdapter
            }

            navigateTo(it.destination)
            // findNavController().navigate(it.destination)
        }

        setProfileImage()
        Handler().postDelayed({
            progressBar.isVisible = false
        }, 600)

    }

    private fun setProfileImage() {
        val photoUrl = UserStore.storeUser.photoUrl
        val profileImg = requireActivity().findViewById<ImageView>(R.id.profile_img)

        if (photoUrl.isEmpty()) showView(false, profileImg)
        else showView(true, profileImg).also { profileImg.load(requireContext(), photoUrl) }
    }


}
