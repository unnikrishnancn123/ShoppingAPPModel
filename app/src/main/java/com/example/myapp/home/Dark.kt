package com.example.myapp.home

import android.app.ActionBar
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.example.myapp.R


class Dark : AppCompatActivity() {
    private val MyPREFERENCES = "nightModePrefs"
    private val KEY_ISNIGHTMODE = "isNightMode"
    private lateinit var sharedpreferences: SharedPreferences
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark)
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        radioGroup = findViewById(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.day_mode -> {
                    // Light mode selected
                    setLightMode()
                }

                R.id.night_mode -> {
                    // Dark mode selected
                    setDarkMode()
                }

                R.id.system_default -> {
                    // System default mode selected
                    setDefaultMode()
                }
            }
        }

        // Retrieve the saved mode from SharedPreferences
        val savedNightMode = sharedpreferences.getBoolean(KEY_ISNIGHTMODE, false)

        if (savedNightMode) {
            // Dark mode is saved
            setDarkMode()
            radioGroup.check(R.id.night_mode)
        } else if(!savedNightMode){
            // Light mode is saved or no mode is saved
            setLightMode()
            radioGroup.check(R.id.day_mode)
        }else {
            // Light mode is saved or no mode is saved
           setDefaultMode()
            radioGroup.check(R.id.system_default)
        }
    }

    private fun setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        saveNightModeState(false)
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        saveNightModeState(true)
    }

    private fun setDefaultMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        saveNightModeState(false)
    }

    private fun saveNightModeState(nightMode: Boolean) {
        val editor = sharedpreferences.edit()
        editor.putBoolean(KEY_ISNIGHTMODE, nightMode)
        editor.apply()
    }
}













