package com.example.hr.Education

import com.example.hr.R

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
import org.json.JSONObject

class EducationAdapter (fragmentActivity: FragmentActivity, val dataSource: JSONArray, val account_username:String?) : RecyclerView.Adapter<EducationAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view
        lateinit var layout : LinearLayout
        lateinit var education_degree: TextView
        lateinit var education_id: TextView
        lateinit var education_start: TextView
        lateinit var education_end: TextView



        fun Holder(){
            layout = View.findViewById<View>(R.id.recy_education_layout) as LinearLayout
            education_degree = View.findViewById<View>(R.id.education_degree) as TextView
            education_id = View.findViewById<View>(R.id.education_name) as TextView
            education_start = View.findViewById<View>(R.id.education_start_date) as TextView
            education_end = View.findViewById<View>(R.id.education_end_date) as TextView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_education, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.education_degree.setText( dataSource.getJSONObject(position).getString("degree").toString() )
        holder.education_id.setText( "ชื่อ: " + dataSource.getJSONObject(position).getString("name").toString() )
        holder.education_start.setText( dataSource.getJSONObject(position).getString("start_date").toString() )
        holder.education_end.setText( dataSource.getJSONObject(position).getString("exp_date").toString() )

        holder.layout.setOnClickListener {
//            for( i in 0 until dataSource.length() ){
//                val item = dataSource.getJSONObject(i)
//                println(item);
//            }
//            print("click layout")
//            var instance : JSONObject? = dataSource.getJSONObject(position)
//            if(instance!!.has("place")) {
//                println("no data")
//            }else {
//                println(dataSource)
//                println("have data")
//            }
            var key = dataSource.getJSONObject(position).getString("key").toString()
            var username = dataSource.getJSONObject(position).getString("username").toString()
            var education_degree = dataSource.getJSONObject(position).getString("degree").toString()
            var education_id = dataSource.getJSONObject(position).getString("name").toString()
            var education_start = dataSource.getJSONObject(position).getString("start_date").toString()
            var education_end = dataSource.getJSONObject(position).getString("exp_date").toString()
            var education_place = dataSource.getJSONObject(position).getString("place").toString()
            var education_country= dataSource.getJSONObject(position).getString("country").toString()
            var education_major = dataSource.getJSONObject(position).getString("major").toString()
            var education_major_type = dataSource.getJSONObject(position).getString("major_type").toString()
            var education_hornors = dataSource.getJSONObject(position).getString("hornors").toString()

            val fm = thisActivity.supportFragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            val load_fragment = EducationInputFragment().newInstance(key, username, education_degree, education_id,education_place,education_country,education_major,education_major_type,education_hornors, education_start, education_end)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_education_input")
            transaction.addToBackStack("fragment_education_input")
            transaction.commit()
        }

    }

}

