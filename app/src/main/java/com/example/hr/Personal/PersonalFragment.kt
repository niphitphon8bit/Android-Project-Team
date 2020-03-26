package com.example.hr.Personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager

import com.example.hr.R

/**
 * Function: onCreateView
 * Author: Namchok Singhachai
 */
class PersonalFragment : Fragment() {

    /**
     * Data class: hr_personal
     * Author: Namchok Singhachai
     */
    data class hr_personal (
        var username : String
        ,var work_in : String
        ,var prefix_th : String
        ,var prefix_en : String
        ,var f_name_th : String
        ,var f_name_en : String
        ,var l_name_th : String
        ,var l_name_en : String
        ,var nick_name_th : String
        ,var nick_name_en :String
        ,var gender : String
        ,var province : String
        ,var dob : String
        ,var blood_type : String
        ,var religion : String
        ,var nationality : String
        ,var race : String
        ,var maritual_status : String
        ,var citizen_id : String
        ,var passport_id : String
        ,var tax_id : String
        ,var bank_name : String
        ,var  bank_id : String
        ,var email : String
        ,var facebook : String
        ,var  twitter : String
        ,var website : String
        ,var motto : String
        ,var interest_in :String
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal, container, false)

        // --------------------- Set variable form view ------------------------------- //
        val ScrollView_1 = view.findViewById<ScrollView>(R.id.scroll1)
        val ScrollView_2 = view.findViewById<ScrollView>(R.id.scroll2)
        val linearLayout_1 = view.findViewById<LinearLayout>(R.id.linearLayout_1)
        val linearLayout_2 = view.findViewById<LinearLayout>(R.id.linearLayout_2)
        val linearLayout_3 = view.findViewById<LinearLayout>(R.id.linearLayout_3)
        val linearLayout_4 = view.findViewById<LinearLayout>(R.id.linearLayout_4)

        // ---------------------------------------------------------------------------- //
        val view_btn_next_1 = view.findViewById<Button>(R.id.view_btn_next_1)
        val view_btn_next_2 = view.findViewById<Button>(R.id.view_btn_next_2)
        val view_btn_next_3 = view.findViewById<Button>(R.id.view_btn_next_3)
        val view_btn_back_menu = view.findViewById<ImageButton>(R.id.view_btn_back) // back to menu
        val view_btn_back_2 = view.findViewById<Button>(R.id.view_btn_back_2)
        val view_btn_back_3 = view.findViewById<Button>(R.id.view_btn_back_3)
        val view_btn_back_4 = view.findViewById<Button>(R.id.view_btn_back_4)
        val view_btn_save = view.findViewById<Button>(R.id.view_btn_save)
        // ---------------------------------------------------------------------------- //

        // ------------------------------- Switch ------------------------------------ //

        // Next
        view_btn_next_1.setOnClickListener {
            ScrollView_1.visibility = View.GONE
            ScrollView_2.visibility = View.VISIBLE
        }
        view_btn_next_2.setOnClickListener {
            ScrollView_2.visibility = View.GONE
            linearLayout_3.visibility = View.VISIBLE
        }
        view_btn_next_3.setOnClickListener {
            linearLayout_3.visibility = View.GONE
            linearLayout_4.visibility = View.VISIBLE
        }

        // Back
        view_btn_back_2.setOnClickListener {
            ScrollView_1.visibility = View.VISIBLE
            ScrollView_2.visibility = View.GONE
        }
        view_btn_back_3.setOnClickListener {
            ScrollView_2.visibility = View.VISIBLE
            linearLayout_3.visibility = View.GONE
        }
        view_btn_back_4.setOnClickListener {
            linearLayout_3.visibility = View.VISIBLE
            linearLayout_4.visibility = View.GONE
        }

        // Back
        view_btn_back_menu.setOnClickListener {
            val fm: FragmentManager = activity!!.supportFragmentManager
            fm.popBackStack("fragment_personal", FragmentManager.POP_BACK_STACK_INCLUSIVE)

            Toast.makeText(activity!!.baseContext, "เมนูหลัก", Toast.LENGTH_SHORT).show()
        }
        // ---------------------------------------------------------------------------- //


        return view
    }
    // ---------------------------------------------------------------------------- //

}
