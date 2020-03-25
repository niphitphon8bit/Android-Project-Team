package com.example.hr.Membership_card


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.hr.R

/**
 * A simple [Fragment] subclass.
 */
class MembershipCardInputFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var obj_hr_work_experience : hr_work_experience

    var Str_key = "" // key obj_hr_work_experience from firebase

    data class hr_work_experience(
        var username: String? = "",
        var professional_name: String? = "",
        var member_number: String? = "",
        var issue_date: String? = "",
        var expiration_date: String? = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_membership_card_input, container, false)

        return view
    }


    fun newInstance(key: String, username:String, professional_name: String, member_number:String, issue_date:String, expiration_date:String): MembershipCardInputFragment {
        val fragment = MembershipCardInputFragment()
        val bundle = Bundle()
        bundle.putString("key", key)
        bundle.putString("username", username)
        bundle.putString("professional_name", professional_name)
        bundle.putString("member_number", member_number)
        bundle.putString("issue_date", issue_date)
        bundle.putString("expiration_date", expiration_date)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            obj_hr_work_experience = hr_work_experience(
                bundle.getString("username").toString(),
                bundle.getString("professional_name").toString(),
                bundle.getString("member_number").toString(),
                bundle.getString("issue_date").toString(),
                bundle.getString("expiration_date").toString()
            )
            Str_key = bundle.getString("key").toString()
        }
    }


}
