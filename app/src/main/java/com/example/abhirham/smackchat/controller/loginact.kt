package com.example.abhirham.smackchat.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.abhirham.smackchat.R
import kotlinx.android.synthetic.main.activity_loginact.*

class loginact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginact)

        signupbtn.setOnClickListener {
            val act = Intent(this, createuseract::class.java)
            startActivity(act)

        }

    }


}
