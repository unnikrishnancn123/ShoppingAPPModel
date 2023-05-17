package com.example.myapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.myapp.home.HomeActivity
import com.example.myapp.SignUp.SignUpActivity


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginForm = findViewById<LinearLayout>(R.id.login_form)

        val slideInFromBottom = AnimationUtils.loadAnimation(this, R.anim.slide)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        var email = findViewById<LinearLayout>(R.id.email) as EditText
        var password = findViewById<LinearLayout>(R.id.password) as EditText
        var loginBtn = findViewById<LinearLayout>(R.id.login) as Button
        var signUp = findViewById<LinearLayout>(R.id.signup) as Button
        var forgot = findViewById<LinearLayout>(R.id.forgitpassword) as TextView
        var signin_G = findViewById<LinearLayout>(R.id.signInButton) as TextView
        var rememberCheckBox = findViewById<LinearLayout>(R.id.remember) as CheckBox


        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val savedUsername = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val rememberLogin = sharedPreferences.getBoolean("remember_login", false)

        email.setText(savedUsername)
        password.setText(savedPassword)
        rememberCheckBox.isChecked = rememberLogin



        loginForm.startAnimation(slideInFromBottom)

        Handler().postDelayed({
            loginForm.startAnimation(fadeIn)
        }, 500)

        loginBtn.setOnClickListener {
            val username = "unni@gmail.com"
            val password1 = "unni"

            email.setText(username)
            password.setText(password1)
//            val username= email.text
//            var pass =password.text
            /* if (rememberCheckBox.isChecked) {
                val editor = sharedPreferences.edit()
                editor.putString("email", username)
                editor.putString("password", password1)
                editor.putBoolean("remember_login", true)
                editor.apply()
            } else {
                val editor = sharedPreferences.edit()
                editor.remove("email")
                editor.remove("password")
                editor.remove("remember_login")
                editor.apply()
            }*/
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
        }
        signUp.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        forgot.setOnClickListener {
            Toast.makeText(this@MainActivity, "rest", Toast.LENGTH_LONG).show()
        }
        signin_G.setOnClickListener {
            Toast.makeText(this@MainActivity, "test", Toast.LENGTH_LONG).show()


        }

    }
}

