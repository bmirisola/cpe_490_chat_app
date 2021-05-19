package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var haveAccountTextView: TextView? = null
    var registerButton: Button? = null
    var emailTextview: TextView? = null
    var passwordTextview: TextView? = null
    var usernameTextview:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        haveAccountTextView = findViewById<TextView>(R.id.have_account_textview)
        registerButton = findViewById<Button>(R.id.register_button)
        emailTextview = findViewById<TextView>(R.id.register_email)
        passwordTextview = findViewById<TextView>(R.id.register_password)
        usernameTextview = findViewById<TextView>(R.id.register_username)
        findViewById<TextView>(R.id.have_account_textview).setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.register_button).setOnClickListener {
            register()
        }
    }

    private fun register(){
        val email = findViewById<TextView>(R.id.register_email).text.toString()
        val password = findViewById<TextView>(R.id.register_password).text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(applicationContext,"Make sure name and password aren't blank!", Toast.LENGTH_LONG).show()

        }

        else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Log.d("MainActivity", "NOOOOOOOOOOOO")
                        return@addOnCompleteListener
                    } else {
                        Toast.makeText(applicationContext, "Successfully created user", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener{
                    Log.d("MainActivity", "Failed to create user: ${it.message}")
                }
        }
    }
}