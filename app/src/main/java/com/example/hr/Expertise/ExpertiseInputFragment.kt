package com.example.hr.Expertise


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
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class ExpertiseInputFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var obj_hr_expertise : hr_expertise

    var Str_key = "" // key obj_hr_work_experience from firebase

    data class hr_expertise (
        var username: String? = "",
        var title_name_th: String? = "",
        var title_name_en: String? = "",
        var text_th: String? = "",
        var text_en: String? = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_expertise_input, container, false)

        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_expertise")


        val view_title_name_th = view.findViewById<TextView>(R.id.view_title_name_th)
        val view_title_name_en = view.findViewById<TextView>(R.id.view_title_name_en)
        val view_text_th= view.findViewById<TextView>(R.id.view_text_th)
        val view_text_en = view.findViewById<TextView>(R.id.view_text_en)

        val btn_back = view.findViewById(R.id.view_btn_back) as ImageButton
        val btn_delete = view.findViewById(R.id.view_btn_delete) as ImageButton
        val btn_save = view.findViewById(R.id.view_btn_save) as Button

        if(Str_key != "") {
            view_title_name_th.setText(obj_hr_expertise.title_name_th)
            view_title_name_en.setText(obj_hr_expertise.title_name_en)
            view_text_th.setText(obj_hr_expertise.text_th)
            view_text_en.setText(obj_hr_expertise.text_en)
        }

        btn_save.setOnClickListener {
            var check_insert = true
            if(view_title_name_th.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกเรื่อง (ภาษาไทย)\n", Toast.LENGTH_SHORT).show()
            }else if(view_title_name_en.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกเรื่อง (อังกฤษ)", Toast.LENGTH_SHORT).show()
            }else if(view_text_th.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกวประเด็นสำคัญ (ภาษาไทย)", Toast.LENGTH_SHORT).show()
            }else if(view_text_en.text.toString() == ""){
                check_insert = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกวประเด็นสำคัญ (อังกฤษ)", Toast.LENGTH_SHORT).show()
            }else if(check_insert == true){
                if(Str_key == ""){
                    var user =  obj_hr_expertise.username
                    obj_hr_expertise = hr_expertise(
                        user,
                        view_title_name_th.text.toString(),
                        view_title_name_en.text.toString(),
                        view_text_th.text.toString(),
                        view_text_en.text.toString()
                    )
                    mMessagesRef.push().setValue(obj_hr_expertise)
                    Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
                    activity!!.supportFragmentManager.popBackStack()
                }else{
                    mMessagesRef.child(Str_key).child("title_name_th").setValue(view_title_name_th.text.toString())
                    mMessagesRef.child(Str_key).child("title_name_en").setValue(view_title_name_en.text.toString())
                    mMessagesRef.child(Str_key).child("text_th").setValue(view_text_th.text.toString())
                    mMessagesRef.child(Str_key).child("text_en").setValue(view_text_en.text.toString())

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
                    val mMessagesRef = mRootRef.child("hr_expertise").child(Str_key)

                    mMessagesRef.setValue(null)

                    Toast.makeText(activity!!.baseContext, "ลบสำเร็จ", Toast.LENGTH_SHORT).show()
                    val fm: FragmentManager = activity!!.getSupportFragmentManager()
                    fm.popBackStack("_ExpertiseInputFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                })
            builder.setNegativeButton("ยกเลิก",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()
        }

        btn_back.setOnClickListener{
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("_ExpertiseInputFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return view
    }

    fun newInstance(key: String, username:String, title_name_th: String, title_name_en:String, text_th:String, text_en:String): ExpertiseInputFragment {
        val fragment = ExpertiseInputFragment()
        val bundle = Bundle()
        bundle.putString("key", key)
        bundle.putString("username", username)
        bundle.putString("title_name_th", title_name_th)
        bundle.putString("title_name_en", title_name_en)
        bundle.putString("text_th", text_th)
        bundle.putString("text_en", text_en)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            obj_hr_expertise = hr_expertise(
                bundle.getString("username").toString(),
                bundle.getString("title_name_th").toString(),
                bundle.getString("title_name_en").toString(),
                bundle.getString("text_th").toString(),
                bundle.getString("text_en").toString()
            )
            Str_key = bundle.getString("key").toString()
        }
    }


}
