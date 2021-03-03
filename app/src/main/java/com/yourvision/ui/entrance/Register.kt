package com.yourvision.ui.entrance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.yourvision.R

class Register : AppCompatActivity() {
    private val userNameField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_user_name)
    }
    private val emailField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_email)
    }
    private val passwordField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_password)
    }
    private val confirmPasswordField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_confirm_password)
    }

    private val registerBtn: Button by lazy {
        findViewById<Button>(R.id.register_btn)
    }
    private val logInBtn: Button by lazy {
        findViewById<Button>(R.id.log_in_btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        buttonInit()
    }

    private fun buttonInit() {
        registerBtn.setOnClickListener(this::onRegisterClick)

        logInBtn.setOnClickListener(this::onLogInClick)
    }

    private fun onRegisterClick(view: View) {
        //TODO("Check fields and open introduction activity")
    }

    private fun onLogInClick(view: View) {
        super.onBackPressed()
    }
}