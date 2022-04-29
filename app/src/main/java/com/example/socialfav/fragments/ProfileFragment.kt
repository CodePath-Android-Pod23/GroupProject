package com.example.socialfav.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.socialfav.R
import com.parse.ParseUser
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<ImageButton>(R.id.button)

        btn.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
        }

        val userPhoto = view.findViewById<ImageView>(R.id.avatar)
        val currUser = ParseUser.getCurrentUser()
        Glide.with(this.requireContext()).load(currUser.getParseFile("profilePicture")?.url).circleCrop().into(userPhoto)
        view.findViewById<TextView>(R.id.tv_username).setText(currUser.getString("FullName"))
        view.findViewById<TextView>(R.id.tv_location).setText(currUser.getString("Location"))
        view.findViewById<TextView>(R.id.tv_caption).setText(currUser.getString("username"))
    }
}