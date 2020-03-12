package com.example.hr.Authen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.hr.HomeFragment

import com.example.hr.R

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private var btn_signin : Button ? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =   inflater.inflate(R.layout.fragment_login, container, false)

        btn_signin = view.findViewById<Button>(R.id.view_btn_signin)

        btn_signin!!.setOnClickListener{

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()

            val load_fragment = HomeFragment()
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_home")
            transaction.addToBackStack("fragment_home")
            transaction.commit()

        }

        return view
    }


}
