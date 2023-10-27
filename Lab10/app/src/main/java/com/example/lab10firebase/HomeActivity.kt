package com.example.lab10firebase

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType {
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

private lateinit var emailTextView: TextView
private lateinit var providerTextView: TextView
private lateinit var logOutButton: Button
private lateinit var email: String
private lateinit var provider: String
private lateinit var authLayout: androidx.constraintlayout.widget.ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        emailTextView = findViewById(R.id.emailTextView)
        providerTextView = findViewById(R.id.providerTextView)
        logOutButton = findViewById(R.id.logOutButton)

        // Setup
        val bundle = intent.extras
        email = bundle?.getString("email") ?: ""
        provider = bundle?.getString("provider") ?: ""
        setup(email, provider)

        // Guardar datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(s: String, s1: String) {
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {

            //Borrar datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()

            onBackPressed()
        }
    }
}