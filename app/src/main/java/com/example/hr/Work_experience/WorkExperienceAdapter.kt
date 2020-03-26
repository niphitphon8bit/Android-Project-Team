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

        fun Holder(){
            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            rank = View.findViewById(R.id.position) as TextView
            start_date = View.findViewById(R.id.start_date) as TextView
            end_date = View.findViewById(R.id.end_date) as TextView
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

        holder.layout.setOnClickListener {
            var key = dataSource.getJSONObject(position).getString("key").toString()
            var username  = dataSource.getJSONObject(position).getString("username").toString()
            var position_work_name = dataSource.getJSONObject(position).getString("position_work_name").toString()
            var position_manage_name = dataSource.getJSONObject(position).getString("position_manage_name").toString()
            var position_level = dataSource.getJSONObject(position).getString("position_level").toString()
            var manage_name = dataSource.getJSONObject(position).getString("manage_name").toString()
            var place = dataSource.getJSONObject(position).getString("place").toString()
            var start_date = dataSource.getJSONObject(position).getString("start_date").toString()
            var end_date = dataSource.getJSONObject(position).getString("end_date").toString()
            var text_th = dataSource.getJSONObject(position).getString("text_th").toString()

            val fm = thisActivity.supportFragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            val load_fragment = WorkExperienceInputFragment().newInstance(username, position_work_name, position_manage_name, position_level, manage_name, place, start_date, end_date, text_th) as WorkExperienceInputFragment
            transaction.replace(R.id.contentContainer, load_fragment,"WorkExperienceInputFragment")
            transaction.addToBackStack("WorkExperienceInputFragment")
            transaction.commit()

        }

    }





}