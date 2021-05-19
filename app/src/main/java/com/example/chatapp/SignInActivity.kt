package com.example.chatapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<Button>(R.id.log_in_button).setOnClickListener {
            logIn()
        }
    }

    private fun logIn() {
        val email = findViewById<TextView>(R.id.log_in_email).text.toString()
        val password = findViewById<TextView>(R.id.log_in_password).text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(applicationContext, "Make sure name and password aren't blank!", Toast.LENGTH_LONG).show()

        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() {
                    if (!it.isSuccessful) {
                        Log.d("MainActivity", "Not successful")
                        return@addOnCompleteListener
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Successfully logged in",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }.addOnFailureListener {
                    Log.d("MainActivity", "Failed to log in: ${it.message}")
                    Toast.makeText(applicationContext, "Failed to log in", Toast.LENGTH_LONG).show()
                }
        }
    }
}