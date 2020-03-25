package com.example.hr.Work_experience


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.hr.R
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class WorkExperienceFragment : Fragment() {

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_work_experience, container, false)
//    }

    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_work_experience, container, false)
        // Inflate the layout for this fragment

//        val jsonString : String = loadJsonFromAsset("team4.json", activity!!.baseContext).toString()
//        val json = JSONObject(jsonString)
//        val jsonArray = json.getJSONArray("team4")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyLayout)

        //ตั้งค่า Layout
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)
        recyclerView.layoutManager = layoutManager

        //ตั้งค่า Adapter
//        val adapter = MyRecyclerAdapter(activity!!,jsonArray)
//        recyclerView.adapter = adapter

        return view
    }


}
