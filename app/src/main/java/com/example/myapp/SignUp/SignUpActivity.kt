package com.example.myapp.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapp.MainActivity
import com.example.myapp.R

class SignUpActivity : AppCompatActivity() {
    private lateinit var FirstnameEditText: EditText
    private lateinit var LastnameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var ResetButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        FirstnameEditText = findViewById(R.id.firstname)
        LastnameEditText = findViewById(R.id.Lastname)
        passwordEditText = findViewById(R.id.password)
        emailEditText = findViewById(R.id.email)
        signUpButton = findViewById(R.id.submit)
        ResetButton = findViewById(R.id.Reset)

        signUpButton.setOnClickListener { signUp() }
        ResetButton.setOnClickListener {
            FirstnameEditText.setText("")
            LastnameEditText.setText("")
            passwordEditText.setText("")
            emailEditText.setText("")
        }



    }

    private fun signUp() {
            val username = FirstnameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            // Validate the username, password, and email
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Please enter a valid username, password, and email.", Toast.LENGTH_SHORT).show()
                return
            }

            // Check if the username is already taken
            if (checkUsername(username)) {
                Toast.makeText(
                    this@SignUpActivity,
                    "The username '$username' is already taken. Please choose a different username.",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            // Check if the email is already used for another account
            if (checkEmail(email)) {
                Toast.makeText(
                    this@SignUpActivity,
                    "The email '$email' is already used for another account. Please use a different email.",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            // If everything is valid, create the new user account
            createUser(username, password, email)
        val intent = Intent(this@SignUpActivity,MainActivity::class.java)
        startActivity(intent)

        }
    private fun checkUsername(username: String): Boolean {
        // Check if the username already exists in the database
        // Return true if the username exists, false otherwise
        return false}

    private fun checkEmail(email: String): Boolean {
        // Check if the email is already used for another account in the database
        // Return true if the email is used, false otherwise
        return false
    }

    private fun createUser(username: String, password: String, email: String) {
        // Create a new user account with the provided username, password, and email
        // Save the user account to the database

    }
}