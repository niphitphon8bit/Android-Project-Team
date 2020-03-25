package com.example.hr.Work_experience


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.Membership_card.MembershipCardInputFragment

import com.example.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class WorkExperienceFragment : Fragment() {

        private lateinit var recyclerView: RecyclerView

        private lateinit var obj_hr_work_experience : hr_work_experience
        var account_username : String = "60160157"
        var Str_key = "" // key obj_hr_work_experience from firebase

        data class hr_work_experience (
            var username: String? = "",
            var position_work_name : String? = "",
            var position_manage_name : String? = "",
            var position_level : String? = "",
            var manage_name : String? = "",
            var place  : String? = "",
            var start_date  : String? = "",
            var end_date  : String? = "",
            var text_th  : String? = "",
            var text_en  : String? = ""
        )




//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_work_experience, container, false)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_work_experience, container, false)
        // Inflate the layout for this fragment

        val mRootRef = FirebaseDatabase.getInstance().reference
        val mMessagesRef = mRootRef.child("data")

        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val list = JSONArray()
                recyclerView = view.findViewById(R.id.recyLayout)


                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val username = ds.child("username").getValue(String::class.java)!!
                    val position_work_name =
                        ds.child("position_work_name").getValue(String::class.java)!!
                    val position_manage_name =
                        ds.child("position_manage_name").getValue(String::class.java)!!
                    val position_level = ds.child("position_level").getValue(String::class.java)!!
                    val manage_name = ds.child("manage_name").getValue(String::class.java)!!
                    val place = ds.child("place").getValue(String::class.java)!!
                    val start_date = ds.child("start_date").getValue(String::class.java)!!
                    val end_date = ds.child("end_date").getValue(String::class.java)!!
                    val text_th = ds.child("start_date").getValue(String::class.java)!!
                    val text_en = ds.child("end_date").getValue(String::class.java)!!


                    if (username == account_username) {
                        jObject.put("key", ds.key)
                        jObject.put("username", username)
                        jObject.put("text", position_work_name)
                        jObject.put("username", position_manage_name)
                        jObject.put("text", position_level)
                        jObject.put("username", manage_name)
                        jObject.put("text", place)
                        jObject.put("username", start_date)
                        jObject.put("text", end_date)
                        jObject.put("username", text_th)
                        jObject.put("text", text_en)

                        list.put(jObject)

                    }
                }

                val adapter = WorkExperienceAdapter(activity!!, list, account_username)

                recyclerView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()

            var btn_add = view.findViewById<FloatingActionButton>(R.id.floatingActionButton) as FloatingActionButton

            btn_add!!.setOnClickListener{
                val load_fragment = WorkExperienceInputFragment()
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                transaction.replace(R.id.contentContainer, load_fragment,"WorkExperienceInputFragment")
                transaction.addToBackStack("WorkExperienceInputFragment")
                transaction.commit()
            }

        return view
    }



}
