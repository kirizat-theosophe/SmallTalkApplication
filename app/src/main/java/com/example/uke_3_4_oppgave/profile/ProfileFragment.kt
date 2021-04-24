package com.example.uke_3_4_oppgave.profile

import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.uke_3_4_oppgave.R
import com.example.uke_3_4_oppgave.tabbar.MainActivity
import com.example.uke_3_4_oppgave.UserManager

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    private lateinit var circleTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var firstNameTextView: TextView
    private lateinit var logOutButton: Button

   // private lateinit var notificationButton: Button

    private lateinit var notificationManager: NotificationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        circleTextView = view.findViewById(R.id.circle_image_text_view)
        usernameTextView = view.findViewById(R.id.user_name_text_view)
        firstNameTextView = view.findViewById(R.id.first_name_text_view)
        logOutButton = view.findViewById(R.id.log_out_button)

        //notificationButton = view.findViewById(R.id.notification_button)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationManager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val user = UserManager.loggedInUser

        circleTextView.text = user.firstName.firstOrNull()?.toString() ?: "X"
        usernameTextView.text = user.userName
        firstNameTextView.text = user.firstName

        logOutButton.setOnClickListener {
            (activity as? MainActivity)?.logOutUser()
        }
        /*notificationButton.setOnClickListener{

        }*/
    }
}