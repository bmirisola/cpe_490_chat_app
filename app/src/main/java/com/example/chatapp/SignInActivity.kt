package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<Button>(R.id.log_in_button).setOnClickListener{
           logIn()

        }
    }

    private fun logIn(){
        val email = findViewById<TextView>(R.id.log_in_email).text.toString()
        val password = findViewById<TextView>(R.id.log_in_password).text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(applicationContext,"Make sure name and password aren't blank!", Toast.LENGTH_LONG).show()

        }
        else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword().addOnCompleteListener(){

            }
        }
    }
}