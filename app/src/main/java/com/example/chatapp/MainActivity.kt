package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class MainActivity : AppCompatActivity() {
    var haveAccountTextView: TextView? = null
    var registerButton: Button? = null
    var emailTextview: TextView? = null
    var passwordTextview: TextView? = null
    var usernameTextview: TextView? = null


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

    private fun register() {
        val email = findViewById<TextView>(R.id.register_email).text.toString()
        val password = findViewById<TextView>(R.id.register_password).text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(
                applicationContext,
                "Make sure name and password aren't blank!",
                Toast.LENGTH_LONG
            ).show()

        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Log.d("MainActivity", "NOOOOOOOOOOOO")
                        return@addOnCompleteListener
                    } else {
                        saveUserToDatabase()
                        Toast.makeText(
                            applicationContext,
                            "Successfully created user",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }.addOnFailureListener {
                    Log.d("MainActivity", "Failed to create user: ${it.message}")
                    Toast.makeText(applicationContext, "Failed to register: ${it.message}", Toast.LENGTH_LONG).show()

                }


        }
    }

    private fun saveUserToDatabase(){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, findViewById<TextView>(R.id.register_username).text.toString())
        ref.setValue(user).addOnSuccessListener {
            Log.d("MainActivity", "Saved user to firebase database")

            val intent = Intent(this, LatestMessageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}

@Parcelize
class User(val uid: String, val username: String): Parcelable{
    constructor() : this("", "")
}