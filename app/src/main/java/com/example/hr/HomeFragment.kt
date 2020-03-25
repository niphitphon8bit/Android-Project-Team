package com.example.hr

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
 * Class: HomeFragment
 * Author: Namchok Singhachai
 */
class HomeFragment : Fragment() {

    lateinit var btn_personal : Button
    lateinit var btn_address : Button
    lateinit var btn_education : Button
    lateinit var btn_professional_license : Button
    lateinit var btn_membership_card : Button
    lateinit var btn_expertise : Button
    lateinit var btn_work_experience : Button
    private lateinit var load_fragment : Fragment

    /**
     * Function: onCreateView
     * Author: Namchok Singhachai
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()

        // --------------------- Set variable form view ------------------------------- //
        btn_personal = view.findViewById<Button>(R.id.view_btn_personal) as Button
        btn_address = view.findViewById<Button>(R.id.view_btn_address) as Button
        btn_education = view.findViewById<Button>(R.id.view_btn_education) as Button
        btn_professional_license = view.findViewById<Button>(R.id.view_btn_license) as Button
        btn_membership_card = view.findViewById<Button>(R.id.view_btn_membership) as Button
        btn_expertise = view.findViewById<Button>(R.id.view_btn_expertise) as Button
        btn_work_experience = view.findViewById<Button>(R.id.view_btn_work) as Button
        // ---------------------------------------------------------------------------- //

        btn_personal.setOnClickListener{
            load_fragment = PersonalFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_personal")
            transaction.addToBackStack("fragment_personal")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //

        btn_address.setOnClickListener{
            load_fragment = AddressFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_address")
            transaction.addToBackStack("fragment_address")
            transaction.commit()
        }

        btn_education.setOnClickListener{
            load_fragment = EducationFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_education")
            transaction.addToBackStack("fragment_education")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //

        btn_professional_license.setOnClickListener{
            load_fragment = ProfessionalLicenseFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_professional_license")
            transaction.addToBackStack("fragment_professional_license")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //

        btn_membership_card.setOnClickListener{
            load_fragment = MembershipCardFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_membership_card")
            transaction.addToBackStack("fragment_membership_card")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //

        btn_expertise.setOnClickListener{
            load_fragment = ExpertiseFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_expertise")
            transaction.addToBackStack("fragment_expertise")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //

        btn_work_experience.setOnClickListener{
            load_fragment = WorkExperienceFragment()
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            transaction.replace(R.id.contentContainer, load_fragment,"fragment_work_experience")
            transaction.addToBackStack("fragment_work_experience")
            transaction.commit()
        }
        // ---------------------------------------------------------------------------- //
        return view
    }
    // ---------------------------------------------------------------------------- //


}
