package com.example.hr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hr.Authen.LoginFragment
import com.facebook.login.Login

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        val load_fragment = LoginFragment()
        transaction.replace(R.id.contentContainer, load_fragment, "fragment_login")
        transaction.addToBackStack("fragment_login")
        transaction.commit()
    }

    override fun onBackPressed() {
        val manager = supportFragmentManager.findFragmentById(R.id.contentContainer)
        if (manager is LoginFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
