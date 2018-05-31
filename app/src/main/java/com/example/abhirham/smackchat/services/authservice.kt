package com.example.abhirham.smackchat.services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.abhirham.smackchat.utilities.loginurl
import com.example.abhirham.smackchat.utilities.regurl
import org.json.JSONException
import org.json.JSONObject
import java.security.AccessControlContext

object authservice {


    var isloggedin = false
    var useremail = ""
    var authtoken = ""
    fun registeruser(context: Context,email: String, password: String, complete: (Boolean) -> Unit ){
        val jsonbody = JSONObject()
        jsonbody.put("email", email)
        jsonbody.put("password", password)
        val request = jsonbody.toString()

        val register = object : StringRequest(Method.POST, regurl,Response.Listener {response ->
            println(response)
            complete(true)
        }, Response.ErrorListener {error ->
            Log.d("ERROR","Could not register user : $error")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return request.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(register)
    }

    fun loginuser(context: Context,email: String,password: String,complete: (Boolean) -> Unit){
        val jsonbody = JSONObject()
        jsonbody.put("email", email)
        jsonbody.put("password", password)
        val request = jsonbody.toString()

        val loginrequest = object : JsonObjectRequest(Method.POST, loginurl,null,Response.Listener {response ->
           try {
               useremail= response.getString("email")
               authtoken = response.getString("token")
               isloggedin=true
               complete(true)
           }catch (e: JSONException){
                Log.d("JSON","EXC: "+e.localizedMessage)
               complete(false)
           }


        }, Response.ErrorListener {error ->
            Log.d("ERROR","Could not register user : $error")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
            override fun getBody(): ByteArray {
                return request.toByteArray()
            }
        }
        Volley.newRequestQueue(context).add(loginrequest)
    }
}