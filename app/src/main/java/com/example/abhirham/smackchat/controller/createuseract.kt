package com.example.abhirham.smackchat.controller

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.abhirham.smackchat.R
import com.example.abhirham.smackchat.R.id.*
import com.example.abhirham.smackchat.services.authservice
import kotlinx.android.synthetic.main.activity_createuseract.*
import java.util.*

class createuseract : AppCompatActivity() {

    var avataar = "profiledefault"
    var avataarcolor = "[0.5, 0.5, 0.5, 1]"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createuseract)

        cavataarimg.setOnClickListener {
            var random = Random()
            val color = random.nextInt(2)
            val avataarno = random.nextInt(28)
            if(color == 0){
                avataar = "light$avataarno"
            }else
                avataar="dark$avataarno"
            val resid = resources.getIdentifier(avataar,"drawable", packageName)
            cavataarimg.setImageResource(resid)
        }

        bgbtn.setOnClickListener {
            var random = Random()
            val r = random.nextInt(255)
            val g = random.nextInt(255)
            val b = random.nextInt(255)
            cavataarimg.setBackgroundColor(Color.rgb(r,g,b))
            val savedr = r.toDouble()/255
            val savedg = g.toDouble()/255
            val savedb = b.toDouble()/255
            avataarcolor = "[$savedr, $savedg, $savedb, 1]"
        }

        createuserbtn.setOnClickListener {
            authservice.registeruser(this,cemail.text.toString(),cpassword.text.toString()){complete ->
                if(complete)
                    authservice.loginuser(this,cemail.text.toString(),cpassword.text.toString()){loginsucess ->
                        if(loginsucess){
                            println(authservice.authtoken)
                            println(authservice.useremail)
                        }
                    }
                else
                    Toast.makeText(this,"Failed to add user",Toast.LENGTH_LONG).show()

            }
        }
    }




}
