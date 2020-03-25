package com.example.hr.Address


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.Membership_card.MembershipCardAdapter
import com.example.hr.Membership_card.MembershipCardInputFragment

import com.example.hr.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class AddressFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var obj_hr_address : hr_address
    var account_username : String = "60160157"

    var Str_key = "" // key obj_hr_work_experience from firebase

    data class hr_address (
        var username: String? = "",
        var house_regis_address : String? = "",
        var house_regis_dist : String? = "",
        var house_regis_amph : String? = "",
        var house_regis_province : String? = "",
        var house_regis_zipcode  : String? = "",
        var house_cur_address  : String? = "",
        var house_cur_dist  : String? = "",
        var house_cur_amph  : String? = "",
        var house_cur_province  : String? = "",
        var house_cur_zipcode  : String? = "",
        var house_phone_number  : String? = "",
        var mobile_phone_number  : String? = "",
        var work_phone_number  : String? = "",
        var work_in_phone_number  : String? = ""
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_address, container, false)

        val view_title = view.findViewById<TextView>(R.id.view_title)

        val linearLayout_1 = view.findViewById<LinearLayout>(R.id.linearLayout_1)
        val linearLayout_2 = view.findViewById<LinearLayout>(R.id.linearLayout_2)
        val linearLayout_3 = view.findViewById<LinearLayout>(R.id.linearLayout_3)

        val view_btn_next_1 = view.findViewById<Button>(R.id.view_btn_next_1)
        val view_btn_next_2 = view.findViewById<Button>(R.id.view_btn_next_2)

        val view_btn_back = view.findViewById<ImageButton>(R.id.view_btn_back)
        val view_btn_back_2 = view.findViewById<Button>(R.id.view_btn_back_2)
        val view_btn_back_3 = view.findViewById<Button>(R.id.view_btn_back_3)

        val view_house_regis_address = view.findViewById<TextView>(R.id.view_house_regis_address)
        val view_house_regis_dist = view.findViewById<TextView>(R.id.view_house_regis_dist)
        val view_house_regis_amph = view.findViewById<TextView>(R.id.view_house_regis_amph)
        val view_house_regis_province = view.findViewById<TextView>(R.id.view_house_regis_province)
        val view_house_regis_zipcode = view.findViewById<TextView>(R.id.view_house_regis_zipcode)

        val view_house_cur_address = view.findViewById<TextView>(R.id.view_house_cur_address)
        val view_house_cur_dist = view.findViewById<TextView>(R.id.view_house_cur_dist)
        val view_house_cur_amph = view.findViewById<TextView>(R.id.view_house_cur_amph)
        val view_house_cur_province = view.findViewById<TextView>(R.id.view_house_cur_province)
        val view_house_cur_zipcode = view.findViewById<TextView>(R.id.view_house_cur_zipcode)
        val view_house_phone_number= view.findViewById<TextView>(R.id.view_house_phone_number)

        val view_mobile_phone_number= view.findViewById<TextView>(R.id.view_mobile_phone_number)
        val view_work_phone_number= view.findViewById<TextView>(R.id.view_work_phone_number)
        val view_work_in_phone_number = view.findViewById<TextView>(R.id.view_work_in_phone_number)

        val btn_back = view.findViewById(R.id.view_btn_back) as ImageButton
        val btn_save = view.findViewById(R.id.view_btn_save) as Button

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        view_btn_next_1.setOnClickListener {
            linearLayout_1.setVisibility(View.GONE)
            linearLayout_2.setVisibility(View.VISIBLE)
            view_title.setText("บักทึนที่อยู่ปัจจุบัน")
        }
        view_btn_next_2.setOnClickListener {
            linearLayout_2.setVisibility(View.GONE)
            linearLayout_3.setVisibility(View.VISIBLE)
            view_title.setText("บักทึกเบอร์โทรศัพท์")
        }
        view_btn_back_2.setOnClickListener {
            linearLayout_1.setVisibility(View.VISIBLE)
            linearLayout_2.setVisibility(View.GONE)
            view_title.setText("บันทึกที่อยู่ตามสำเนาทะเบียนบ้าน")
        }
        view_btn_back_3.setOnClickListener {
            linearLayout_2.setVisibility(View.VISIBLE)
            linearLayout_3.setVisibility(View.GONE)
            view_title.setText("บักทึกที่อยู่ปัจจุบัน")
        }

        view_btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_address", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        linearLayout_1.setVisibility(View.VISIBLE)
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ดึงข้อมูลมาเช็ค
        account_username = "60160157"
        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_address")
        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for (ds in dataSnapshot.children) {
                    val username = ds.child("username").getValue(String::class.java)!!
                    val house_regis_address = ds.child("house_regis_address").getValue(String::class.java)!!
                    val house_regis_dist = ds.child("house_regis_dist").getValue(String::class.java)!!
                    val house_regis_amph = ds.child("house_regis_amph").getValue(String::class.java)!!
                    val house_regis_province = ds.child("house_regis_province").getValue(String::class.java)!!
                    val house_regis_zipcode = ds.child("house_regis_zipcode").getValue(String::class.java)!!
                    val house_cur_address  = ds.child("house_cur_address").getValue(String::class.java)!!
                    val house_cur_dist = ds.child("house_cur_dist").getValue(String::class.java)!!
                    val house_cur_amph = ds.child("house_cur_amph").getValue(String::class.java)!!
                    val house_cur_province = ds.child("house_cur_province").getValue(String::class.java)!!
                    val house_cur_zipcode = ds.child("house_cur_zipcode").getValue(String::class.java)!!
                    val house_phone_number  = ds.child("house_phone_number").getValue(String::class.java)!!
                    val mobile_phone_number = ds.child("mobile_phone_number").getValue(String::class.java)!!
                    val work_phone_number  = ds.child("work_phone_number").getValue(String::class.java)!!
                    val work_in_phone_number   = ds.child("work_in_phone_number").getValue(String::class.java)!!

                    if (username == account_username) {

                        Str_key = ds.key.toString()
                        obj_hr_address = hr_address(
                            account_username,
                            house_regis_address,
                            house_regis_dist,
                            house_regis_amph,
                            house_regis_province,
                            house_regis_zipcode,
                            house_cur_address,
                            house_cur_dist,
                            house_cur_amph,
                            house_cur_province,
                            house_cur_zipcode,
                            house_phone_number,
                            mobile_phone_number,
                            work_phone_number,
                            work_in_phone_number
                        )
                    }

                }

                if(Str_key != "") {
                    view_house_regis_address.setText(obj_hr_address.house_regis_address )
                    view_house_regis_dist.setText(obj_hr_address.house_regis_dist )
                    view_house_regis_amph.setText(obj_hr_address.house_regis_amph )
                    view_house_regis_province.setText(obj_hr_address.house_regis_province )
                    view_house_regis_zipcode.setText(obj_hr_address.house_regis_zipcode )

                    view_house_cur_address.setText(obj_hr_address.house_cur_address )
                    view_house_cur_dist.setText(obj_hr_address.house_cur_dist )
                    view_house_cur_amph.setText(obj_hr_address.house_cur_amph )
                    view_house_cur_province.setText(obj_hr_address.house_cur_province  )
                    view_house_cur_zipcode.setText(obj_hr_address.house_cur_zipcode  )

                    view_house_phone_number.setText(obj_hr_address.house_phone_number  )
                    view_mobile_phone_number.setText(obj_hr_address.mobile_phone_number )
                    view_work_phone_number.setText(obj_hr_address.work_phone_number )
                    view_work_in_phone_number.setText(obj_hr_address.work_in_phone_number )
                }

                btn_save.setOnClickListener {

                    if(Str_key == ""){
                        obj_hr_address = hr_address(
                            account_username,
                            view_house_regis_address.text.toString(),
                            view_house_regis_dist.text.toString(),
                            view_house_regis_amph .text.toString(),
                            view_house_regis_province.text.toString(),
                            view_house_regis_zipcode.text.toString(),
                            view_house_cur_address .text.toString(),
                            view_house_cur_dist.text.toString(),
                            view_house_cur_amph.text.toString(),
                            view_house_cur_province .text.toString(),
                            view_house_cur_zipcode.text.toString(),
                            view_house_phone_number.text.toString(),
                            view_mobile_phone_number.text.toString(),
                            view_work_phone_number.text.toString(),
                            view_work_in_phone_number.text.toString()
                        )
                        mMessagesRef.push().setValue(obj_hr_address)
                        Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }else{
                        mMessagesRef.child(Str_key).child("house_regis_address").setValue(view_house_regis_address.text.toString())
                        mMessagesRef.child(Str_key).child("house_regis_dist").setValue(view_house_regis_dist.text.toString())
                        mMessagesRef.child(Str_key).child("house_regis_amph").setValue(view_house_regis_amph.text.toString())
                        mMessagesRef.child(Str_key).child("house_regis_province").setValue(view_house_regis_province.text.toString())
                        mMessagesRef.child(Str_key).child("house_regis_zipcode").setValue(view_house_regis_zipcode.text.toString())
                        mMessagesRef.child(Str_key).child("house_cur_address").setValue(view_house_cur_address.text.toString())
                        mMessagesRef.child(Str_key).child("house_cur_dist").setValue(view_house_cur_dist.text.toString())
                        mMessagesRef.child(Str_key).child("house_cur_amph").setValue(view_house_cur_amph.text.toString())
                        mMessagesRef.child(Str_key).child("house_cur_province").setValue(view_house_cur_province.text.toString())
                        mMessagesRef.child(Str_key).child("house_cur_zipcode").setValue(view_house_cur_zipcode.text.toString())
                        mMessagesRef.child(Str_key).child("house_phone_number").setValue(view_house_phone_number.text.toString())
                        mMessagesRef.child(Str_key).child("mobile_phone_number").setValue(view_mobile_phone_number.text.toString())
                        mMessagesRef.child(Str_key).child("work_phone_number").setValue(view_work_phone_number.text.toString())
                        mMessagesRef.child(Str_key).child("work_in_phone_number").setValue(view_work_in_phone_number.text.toString())

                        Toast.makeText(activity!!.baseContext, "แก้ไขสำเร็จ", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }) // mMessagesRef.addValueEventListener

        btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_address", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return view

    }

    fun newInstance(username:String): AddressFragment {
        val fragment = AddressFragment()
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
