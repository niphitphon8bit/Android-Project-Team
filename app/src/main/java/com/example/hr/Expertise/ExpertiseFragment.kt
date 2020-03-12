package com.example.hr.Expertise


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.hr.Personal.PersonalFragment

import com.example.hr.R

/**
 * A simple [Fragment] subclass.
 */
class ExpertiseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_expertise, container, false)

        return view
    }


}
