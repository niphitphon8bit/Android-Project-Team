package com.example.hr.Personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        // ---------------------------------------------------------------------------- //

        // ---------------------------- Set value ------------------------------------ //
        return view
    }
    // ---------------------------------------------------------------------------- //

}
