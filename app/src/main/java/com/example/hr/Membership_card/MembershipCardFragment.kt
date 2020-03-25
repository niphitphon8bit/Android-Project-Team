package com.example.hr.Membership_card


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.Education.EducationInputFragment

import com.example.hr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass.
 */
class MembershipCardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    var account_username : String = "60160157"
//    private lateinit var obj_hr_membership_card : hr_membership_card
//
//    var Str_key = "" // key obj_hr_work_experience from firebase
//
//    data class hr_membership_card (
//        var username: String? = "",
//        var membership_name: String? = "",
//        var membership_id: String? = "",
//        var issue_date: String? = "",
//        var expiry_date: String? = ""
//    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_membership_card, container, false)

        val mRootRef = FirebaseDatabase.getInstance().reference

        val mMessagesRef = mRootRef.child("hr_membership_card")

//        var obj_hr_membership_card = hr_membership_card (
//            "60160157",
//            "ทดสอบ",
//            "1",
//            "25-3-2563",
//            "30-3-2563"
//        )
//        mMessagesRef.push().setValue(obj_hr_membership_card)

        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val list = JSONArray()
                recyclerView = view.findViewById(R.id.recyLayout)

                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(activity!!.baseContext)
                recyclerView.layoutManager = layoutManager

                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val username = ds.child("username").getValue(String::class.java)!!
                    val membership_name = ds.child("membership_name").getValue(String::class.java)!!
                    val membership_id = ds.child("membership_id").getValue(String::class.java)!!
                    val issue_date = ds.child("issue_date").getValue(String::class.java)!!
                    val expiry_date = ds.child("expiry_date").getValue(String::class.java)!!

                    if (username == account_username) {
                        jObject.put("key", ds.key)
                        jObject.put("username", username)
                        jObject.put("membership_name", membership_name)
                        jObject.put("membership_id", membership_id)
                        jObject.put("issue_date", issue_date)
                        jObject.put("expiry_date", expiry_date)

                        list.put(jObject)
                    }

                }

                val adapter = MembershipCardAdapter(activity!!, list, account_username)

                recyclerView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }) // mMessagesRef.addValueEventListener


        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()

        var btn_add = view.findViewById<FloatingActionButton>(R.id.floatingActionButton) as FloatingActionButton
        val btn_back = view.findViewById(R.id.view_btn_back) as ImageButton

        btn_add!!.setOnClickListener{
            val load_fragment = MembershipCardInputFragment().newInstance("", account_username, "", "", "", "")
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"_MembershipCardInputFragment")
            transaction.addToBackStack("_MembershipCardInputFragment")
            transaction.commit()
        }

        btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_membership_card", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

            return view
        }


}
