package com.example.hr.Membership_card


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

import com.example.hr.R
import com.facebook.login.LoginManager
import com.google.firebase.database.FirebaseDatabase
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass.
 */
class MembershipCardInputFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var obj_hr_membership_card : hr_membership_card

    var Str_key = "" // key obj_hr_work_experience from firebase

    data class hr_membership_card (
        var username: String? = "",
        var membership_name: String? = "",
        var membership_id: String? = "",
        var issue_date: String? = "",
        var expiry_date: String? = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_membership_card_input, container, false)

        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_membership_card")

        val view_membership_name = view.findViewById<TextView>(R.id.view_membership_name)
        val view_membership_id = view.findViewById<TextView>(R.id.view_membership_id)
        val view_issue_date= view.findViewById<TextView>(R.id.view_issue_date)
        val view_expiry_date = view.findViewById<TextView>(R.id.view_expiry_date)

        val btn_back = view.findViewById(R.id.view_btn_back) as ImageButton
        val btn_delete = view.findViewById(R.id.view_btn_delete) as ImageButton
        val btn_save = view.findViewById(R.id.view_btn_save) as Button

        if(Str_key != "") {
            view_membership_name.setText(obj_hr_membership_card.membership_name)
            view_membership_id.setText(obj_hr_membership_card.membership_id)
            view_issue_date.setText(obj_hr_membership_card.issue_date)
            view_expiry_date.setText(obj_hr_membership_card.expiry_date)
        }else{
            btn_delete.setVisibility(View.GONE)
        }

        btn_save.setOnClickListener {
            var check_insert = true
            if(view_membership_name.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกชื่อวิชาชีพ", Toast.LENGTH_SHORT).show()
            }else if(view_membership_id.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกเลขที่สมาชิกสภาวิชาชีพ", Toast.LENGTH_SHORT).show()
            }else if(view_issue_date.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกวันออกบัตร", Toast.LENGTH_SHORT).show()
            }else if(view_expiry_date.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกวันหมดอายุ", Toast.LENGTH_SHORT).show()
            }else if(check_insert == true){
                if(Str_key == ""){
                    var user =  obj_hr_membership_card.username
                    obj_hr_membership_card = hr_membership_card(
                        user,
                        view_membership_name.text.toString(),
                        view_membership_id.text.toString(),
                        view_issue_date.text.toString(),
                        view_expiry_date.text.toString()
                    )
                    mMessagesRef.push().setValue(obj_hr_membership_card)
                    Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
                    activity!!.supportFragmentManager.popBackStack()
                }else{
                    mMessagesRef.child(Str_key).child("membership_name").setValue(view_membership_name.text.toString())
                    mMessagesRef.child(Str_key).child("membership_id").setValue(view_membership_id.text.toString())
                    mMessagesRef.child(Str_key).child("issue_date").setValue(view_issue_date.text.toString())
                    mMessagesRef.child(Str_key).child("expiry_date").setValue(view_expiry_date.text.toString())

                    Toast.makeText(activity!!.baseContext, "แก้ไขสำเร็จ", Toast.LENGTH_SHORT).show()
                    activity!!.supportFragmentManager.popBackStack()
                }

            }
        }

        btn_delete.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("ต้องการลบหรือไม่?")
            builder.setPositiveButton("ลบ",
                DialogInterface.OnClickListener { dialog, id ->
                    val mMessagesRef = mRootRef.child("hr_membership_card").child(Str_key)

                    mMessagesRef.setValue(null)

                    Toast.makeText(activity!!.baseContext, "ลบสำเร็จ", Toast.LENGTH_SHORT).show()
                    val fm: FragmentManager = activity!!.getSupportFragmentManager()
                    fm.popBackStack("MembershipCardInputFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                })
            builder.setNegativeButton("ยกเลิก",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()
        }

        btn_back.setOnClickListener{
//            LoginManager.getInstance().logOut()
//            activity!!.supportFragmentManager.popBackStack()
        }

        return view
    }


    fun newInstance(key: String, username:String, membership_name: String, membership_id:String, issue_date:String, expiry_date:String): MembershipCardInputFragment {
        val fragment = MembershipCardInputFragment()
        val bundle = Bundle()
        bundle.putString("key", key)
        bundle.putString("username", username)
        bundle.putString("membership_name", membership_name)
        bundle.putString("membership_id", membership_id)
        bundle.putString("issue_date", issue_date)
        bundle.putString("expiry_date", expiry_date)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            obj_hr_membership_card = hr_membership_card(
                bundle.getString("username").toString(),
                bundle.getString("membership_name").toString(),
                bundle.getString("membership_id").toString(),
                bundle.getString("issue_date").toString(),
                bundle.getString("expiry_date").toString()
            )
            Str_key = bundle.getString("key").toString()
        }
    }


}
