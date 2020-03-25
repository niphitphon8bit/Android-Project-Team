package com.example.hr.Membership_card

import com.example.hr.R

package com.example.projectmymoney.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

import org.json.JSONArray

class MembershipCardAdapter (fragmentActivity: FragmentActivity, val dataSource: JSONArray, val account_username:String) : RecyclerView.Adapter<MembershipCardAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view
        lateinit var layout : LinearLayout
        lateinit var view_professional_name: TextView
        lateinit var view_membership_id: TextView
        lateinit var view_issue_date: TextView
        lateinit var view_expiry_date: TextView

        fun Holder(){
            layout = View.findViewById<View>(R.id.recy_membership_card_layout) as LinearLayout
            view_professional_name = View.findViewById<View>(R.id.view_professional_name) as TextView
            view_membership_id = View.findViewById<View>(R.id.view_member_number) as TextView
            view_issue_date = View.findViewById<View>(R.id.view_issue_date) as TextView
            view_expiry_date = View.findViewById<View>(R.id.view_expiry_date) as TextView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_membership_card, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.view_professional_name.setText( dataSource.getJSONObject(position).getString("categories_name").toString() )
        holder.view_membership_id.setText( dataSource.getJSONObject(position).getString("transaction_note").toString() )
        holder.view_issue_date.setText( dataSource.getJSONObject(position).getString("transaction_date").toString() )
        holder.view_expiry_date.setText( dataSource.getJSONObject(position).getString("transaction_date").toString() )

        holder.layout.setOnClickListener {
            var key = dataSource.getJSONObject(position).getString("key").toString()
            var username = dataSource.getJSONObject(position).getString("username").toString()
            var professional_name = dataSource.getJSONObject(position).getString("categories_name").toString()
            var member_number = dataSource.getJSONObject(position).getString("categories_type").toString()
            var issue_date = dataSource.getJSONObject(position).getString("transaction_amount").toString()
            var expiry_date = dataSource.getJSONObject(position).getString("transaction_date").toString()

            val fm = thisActivity.supportFragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            val Fragment = MembershipCardInputFragment().newInstance(key, username, professional_name, member_number, issue_date, expiry_date)
            transaction.replace(R.id.contentContainer, Fragment,"MembershipCardInputFragment")
            transaction.addToBackStack("MembershipCardInputFragment")
            transaction.commit()
        }

    }





}

