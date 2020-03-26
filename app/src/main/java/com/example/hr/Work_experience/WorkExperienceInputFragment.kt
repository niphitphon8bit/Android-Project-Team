package com.example.hr.Work_experience


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.hr.Membership_card.MembershipCardInputFragment

import com.example.hr.R
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class WorkExperienceInputFragment : Fragment() {
    var type_input:String = "";
    private lateinit var obj_work_exp : hr_work_experience
    var Str_key = "" // key obj_hr_work_experience from firebase

    data class hr_work_experience (
        var username : String? = "",
        var position_work_name : String? = "",
        var position_manage_name : String? = "",
        var position_level :String? = "",
        var manage_name : String? = "",
        var place : String? = "",
        var start_date  : String? = "",
        var end_date  : String? = "",
        var text_th   : String? = "",
        var text_en    : String? = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var view = inflater.inflate(R.layout.fragment_work_experience_input, container, false)

        var pos_work:EditText = view.findViewById(R.id.pos_work)
        var pos_manager:EditText  = view.findViewById(R.id.pos_manager)
        var seq_pos:EditText = view.findViewById(R.id.seq_pos)
        var manage_name:EditText = view.findViewById(R.id.manage_name)
        var place:EditText = view.findViewById(R.id.place)
        var start_pos:EditText = view.findViewById(R.id.start_pos)
        var end_pos:EditText = view.findViewById(R.id.end_pos)
        var note:EditText = view.findViewById(R.id.note)

        var header:TextView = view.findViewById(R.id.header)

        var submit_btn:Button = view.findViewById(R.id.submit_btn)
        var delete_btn:ImageButton = view.findViewById(R.id.btn_delete)

        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_experience")


        if(Str_key == ""){
            header.setText("เพิ่มข้อมูลประสบการณ์")
            delete_btn.setVisibility(View.GONE)
        }else{
            header.setText("แก้ไขข้อมูลประสบการณ์")
        }


        submit_btn.setOnClickListener {
            if(Str_key == ""){
                var user =  obj_work_exp.username
                obj_work_exp.username = user
                obj_work_exp.position_work_name  = pos_work.text.toString()
                obj_work_exp.position_manage_name  = pos_manager.text.toString()
                obj_work_exp.position_level = seq_pos.text.toString()
                obj_work_exp.manage_name = manage_name.text.toString()
                obj_work_exp.place = place.text.toString()
                obj_work_exp.start_date = start_pos.text.toString()
                obj_work_exp.end_date  = end_pos.text.toString()
                obj_work_exp.text_th  = note.text.toString()

                mMessagesRef.push().setValue(obj_work_exp)
                Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
                activity!!.supportFragmentManager.popBackStack()
            }else{

            }

        }


        return view
    }

    fun newInstance(username: String, position_work_name :String , position_manage_name :String , position_level :String , manage_name :String , place :String , start_date :String, end_date  :String, text_th  :String): WorkExperienceInputFragment {
        val fragment = WorkExperienceInputFragment()
        val bundle = Bundle()
        bundle.putString("username ", username)
        bundle.putString("position_work_name", position_work_name)
        bundle.putString("position_manage_name", position_manage_name)
        bundle.putString("position_level", position_level)
        bundle.putString("manage_name", manage_name)
        bundle.putString("place", place)
        bundle.putString("start_date", start_date)
        bundle.putString("end_date", end_date)
        bundle.putString("text_th", text_th)
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            obj_work_exp = hr_work_experience(
                bundle.getString("username").toString(),
                bundle.getString("position_work_name").toString(),
                bundle.getString("position_manage_name").toString(),
                bundle.getString("position_level").toString(),
                bundle.getString("manage_name").toString(),
                bundle.getString("place").toString(),
                bundle.getString("start_date").toString(),
                bundle.getString("end_date").toString(),
                bundle.getString("text_th ").toString()
            )
            Str_key = bundle.getString("key").toString()
        }
    }

}
