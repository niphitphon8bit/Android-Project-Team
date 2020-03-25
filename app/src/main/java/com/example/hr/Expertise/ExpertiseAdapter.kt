package com.example.hr.Expertise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.hr.R
import org.json.JSONArray

class ExpertiseAdapter (fragmentActivity: FragmentActivity, val dataSource: JSONArray, val account_username:String) : RecyclerView.Adapter<ExpertiseAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view
        lateinit var layout : LinearLayout
        lateinit var view_title_name_th: TextView
        lateinit var view_title_name_en: TextView
        lateinit var view_text_th: TextView
        lateinit var view_text_en: TextView

        fun Holder(){
            layout = View.findViewById<View>(R.id.recy_expertise_layout) as LinearLayout
            view_title_name_th = View.findViewById<View>(R.id.recy_view_title_name_th) as TextView
           // view_title_name_en = View.findViewById<View>(R.id.view_title_name_en) as TextView
            view_text_th = View.findViewById<View>(R.id.recy_view_text_th) as TextView
           // view_text_en = View.findViewById<View>(R.id.view_text_en) as TextView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ExpertiseAdapter.Holder {
        return ExpertiseAdapter.Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recy_expertise, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.view_title_name_th.setText( dataSource.getJSONObject(position).getString("title_name_th").toString() )
//        holder.view_title_name_en.setText( dataSource.getJSONObject(position).getString("title_name_en").toString() )
        holder.view_text_th.setText( dataSource.getJSONObject(position).getString("text_th").toString() )
//        holder.view_text_en.setText( dataSource.getJSONObject(position).getString("text_en").toString() )

        holder.layout.setOnClickListener {
            var key = dataSource.getJSONObject(position).getString("key").toString()
            var username = dataSource.getJSONObject(position).getString("username").toString()
            var title_name_th = dataSource.getJSONObject(position).getString("title_name_th").toString()
            var title_name_en = dataSource.getJSONObject(position).getString("title_name_en").toString()
            var text_th = dataSource.getJSONObject(position).getString("text_th").toString()
            var text_en = dataSource.getJSONObject(position).getString("text_en").toString()

            val fm = thisActivity.supportFragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            val load_fragment = ExpertiseInputFragment().newInstance(key, username, title_name_th, title_name_en, text_th, text_en)
            transaction.replace(R.id.contentContainer, load_fragment,"_ExpertiseInputFragment")
            transaction.addToBackStack("_ExpertiseInputFragment")
            transaction.commit()
        }

    }
}