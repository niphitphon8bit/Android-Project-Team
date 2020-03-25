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
 * Class: LoginFragment
 * Author: Namchok Singhachai
 */
class LoginFragment : Fragment() {

    private var btn_signin : Button ? = null

    /**
     * Function: onCreateView
     * Author: Namchok Singhachai
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =   inflater.inflate(R.layout.fragment_login, container, false)

        // Button Sign In
        btn_signin = view.findViewById<Button>(R.id.view_btn_signin)

        // Set on button Sign In
        btn_signin!!.setOnClickListener{
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            // Load fragment Home
            val load_fragment = HomeFragment()

            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_home")
            transaction.addToBackStack("fragment_home")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //

        return view
    }
    // ---------------------------------------------------------------------------- //

}
