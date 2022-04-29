package com.example.socialfav.fragments

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.socialfav.MainActivity
import com.example.socialfav.NearbyAdapter
import com.example.socialfav.R
import com.example.socialfav.UserDetails
import com.parse.ParseUser


class ModalBottomSheet : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_modal_bottom_sheet_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.editProfile).setOnClickListener {
            val intent = Intent(this.requireContext(), UserDetails::class.java)
            intent.putExtra("Activity", "EditProfile")
            Toast.makeText(this.requireContext(), "Starting UserDetails", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            //finish()
        }
        view.findViewById<LinearLayout>(R.id.btn_editGenre).setOnClickListener {
            Toast.makeText(this.requireContext(), "Edit Genre Coming Soon!", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<LinearLayout>(R.id.btn_editFriends).setOnClickListener {
            Toast.makeText(this.requireContext(), "Friend List Coming Soon!", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<LinearLayout>(R.id.btn_Chat).setOnClickListener {
            Toast.makeText(this.requireContext(), "Chat Feature Coming Soon!", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<LinearLayout>(R.id.btn_editFavs).setOnClickListener {
            Toast.makeText(this.requireContext(), "Favs List Coming Soon!", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btn_logOut).setOnClickListener {
            ParseUser.logOut();
            Toast.makeText(this.requireContext(), "Logging out!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}