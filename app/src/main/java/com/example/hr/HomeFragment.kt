package com.example.hr


import android.location.Address
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.hr.Address.AddressFragment
import com.example.hr.Education.EducationFragment
import com.example.hr.Expertise.ExpertiseFragment
import com.example.hr.Membership_card.MembershipCardFragment
import com.example.hr.Personal.PersonalFragment
import com.example.hr.Professional_license.ProfessionalLicenseFragment
import com.example.hr.Work_experience.WorkExperienceFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()

        var btn_personal = view.findViewById<Button>(R.id.view_btn_personal) as Button
        var btn_address = view.findViewById<Button>(R.id.view_btn_address) as Button
        var btn_education = view.findViewById<Button>(R.id.view_btn_education) as Button
        var btn_professional_license = view.findViewById<Button>(R.id.view_btn_license) as Button
        var btn_membership_card = view.findViewById<Button>(R.id.view_btn_membership) as Button
        var btn_expertise = view.findViewById<Button>(R.id.view_btn_expertise) as Button
        var btn_work_experience = view.findViewById<Button>(R.id.view_btn_work) as Button

        btn_personal!!.setOnClickListener{
            val load_fragment = PersonalFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_personal")
            transaction.addToBackStack("fragment_personal")
            transaction.commit()
        }
        btn_address!!.setOnClickListener{
            val load_fragment = AddressFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_address")
            transaction.addToBackStack("fragment_address")
            transaction.commit()
        }
        btn_education!!.setOnClickListener{
            val load_fragment = EducationFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_education")
            transaction.addToBackStack("fragment_education")
            transaction.commit()
        }
        btn_professional_license!!.setOnClickListener{
            val load_fragment = ProfessionalLicenseFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_professional_license")
            transaction.addToBackStack("fragment_professional_license")
            transaction.commit()
        }
        btn_membership_card!!.setOnClickListener{
            val load_fragment = MembershipCardFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_membership_card")
            transaction.addToBackStack("fragment_membership_card")
            transaction.commit()
        }
        btn_expertise!!.setOnClickListener{
            val load_fragment = ExpertiseFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_expertise")
            transaction.addToBackStack("fragment_expertise")
            transaction.commit()
        }
        btn_work_experience!!.setOnClickListener{
            val load_fragment = WorkExperienceFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_work_experience")
            transaction.addToBackStack("fragment_work_experience")
            transaction.commit()
        }
        return view
    }


}
