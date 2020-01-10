package com.example.rrhh_si2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.example.rrhh_si2.PreferenceHelper.get
import com.example.rrhh_si2.PreferenceHelper.set
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val snackBar by lazy {
        Snackbar.make(mainLayout, R.string.press_back_again, Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //controlamos la sesion
        // shared preferences   alternativa de variable o datos puntuales, token
        // sqlite
        // files
        /*val preferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val session = preferences.getBoolean("session", false)
        */

        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["session", false])
            gotToMenuActivity()


        btnLogin.setOnClickListener {
            //validamos el usuario
            createSessionPreference()
            gotToMenuActivity()
        }


        tvGoToRegister.setOnClickListener{
            Toast.makeText(this,getString(R.string.please_fill_your_data),Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gotToMenuActivity(){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun createSessionPreference(){
        /*val preferences = getSharedPreferences("general",Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putBoolean("session", true)
        editor.apply()*/

        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true

    }

    override fun onBackPressed() {
        if (snackBar.isShown)
            super.onBackPressed()
        else
            snackBar.show()
    }
}
