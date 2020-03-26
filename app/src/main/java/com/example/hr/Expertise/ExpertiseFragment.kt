package com.example.hr.Expertise


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.Membership_card.MembershipCardAdapter
import com.example.hr.Membership_card.MembershipCardFragment
import com.example.hr.Membership_card.MembershipCardInputFragment
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
class ExpertiseFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    var account_username : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_expertise, container, false)

        val mRootRef = FirebaseDatabase.getInstance().reference

        val mMessagesRef = mRootRef.child("hr_expertise")

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
                    val title_name_th = ds.child("title_name_th").getValue(String::class.java)!!
                    val title_name_en = ds.child("title_name_en").getValue(String::class.java)!!
                    val text_th = ds.child("text_th").getValue(String::class.java)!!
                    val text_en = ds.child("text_en").getValue(String::class.java)!!

                    if (username == account_username) {
                        jObject.put("key", ds.key)
                        jObject.put("username", username)
                        jObject.put("title_name_th", title_name_th)
                        jObject.put("title_name_en", title_name_en)
                        jObject.put("text_th", text_th)
                        jObject.put("text_en", text_en)

                        list.put(jObject)
                    }

                }

                val adapter = ExpertiseAdapter(activity!!, list, account_username)

                recyclerView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }) // mMessagesRef.addValueEventListener


        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()

        var btn_add = view.findViewById<FloatingActionButton>(R.id.floatingActionButton) as FloatingActionButton

        btn_add!!.setOnClickListener{
            val load_fragment = ExpertiseInputFragment().newInstance("",account_username,"","","","")
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"_ExpertiseInputFragment")
            transaction.addToBackStack("_ExpertiseInputFragment")
            transaction.commit()
        }

        return view
    }

    fun newInstance(username:String): ExpertiseFragment {
        val fragment = ExpertiseFragment()
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
