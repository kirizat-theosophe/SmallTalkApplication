package com.example.uke_3_4_oppgave.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.example.uke_3_4_oppgave.*
import com.example.uke_3_4_oppgave.tabbar.MainActivity

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var loginLoader: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        loginButton = view.findViewById(R.id.login_button)
        usernameEditText = view.findViewById(R.id.username)
        passwordEditText = view.findViewById(R.id.password)
        loginLoader = view.findViewById(R.id.login_loader)
        loginLoader.isVisible = false

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtons()
        bindObservers()
    }

    private fun setButtons() {
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            viewModel.logInUser(
                username,
                password
            )
        }
    }
    private fun bindObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { showLoader ->
            loginLoader.isVisible = showLoader
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
           if (success) {
               val intent = Intent(activity, MainActivity::class.java)
               intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
               startActivity(intent)
           } else {
               Toast.makeText(context, "wrong username or password", Toast.LENGTH_LONG).show()
           }
        }
    }
}