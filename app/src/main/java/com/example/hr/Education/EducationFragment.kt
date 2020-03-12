package com.example.hr.Education


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.hr.Personal.PersonalFragment

import com.example.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class EducationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_education, container, false)
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()
        var btn_add = view.findViewById<FloatingActionButton>(R.id.floatingActionButton) as FloatingActionButton

        btn_add!!.setOnClickListener{
            val load_fragment = EducationInputFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_education_input")
            transaction.addToBackStack("fragment_education_input")
            transaction.commit()
        }
        return view
    }


}
