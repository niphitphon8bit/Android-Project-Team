package com.example.hr.Education


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.Address.AddressFragment
import com.example.hr.Membership_card.MembershipCardInputFragment

import com.example.hr.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 */
class EducationInputFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var obj_hr_education : hr_education
    var account_username : String = "60160180"

    var Str_key = "" // key obj_hr_work_experience from firebase

    data class hr_education (
        var username: String? = "",
        var degree: String? = "",
        var name: String? = "",
        var start_date: String? = "",
        var end_date: String? = "",
        var place: String? = "",
        var country: String? = "",
        var major: String? = "",
        var major_type: String? = "",
        var hornors: String? = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_education_input, container, false)

        val view_title = view.findViewById<TextView>(R.id.view_title)

        val linearLayout_1 = view.findViewById<LinearLayout>(R.id.linearLayout_1)
        val linearLayout_2 = view.findViewById<LinearLayout>(R.id.linearLayout_2)
        val linearLayout_3 = view.findViewById<LinearLayout>(R.id.linearLayout_3)

        val view_btn_next_1 = view.findViewById<Button>(R.id.view_btn_next_1)
        val view_btn_next_2 = view.findViewById<Button>(R.id.view_btn_next_2)

        val view_btn_back = view.findViewById<ImageButton>(R.id.view_btn_back)
        val view_btn_back_2 = view.findViewById<Button>(R.id.view_btn_back_2)


        val view_degree = view.findViewById<TextView>(R.id.view_education_degree)
        val view_major = view.findViewById<TextView>(R.id.view_education_major)
        val view_major_type = view.findViewById<TextView>(R.id.view_education_major_type)
        val view_place = view.findViewById<TextView>(R.id.view_education_place)
        val view_country = view.findViewById<TextView>(R.id.view_education_country)

        val view_hornors = view.findViewById<TextView>(R.id.view_education_hornors)
        val view_name = view.findViewById<TextView>(R.id.view_education_name)
        val view_start = view.findViewById<TextView>(R.id.view_start_date)
        val view_end = view.findViewById<TextView>(R.id.view_end_date)

        val btn_back = view.findViewById(R.id.view_btn_back) as ImageButton
        val btn_save = view.findViewById(R.id.view_btn_save) as Button
        val btn_delete = view.findViewById(R.id.view_btn_delete) as ImageButton

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        view_btn_next_1.setOnClickListener {
            linearLayout_1.setVisibility(View.GONE)
            linearLayout_2.setVisibility(View.VISIBLE)
           // view_title.setText("บักทึนที่อยู่ปัจจุบัน")
        }

        view_btn_back_2.setOnClickListener {
            linearLayout_1.setVisibility(View.VISIBLE)
            linearLayout_2.setVisibility(View.GONE)
           // view_title.setText("บันทึกที่อยู่ตามสำเนาทะเบียนบ้าน")
        }




        view_btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_education", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        linearLayout_1.setVisibility(View.VISIBLE)
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ดึงข้อมูลมาเช็ค
        account_username = "60160157"
        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_education")
        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = JSONArray()

                for (ds in dataSnapshot.children) {

//                    val jObject = JSONObject()

                    val username = ds.child("username").getValue(String::class.java)!!
//                    val degree = ds.child("degree").getValue(String::class.java)!!
//                    val name = ds.child("name").getValue(String::class.java)!!
//                    val place = ds.child("place").getValue(String::class.java)!!
//                    val country = ds.child("country").getValue(String::class.java)!!
//                    val major = ds.child("major").getValue(String::class.java)!!
//                    val major_type  = ds.child("major_type").getValue(String::class.java)!!
//                    val hornors = ds.child("hornors").getValue(String::class.java)!!
//                    val start_date = ds.child("start_date").getValue(String::class.java)!!
//                    val end_date  = ds.child("end_date").getValue(String::class.java)!!

                    if (username == account_username) {
//                        jObject.put("key", ds.key)
//                        jObject.put("username", username)
//                        jObject.put("house_regis_address", house_regis_address)
//                        jObject.put("house_regis_dist", house_regis_dist)
//                        jObject.put("house_regis_amph", house_regis_amph)
//                        jObject.put("house_regis_province", house_regis_province)
//                        jObject.put("house_regis_zipcode", house_regis_zipcode)
//                        jObject.put("house_cur_address", house_cur_address)
//                        jObject.put("house_cur_dist", house_cur_dist)
//                        jObject.put("house_cur_amph", house_cur_amph)
//                        jObject.put("house_cur_province", house_cur_province)
//                        jObject.put("house_cur_zipcode", house_cur_zipcode)
//                        jObject.put("house_phone_number", house_phone_number)
//                        jObject.put("mobile_phone_number", mobile_phone_number)
//                        jObject.put("work_phone_number", work_phone_number)
//                        jObject.put("work_in_phone_number", work_in_phone_number)
//
//                        list.put(jObject)
//                        Str_key = ds.key.toString()
//                        obj_hr_education = hr_education(
//                            account_username,
//                            degree,
//                            name,
//                            place,
//                            country,
//                            major,
//                            major_type,
//                            hornors,
//                            start_date,
//                            end_date

                       // )
                    }

                }

                if(Str_key != "") {
                    view_degree.setText(obj_hr_education.degree )
                    view_place.setText(obj_hr_education.place )
                    view_country.setText(obj_hr_education.country )
                    view_major.setText(obj_hr_education.major )
                    view_major_type.setText(obj_hr_education.major_type )

                    view_hornors.setText(obj_hr_education.hornors )
                    view_name.setText(obj_hr_education.name )
                    view_start.setText(obj_hr_education.start_date)
                    view_end.setText(obj_hr_education.end_date)

                }

                btn_save.setOnClickListener {

                    if(Str_key == ""){
                        obj_hr_education = hr_education(
                            account_username,
                            view_degree.text.toString(),
                            view_place.text.toString(),
                            view_country.text.toString(),
                            view_major.text.toString(),
                            view_major_type.text.toString(),
                            view_hornors.text.toString(),
                            view_name.text.toString(),
                            view_start.text.toString(),
                            view_end.text.toString()
                        )
                        mMessagesRef.push().setValue(obj_hr_education)
                        Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }else{
                        mMessagesRef.child(Str_key).child("degree").setValue(view_degree.text.toString())
                        mMessagesRef.child(Str_key).child("place").setValue(view_place.text.toString())
                        mMessagesRef.child(Str_key).child("country").setValue(view_country.text.toString())
                        mMessagesRef.child(Str_key).child("major").setValue(view_major.text.toString())
                        mMessagesRef.child(Str_key).child("major_type").setValue(view_major_type.text.toString())
                        mMessagesRef.child(Str_key).child("hornors").setValue(view_hornors.text.toString())
                        mMessagesRef.child(Str_key).child("name").setValue(view_name.text.toString())
                        mMessagesRef.child(Str_key).child("start_date").setValue(view_name.text.toString())
                        mMessagesRef.child(Str_key).child("exp_date").setValue(view_name.text.toString())

                        Toast.makeText(activity!!.baseContext, "แก้ไขสำเร็จ", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }) // mMessagesRef.addValueEventListener

        btn_delete.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("ต้องการลบหรือไม่?")
            builder.setPositiveButton("ลบ",
                DialogInterface.OnClickListener { dialog, id ->
                    val mMessagesRef = mRootRef.child("hr_education").child(Str_key)

                    mMessagesRef.setValue(null)

                    Toast.makeText(activity!!.baseContext, "ลบสำเร็จ", Toast.LENGTH_SHORT).show()
                    val fm: FragmentManager = activity!!.getSupportFragmentManager()
                    fm.popBackStack("_MembershipCardInputFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                })
            builder.setNegativeButton("ยกเลิก",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()
        }

        btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_education", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return view

    }


    fun newInstance(key: String, username:String, degree: String, name:String, start_date:String, exp_date:String):  EducationInputFragment {
        val fragment =  EducationInputFragment()
        val bundle = Bundle()
        bundle.putString("key", key)
        bundle.putString("username", username)
        bundle.putString("degree", degree)
        bundle.putString("name", name)
        bundle.putString("start_date", start_date)
        bundle.putString("exp_date", exp_date)

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            obj_hr_education = hr_education(
                bundle.getString("username").toString(),
                bundle.getString("degree").toString(),
                bundle.getString("name").toString(),
                bundle.getString("start_date").toString(),
                bundle.getString("exp_date").toString(),
                bundle.getString("place").toString(),
                bundle.getString("country").toString(),
                bundle.getString("major").toString(),
                bundle.getString("major_type").toString()


            )
            Str_key = bundle.getString("key").toString()
        }
    }

}
