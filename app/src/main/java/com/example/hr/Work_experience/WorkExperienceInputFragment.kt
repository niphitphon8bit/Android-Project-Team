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
    private lateinit var obj_work_exp : work_exp

    data class work_exp (
        var pos_work: String? = "",
        var pos_manager: String? = "",
        var seq_pos: String? = "",
        var start_pos:String? = "",
        var end_pos: String? = "",
        var note: String? = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var view = inflater.inflate(R.layout.fragment_work_experience_input, container, false)
        var pos_work:EditText = view.findViewById(R.id.pos_work)
        var pos_manager:EditText  = view.findViewById(R.id.pos_manager)
        var seq_pos:EditText = view.findViewById(R.id.seq_pos)
        var start_pos:EditText = view.findViewById(R.id.start_pos)
        var end_pos:EditText = view.findViewById(R.id.end_pos)
        var note:EditText = view.findViewById(R.id.note)
        var header:TextView = view.findViewById(R.id.header)
        var submit_btn:Button = view.findViewById(R.id.submit_btn)
        var delete_btn:ImageButton = view.findViewById(R.id.btn_delete)
        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_experience")


        if(type_input == "insert"){
            header.setText("เพิ่มข้อมูลประสบการณ์")
            delete_btn.setVisibility(View.GONE)
        }else{
            header.setText("แก้ไจข้อมูลประสบการณ์")
        }

        submit_btn.setOnClickListener {
            obj_work_exp.pos_work = pos_work.text.toString()
            obj_work_exp.pos_manager = pos_manager.text.toString()
            obj_work_exp.seq_pos= seq_pos.text.toString()
            obj_work_exp.start_pos= start_pos.text.toString()
            obj_work_exp.end_pos= end_pos.text.toString()
            obj_work_exp.note= note.text.toString()

            mMessagesRef.push().setValue(obj_work_exp)
            Toast.makeText(activity!!.baseContext, "เพิ่มสำเร็จ", Toast.LENGTH_SHORT).show()
            activity!!.supportFragmentManager.popBackStack()
        }


        return view
    }

    fun newInstance(type_input:String , pos_work:String , pos_manager:String , seq_pos:String , start_pos:String , end_pos:String , note:String): WorkExperienceInputFragment {
        val fragment = WorkExperienceInputFragment()
        val bundle = Bundle()
        bundle.putString("type_input", type_input)
        bundle.putString("pos_work", pos_work)
        bundle.putString("pos_manager", pos_manager)
        bundle.putString("seq_pos", seq_pos)
        bundle.putString("start_pos", start_pos)
        bundle.putString("end_pos", end_pos)
        bundle.putString("note", note)
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {

            obj_work_exp = WorkExperienceInputFragment.work_exp(
                bundle.getString("pos_work").toString(),
                bundle.getString("pos_manager").toString(),
                bundle.getString("seq_pos").toString(),
                bundle.getString("start_pos").toString(),
                bundle.getString("end_pos").toString(),
                bundle.getString("note").toString()
            )
            type_input = bundle.getString("type_input").toString()
        }
    }

}
