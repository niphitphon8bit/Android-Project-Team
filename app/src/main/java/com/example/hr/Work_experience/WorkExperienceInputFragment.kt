package com.example.hr.Work_experience


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.hr.R

/**
 * A simple [Fragment] subclass.
 */
class WorkExperienceInputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var view = inflater.inflate(R.layout.fragment_work_experience_input, container, false)


        


        return view
    }


}
