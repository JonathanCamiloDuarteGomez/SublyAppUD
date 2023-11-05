package com.example.sublyappv04

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class VerificacionActivity : AppCompatActivity() {
    private lateinit var EmailViewText: TextView
    private lateinit var passwordViewText: TextView
    private lateinit var btnLogOutButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        println(" showHome 3")
        EmailViewText = findViewById(R.id.emailTextView)
        passwordViewText = findViewById(R.id.proveedorTextView)
        btnLogOutButton = findViewById(R.id.logOutButton)

        //Setup
        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        val provider:String? = bundle?.getString("provider")
        setup(email ?:"",provider ?:"")
    }

    private fun setup(email:String, provider: String){

        title = "Inicio "
        EmailViewText.text = email
        passwordViewText.text = provider

        btnLogOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}
