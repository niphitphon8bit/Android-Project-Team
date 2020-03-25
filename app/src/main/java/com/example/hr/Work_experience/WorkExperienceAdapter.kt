package com.example.hr.Work_experience

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import org.json.JSONArray

class WorkExperienceAdapter (fragmentActivity: FragmentActivity, val dataSource: JSONArray, val account_username:String) : RecyclerView.Adapter<WorkExperienceAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view
        lateinit var layout : LinearLayout
        lateinit var rank:TextView
        lateinit var start_date:TextView
        lateinit var end_date:TextView
//        lateinit var view_transaction_category: TextView


        fun Holder(){

            rank = View.findViewById(R.id.position) as TextView
            start_date = View.findViewById(R.id.start_date) as TextView
            end_date = View.findViewById(R.id.end_date) as TextView
//            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
//            view_transaction_category = View.findViewById<View>(R.id.view_transaction_category) as TextView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_work_experience, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()
        holder.rank.setText(dataSource.getJSONObject(position).getString("position_work_name").toString() )
        holder.start_date.setText(dataSource.getJSONObject(position).getString("start_date").toString() )
        holder.end_date.setText(dataSource.getJSONObject(position).getString("end_date").toString() )

//        holder.view_transaction_category.setText( dataSource.getJSONObject(position).getString("categories_name").toString() )
//        holder.view_transaction_note.setText( dataSource.getJSONObject(position).getString("transaction_note").toString() )


        holder.layout.setOnClickListener {
            var pos_work = dataSource.getJSONObject(position).getString("55").toString()
            var pos_manager = dataSource.getJSONObject(position).getString("55").toString()
            var seq_pos = dataSource.getJSONObject(position).getString("55").toString()
            var start_pos = dataSource.getJSONObject(position).getString("55").toString()
            var end_pos = dataSource.getJSONObject(position).getString("55").toString()
            var note = dataSource.getJSONObject(position).getString("55").toString()

            val fm = thisActivity.supportFragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            val load_fragment = WorkExperienceInputFragment().newInstance(pos_work, pos_manager, seq_pos, start_pos, end_pos, note) as WorkExperienceInputFragment
            transaction.replace(R.id.contentContainer, load_fragment,"WorkExperienceInputFragment")
            transaction.addToBackStack("WorkExperienceInputFragment")
            transaction.commit()

//            var key = dataSource.getJSONObject(position).getString("key").toString()
//            var categories_name = dataSource.getJSONObject(position).getString("categories_name").toString()
//            var categories_type = dataSource.getJSONObject(position).getString("categories_type").toString()
//            var transaction_amount = dataSource.getJSONObject(position).getString("transaction_amount").toString()
//            var transaction_date = dataSource.getJSONObject(position).getString("transaction_date").toString()
//            var transaction_note = dataSource.getJSONObject(position).getString("transaction_note").toString()
//            var username = dataSource.getJSONObject(position).getString("username").toString()
//
//
//            val fm = thisActivity.supportFragmentManager
//            val transaction: FragmentTransaction = fm!!.beginTransaction()
//            val transaction_Fragment = TransactionFragment().newInstance(key, categories_name, categories_type, transaction_amount, transaction_date, transaction_note, username)
//            transaction.replace(R.id.contentContainer, transaction_Fragment,"fragment_transaction")
//            transaction.addToBackStack("fragment_transaction")
//            transaction.commit()
        }

    }





}