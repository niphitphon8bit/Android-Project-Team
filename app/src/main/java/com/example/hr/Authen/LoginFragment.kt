package com.example.hr.Authen

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hr.HomeFragment
import com.example.hr.Membership_card.MembershipCardInputFragment
import com.example.hr.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * Class: LoginFragment
 * Author: Namchok Singhachai
 */
class LoginFragment : Fragment() {

    private var btn_signin : Button ? = null
    private lateinit var obj_hr_account : hr_account
    data class hr_account (
        var username: String? = "",
        var password: String? = "",
        var name: String? = ""
    )
    /**
     * Function: onCreateView
     * Author: Namchok Singhachai
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =   inflater.inflate(R.layout.fragment_login, container, false)

        val view_input_username = view.findViewById<TextView>(R.id.input_username)
        val view_input_password =view.findViewById<TextView>(R.id.input_password)

        // Button Sign In
        btn_signin = view.findViewById<Button>(R.id.view_btn_signin)
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // connect firebase
        val mRootRef = FirebaseDatabase.getInstance().getReference()
        val mMessagesRef = mRootRef.child("hr_account")
        val list = JSONArray()
        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val temp_name = ds.child("name").getValue(String::class.java)!!
                    val temp_username = ds.child("username").getValue(String::class.java)!!
                    val temp_password = ds.child("password").getValue(String::class.java)!!

                    jObject.put("key",ds.key)
                    jObject.put("name",temp_name)
                    jObject.put("username",temp_username)
                    jObject.put("password",temp_password)

                    list.put(jObject)

                }

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Set on button Sign In
        btn_signin!!.setOnClickListener{
            var validate = true
            if(view_input_username.text.toString() == ""){
                validate = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกชื่อผู้ใช้", Toast.LENGTH_SHORT).show()
            }else if(view_input_password.text.toString() == ""){
                validate = false
                Toast.makeText(activity!!.baseContext, "กรุณากรอกรหัสผ่าน", Toast.LENGTH_SHORT).show()
            }

            if(validate == true){
                var check_username = false
                var check_password = false
                var account_name = ""
                for (i in 0 until list.length()) {
                    if(list.getJSONObject(i).getString("username").toString() == view_input_username.text.toString()){
                        check_username = true
                        if(list.getJSONObject(i).getString("password").toString() == view_input_password.text.toString()){
                            check_password = true
                            account_name = list.getJSONObject(i).getString("name").toString()
                            break
                        }
                    }
                }

                if(check_username == true && check_password == true){
                    Toast.makeText(context,"ยินดีต้อนรับ " + account_name!!.toString(), Toast.LENGTH_SHORT).show()

                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    val load_fragment = HomeFragment().newInstance(account_name!!.toString(), view_input_username!!.text.toString())

                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    transaction.replace(R.id.contentContainer, load_fragment,"fragment_home")
                    transaction.addToBackStack("fragment_home")
                    transaction.commit()
                }else{
                    Toast.makeText(context,"ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show()
                }
            }

//            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
//            builder.setMessage("เลือกหน่อย")
//            builder.setPositiveButton("เข้าสู่ระบบ",
//                DialogInterface.OnClickListener { dialog, id ->
//
//                })
//            builder.setNegativeButton("ลงทะเบียน",
//                DialogInterface.OnClickListener { dialog, which ->
//                    obj_hr_account = hr_account(
//                        view_input_username.text.toString(),
//                        view_input_password.text.toString(),
//                        view_input_username.text.toString()
//                    )
//                    mMessagesRef.push().setValue(obj_hr_account)
//                })
//            builder.show()


//                })
//            builder.setNegativeButton("ลงทะเบียน",
//                DialogInterface.OnClickListener { dialog, which ->
//                    obj_hr_account = hr_account(
//                        view_input_username.text.toString(),
//                        view_input_password.text.toString(),
//                        view_input_username.text.toString()
//                    )
//                    mMessagesRef.push().setValue(obj_hr_account)
//                    Toast.makeText(context,"สมัครสำเร็จ!", Toast.LENGTH_SHORT).show()
//                })
//            builder.show()
//
        }
        // ---------------------------------------------------------------------------- //

        return view
    }
    // ---------------------------------------------------------------------------- //

}
