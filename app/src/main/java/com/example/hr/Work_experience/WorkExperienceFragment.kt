package com.example.hr.Work_experience


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.FragmentManager
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
            var text_th  : String? = ""
        )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_work_experience, container, false)
        // Inflate the layout for this fragment

        val mRootRef = FirebaseDatabase.getInstance().reference
        val mMessagesRef = mRootRef.child("hr_work_experience")

        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val list = JSONArray()
                recyclerView = view.findViewById(R.id.recyLayout)

                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val username = ds.child("username").getValue(String::class.java)!!
                    val position_work_name = ds.child("position_work_name").getValue(String::class.java)!!
                    val position_manage_name = ds.child("position_manage_name").getValue(String::class.java)!!
                    val position_level = ds.child("position_level").getValue(String::class.java)!!
                    val manage_name = ds.child("manage_name").getValue(String::class.java)!!
                    val place = ds.child("place").getValue(String::class.java)!!
                    val start_date = ds.child("start_date").getValue(String::class.java)!!
                    val end_date = ds.child("end_date").getValue(String::class.java)!!
                    val text_th = ds.child("text_th ").getValue(String::class.java)!!


                    if (username == account_username) {
                        jObject.put("key", ds.key)
                        jObject.put("username", username)
                        jObject.put("position_work_name", position_work_name)
                        jObject.put("position_manage_name", position_manage_name)
                        jObject.put("position_level", position_level)
                        jObject.put("manage_name", manage_name)
                        jObject.put("place", place)
                        jObject.put("start_date", start_date)
                        jObject.put("end_date", end_date)
                        jObject.put("text_th", text_th)

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
            val btn_back = view.findViewById(R.id.view_btn_back) as ImageButton

            btn_add!!.setOnClickListener{
                val load_fragment = WorkExperienceInputFragment().newInstance(account_username,"","","","","","","","")
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                transaction.replace(R.id.contentContainer, load_fragment,"WorkExperienceInputFragment")
                transaction.addToBackStack("WorkExperienceInputFragment")
                transaction.commit()
            }

            btn_back.setOnClickListener{
                val fm: FragmentManager = activity!!.getSupportFragmentManager()
                fm.popBackStack("fragment_work_experience", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

        return view
    }


    fun newInstance(username:String): WorkExperienceFragment {
        val fragment = WorkExperienceFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            account_username = bundle.getString("username").toString()
        }
    }




}
