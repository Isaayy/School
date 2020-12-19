package com.example.shoutbox.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoutbox.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var textView: TextView
    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        textView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        loadLogin()

        val Button: Button = root.findViewById(R.id.buttonLogin)
        Button.setOnClickListener{
            val et_text : EditText = root.findViewById(R.id.et_text)
            val login = et_text.text.toString();
            val preferences = this.getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val editor = preferences?.edit();
            editor?.apply(){
                putString("STRING_KEY",login)
            }?.apply()

        }


        return root
    }


    private fun loadLogin() {
        val sharedPreferences = this.getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val savedLogin = sharedPreferences?.getString("STRING_KEY",null)
        textView.text = "Twoj login: " + savedLogin
    }
}

