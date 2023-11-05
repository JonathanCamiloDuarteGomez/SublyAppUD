package com.example.sublyappv04

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import com.google.firebase.auth.FirebaseAuth


class AuthActivity : AppCompatActivity() {
    private lateinit var btnSignUp: Button
    private lateinit var btnlogInButton: Button
    private lateinit var txtEmailEditText: EditText
    private lateinit var txtpasswordEditText: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        btnSignUp = findViewById(R.id.signUpButton)
        btnlogInButton = findViewById(R.id.logInbutton)
        txtEmailEditText = findViewById(R.id.emailEditText)
        txtpasswordEditText = findViewById(R.id.passwordEditText)
        // Setup
        setup()
    }

    private fun setup() {
        title = "Autenticación"

        btnSignUp.setOnClickListener {
            if (txtEmailEditText.text.isNotEmpty() &&  txtpasswordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(txtEmailEditText.text.toString(),  txtpasswordEditText.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            println(" showHome 001")
                            showHome(task.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        btnlogInButton .setOnClickListener {
            if (txtEmailEditText.text.isNotEmpty() &&  txtpasswordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(txtEmailEditText.text.toString(),  txtpasswordEditText.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            println(" showHome 0")
                            showHome(task.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autenticación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {

        val homeIntent = Intent(this, MainActivity::class.java).apply {
            println(" showHome 1")
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}
