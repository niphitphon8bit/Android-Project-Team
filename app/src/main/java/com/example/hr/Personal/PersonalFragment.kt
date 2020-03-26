package com.example.hr.Personal

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.example.hr.Address.AddressFragment

import com.example.hr.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_address.*

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

    var Str_key = "" // key obj_hr_work_experience from firebase
    private lateinit var obj_hr_pesonal : hr_personal
    private lateinit var account_username : String

    fun newInstance(username:String): PersonalFragment {
        val fragment = PersonalFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        if (bundle != null) {
            account_username = bundle.getString("username").toString()
        }

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal, container, false)

        var gender = ""

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

        ScrollView_1.visibility = View.VISIBLE

        // Back
        view_btn_back_menu.setOnClickListener {
            val fm: FragmentManager = activity!!.supportFragmentManager
            fm.popBackStack("fragment_personal", FragmentManager.POP_BACK_STACK_INCLUSIVE)

            Toast.makeText(activity!!.baseContext, "เมนูหลัก", Toast.LENGTH_SHORT).show()
        }
        // ---------------------------------------------------------------------------- //

        // ---------------------- Set variable from view --------------------------- //
        val text_work_in  = view.findViewById<EditText>(R.id.text_work_in)
        val text_prefix_th  = view.findViewById<TextView>(R.id.text_prefix)
        val text_prefix_en = view.findViewById<TextView>(R.id.text_prefix_eng)
        val text_f_name_th  = view.findViewById<EditText>(R.id.text_name)
        val text_f_name_en  = view.findViewById<EditText>(R.id.text_name_eng)
        val text_l_name_th  = view.findViewById<EditText>(R.id.text_lastname)
        val text_l_name_en  = view.findViewById<EditText>(R.id.text_lastname_eng)
        val text_nick_name_th  = view.findViewById<EditText>(R.id.text_nickname)
        val text_nick_name_en = view.findViewById<EditText>(R.id.text_nickname_eng)

        val text_gender  = view.findViewById<EditText>(R.id.text_gender)
        val text_province  = view.findViewById<EditText>(R.id.text_province)
        val text_dob = view.findViewById<EditText>(R.id.text_dob)
        val text_blood_type  = view.findViewById<EditText>(R.id.text_blood_type)
        val text_religion  = view.findViewById<EditText>(R.id.text_religion)
        val text_nationality  = view.findViewById<EditText>(R.id.text_nationality)
        val text_race  = view.findViewById<EditText>(R.id.text_race)
        val text_maritual_status  = view.findViewById<EditText>(R.id.text_maritual_status)

        val text_citizen_id  = view.findViewById<EditText>(R.id.text_citizen_id)
        val text_passport_id = view.findViewById<EditText>(R.id.text_passport_id)
        val text_tax_id = view.findViewById<EditText>(R.id.text_tax_id)
        val text_bank_name  = view.findViewById<EditText>(R.id.text_bank_name)
        val text_bank_id  = view.findViewById<EditText>(R.id.text_bank_id)

        val text_email  = view.findViewById<EditText>(R.id.text_email)
        val text_facebook  = view.findViewById<EditText>(R.id.text_facebook)
        val text_twitter  = view.findViewById<EditText>(R.id.text_twitter)
        val text_website  = view.findViewById<EditText>(R.id.text_website)
        val text_motto = view.findViewById<EditText>(R.id.text_motto)
        val text_interest_in  = view.findViewById<EditText>(R.id.text_interest_in)
        // ---------------------------------------------------------------------------- //

        text_prefix_th.setOnClickListener{
            gender = this.chooseGender()
            text_prefix_th.setText(gender)
        }

        // ------------------------------- Firebase---------------------------------- //
        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_personal")

        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (ds in dataSnapshot.children) {

                    val username = ds.child("username").getValue(String::class.java)!!
                    val work_in = ds.child("work_in").getValue(String::class.java)!!
                    val prefix_th = ds.child("prefix_th").getValue(String::class.java)!!
                    val prefix_en = ds.child("prefix_en").getValue(String::class.java)!!
                    val f_name_th = ds.child("f_name_th").getValue(String::class.java)!!
                    val f_name_en = ds.child("f_name_en").getValue(String::class.java)!!
                    val l_name_th = ds.child("l_name_th").getValue(String::class.java)!!
                    val l_name_en = ds.child("l_name_en").getValue(String::class.java)!!
                    val nick_name_th = ds.child("nick_name_th").getValue(String::class.java)!!
                    val nick_name_en = ds.child("nick_name_en").getValue(String::class.java)!!
                    val gender = ds.child("gender").getValue(String::class.java)!!
                    val province = ds.child("province").getValue(String::class.java)!!
                    val dob = ds.child("dob").getValue(String::class.java)!!
                    val blood_type = ds.child("blood_type").getValue(String::class.java)!!
                    val religion = ds.child("religion").getValue(String::class.java)!!
                    val nationality = ds.child("nationality").getValue(String::class.java)!!
                    val race = ds.child("race").getValue(String::class.java)!!
                    val maritual_status= ds.child("maritual_status").getValue(String::class.java)!!
                    val citizen_id = ds.child("citizen_id").getValue(String::class.java)!!
                    val passport_id = ds.child("passport_id").getValue(String::class.java)!!
                    val tax_id = ds.child("tax_id").getValue(String::class.java)!!
                    val bank_name = ds.child("bank_name").getValue(String::class.java)!!
                    val bank_id = ds.child("bank_id").getValue(String::class.java)!!
                    val email = ds.child("email").getValue(String::class.java)!!
                    val facebook = ds.child("facebook").getValue(String::class.java)!!
                    val twitter = ds.child("twitter").getValue(String::class.java)!!
                    val website = ds.child("website").getValue(String::class.java)!!
                    val motto = ds.child("motto").getValue(String::class.java)!!
                    val interest_in = ds.child("interest_in").getValue(String::class.java)!!

                    if (username == account_username) {
                        Str_key = ds.key.toString()
                        obj_hr_pesonal = hr_personal(
                                                                account_username,
                                                                work_in,
                                                                prefix_th,
                                                                prefix_en,
                                                                f_name_th,
                                                                f_name_en,
                                                                l_name_th,
                                                                l_name_en,
                                                                nick_name_th,
                                                                nick_name_en,
                                                                gender,
                                                                province,
                                                                dob,
                                                                blood_type,
                                                                religion,
                                                                nationality,
                                                                race,
                                                                maritual_status,
                                                                citizen_id,
                                                                passport_id,
                                                                tax_id,
                                                                bank_name,
                                                                bank_id,
                                                                email,
                                                                facebook,
                                                                twitter,
                                                                website,
                                                                motto,
                                                                interest_in
                                                            )
                    }

                }

                if(Str_key != "") {
                    text_work_in.setText(obj_hr_pesonal.work_in)
                    text_prefix_th.setText(obj_hr_pesonal.prefix_th)
                    text_prefix_en.setText(obj_hr_pesonal.prefix_en)
                    text_f_name_th.setText(obj_hr_pesonal.f_name_th)
                    text_f_name_en.setText(obj_hr_pesonal.f_name_en)
                    text_l_name_th.setText(obj_hr_pesonal.l_name_th)
                    text_l_name_en.setText(obj_hr_pesonal.l_name_en)
                    text_nick_name_th.setText(obj_hr_pesonal.nick_name_th)
                    text_nick_name_en.setText(obj_hr_pesonal.nick_name_en)

                    text_gender.setText(obj_hr_pesonal.gender)
                    text_province.setText(obj_hr_pesonal.province)
                    text_dob.setText(obj_hr_pesonal.dob)
                    text_blood_type.setText(obj_hr_pesonal.blood_type)
                    text_religion.setText(obj_hr_pesonal.religion)
                    text_nationality.setText(obj_hr_pesonal.nationality)
                    text_race.setText(obj_hr_pesonal.race)
                    text_maritual_status.setText(obj_hr_pesonal.maritual_status)

                    text_citizen_id.setText(obj_hr_pesonal.citizen_id)
                    text_passport_id.setText(obj_hr_pesonal.passport_id)
                    text_tax_id.setText(obj_hr_pesonal.tax_id)
                    text_bank_name.setText(obj_hr_pesonal.bank_name)
                    text_bank_id.setText(obj_hr_pesonal.bank_id)

                    text_email.setText(obj_hr_pesonal.email)
                    text_facebook.setText(obj_hr_pesonal.facebook)
                    text_twitter.setText(obj_hr_pesonal.twitter)
                    text_website.setText(obj_hr_pesonal.website)
                    text_motto.setText(obj_hr_pesonal.motto)
                    text_interest_in.setText(obj_hr_pesonal.interest_in)
                }

                view_btn_save.setOnClickListener {

                    if(Str_key == ""){
                        obj_hr_pesonal = PersonalFragment.hr_personal(
                            account_username
                            ,text_work_in.text.toString()
                            ,text_prefix_th.text.toString()
                            ,text_prefix_en.text.toString()
                            ,text_f_name_th.text.toString()
                            ,text_f_name_en.text.toString()
                            ,text_l_name_th.text.toString()
                            ,text_l_name_en.text.toString()
                            ,text_nick_name_th.text.toString()
                            ,text_nick_name_en.text.toString()

                            ,text_gender.text.toString()
                            ,text_province.text.toString()
                            ,text_dob.text.toString()
                            ,text_blood_type.text.toString()
                            ,text_religion.text.toString()
                            ,text_nationality.text.toString()
                            ,text_race.text.toString()
                            ,text_maritual_status.text.toString()

                            ,text_citizen_id.text.toString()
                            ,text_passport_id.text.toString()
                            ,text_tax_id.text.toString()
                            ,text_bank_name.text.toString()
                            ,text_bank_id.text.toString()

                            ,text_email.text.toString()
                            ,text_facebook.text.toString()
                            ,text_twitter.text.toString()
                            ,text_website.text.toString()
                            ,text_motto.text.toString()
                            ,text_interest_in.text.toString()
                        )
                        mMessagesRef.push().setValue(obj_hr_pesonal)
                        Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }else{
                        mMessagesRef.child(Str_key).child("username").setValue(account_username)
                        mMessagesRef.child(Str_key).child("work_in").setValue(text_work_in.text.toString())
                        mMessagesRef.child(Str_key).child("prefix_th").setValue(text_prefix_th.text.toString())
                        mMessagesRef.child(Str_key).child("prefix_en").setValue(text_prefix_en.text.toString())
                        mMessagesRef.child(Str_key).child("f_name_th").setValue(text_f_name_th.text.toString())
                        mMessagesRef.child(Str_key).child("f_name_en").setValue(text_f_name_en.text.toString())
                        mMessagesRef.child(Str_key).child("l_name_th").setValue(text_l_name_th.text.toString())
                        mMessagesRef.child(Str_key).child("l_name_en").setValue(text_l_name_en.text.toString())
                        mMessagesRef.child(Str_key).child("nick_name_th").setValue(text_nick_name_th.text.toString())
                        mMessagesRef.child(Str_key).child("nick_name_en").setValue(text_nick_name_en.text.toString())
						
                        mMessagesRef.child(Str_key).child("gender").setValue(text_gender.text.toString())
                        mMessagesRef.child(Str_key).child("province").setValue(text_province.text.toString())
                        mMessagesRef.child(Str_key).child("dob").setValue(text_dob.text.toString())
                        mMessagesRef.child(Str_key).child("blood_type").setValue(text_blood_type.text.toString())
                        mMessagesRef.child(Str_key).child("religion").setValue(text_religion.text.toString())
                        mMessagesRef.child(Str_key).child("nationality").setValue(text_nationality.text.toString())
                        mMessagesRef.child(Str_key).child("race").setValue(text_race.text.toString())
                        mMessagesRef.child(Str_key).child("maritual_status").setValue(text_maritual_status.text.toString())
                        mMessagesRef.child(Str_key).child("citizen_id").setValue(text_citizen_id.text.toString())
						
                        mMessagesRef.child(Str_key).child("passport_id").setValue(text_passport_id.text.toString())
                        mMessagesRef.child(Str_key).child("tax_id").setValue(text_tax_id.text.toString())
                        mMessagesRef.child(Str_key).child("bank_name").setValue(text_bank_name.text.toString())
                        mMessagesRef.child(Str_key).child("bank_id").setValue(text_bank_id.text.toString())
                        mMessagesRef.child(Str_key).child("email").setValue(text_email.text.toString())
						
                        mMessagesRef.child(Str_key).child("facebook").setValue(text_facebook.text.toString())
                        mMessagesRef.child(Str_key).child("twitter").setValue(text_twitter.text.toString())
                        mMessagesRef.child(Str_key).child("website").setValue(text_website.text.toString())
                        mMessagesRef.child(Str_key).child("motto").setValue(text_motto.text.toString())
                        mMessagesRef.child(Str_key).child("interest_in").setValue(text_interest_in.text.toString())

                        Toast.makeText(activity!!.baseContext, "แก้ไขสำเร็จ", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }) // mMessagesRef.addValueEventListener

        // ---------------------------------------------------------------------------- //


        return view
    }
    // ---------------------------------------------------------------------------- //

    // Method to show an alert dialog with single choice list items
    private fun chooseGender() : String{
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog

        // Initialize an array of colors
        val array = arrayOf("ไม่ระบุ", "ชาย", "หญิง")

        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(activity!!)

        var gender = ""

        // Set a title for alert dialog
        builder.setTitle("เลือกเพศ")

        // Set the single choice items for alert dialog with initial selection
        builder.setSingleChoiceItems(array,-1) { _, which->
            // Get the dialog selected item
            val txt = array[which]
            gender = txt
            Toast.makeText(activity!!.baseContext, gender, Toast.LENGTH_SHORT).show()
            // Dismiss the dialog
            dialog.dismiss()
        }

        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
        return gender
    }

}
