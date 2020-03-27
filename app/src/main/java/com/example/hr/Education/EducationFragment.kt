package com.example.hr.Education


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.Membership_card.MembershipCardAdapter
import com.example.hr.Personal.PersonalFragment

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
class EducationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    var account_username : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_education, container, false)
        val mRootRef = FirebaseDatabase.getInstance().reference

        val mMessagesRef = mRootRef.child("hr_education")

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
                    val degree = ds.child("degree").getValue(String::class.java)!!
                    val name= ds.child("name").getValue(String::class.java)!!
                    val place = ds.child("place").getValue(String::class.java)!!
                    val country= ds.child("country").getValue(String::class.java)!!
                    val major = ds.child("major").getValue(String::class.java)!!
                    val major_type = ds.child("major_type").getValue(String::class.java)!!
                    val hornors = ds.child("hornors").getValue(String::class.java)!!
                    val start_date = ds.child("start_date").getValue(String::class.java)!!
                    val end_date = ds.child("end_date").getValue(String::class.java)!!

                    if (username == account_username) {
                        jObject.put("key", ds.key)
                        jObject.put("username", username)
                        jObject.put("degree", degree)
                        jObject.put("name", name)
                        jObject.put("place", place)
                        jObject.put("country", country)
                        jObject.put("major", major)
                        jObject.put("major_type", major_type)
                        jObject.put("hornors", hornors)
                        jObject.put("start_date", start_date)
                        jObject.put("exp_date", end_date)

                        list.put(jObject)
                    }

                }

                val adapter = EducationAdapter(activity!!, list, account_username)

                recyclerView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }) // mMessagesRef.addValueEventListener

        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()
        var btn_add = view.findViewById<FloatingActionButton>(R.id.floatingActionButton) as FloatingActionButton
        val btn_back = view.findViewById(R.id.education_btn_back) as ImageButton

        btn_add!!.setOnClickListener{
            val load_fragment = EducationInputFragment().newInstance("",account_username,"","","","","","","","","")
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_education_input")
            transaction.addToBackStack("fragment_education_input")
            transaction.commit()
        }


        btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_education", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        return view
    }

    fun newInstance(username:String): EducationFragment {
        val fragment = EducationFragment()
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
