package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.yourvision.R

class Authorization : AppCompatActivity() {

    private val registerBtn: Button by lazy {
        findViewById<Button>(R.id.register_btn)
    }
    private val logInBtn: Button by lazy {
        findViewById<Button>(R.id.log_in_btn)
    }
    private val resetPasswordBtn: Button by lazy {
        findViewById<Button>(R.id.reset_btn)
    }
    private val googleAuthorizationBtn: Button by lazy {
        findViewById<Button>(R.id.google_authorization_btn)
    }
    private val vkAuthorizationBtn: Button by lazy {
        findViewById<Button>(R.id.vk_authorization_btn)
    }
    private val instAuthorizationBtn: Button by lazy {
        findViewById<Button>(R.id.inst_authorization_btn)
    }

    private val userNameField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_user_name)
    }
    private val passwordField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_password)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_authorization)

        supportActionBar?.hide()

        buttonsInit()
    }

    private fun buttonsInit() {
        registerBtn.setOnClickListener(this::onRegisterClick)

        logInBtn.setOnClickListener(this::onLogInClick)

        resetPasswordBtn.setOnClickListener(this::onResetPasswordClick)

        googleAuthorizationBtn.setOnClickListener(this::onGoogleAuthorizationClick)

        vkAuthorizationBtn.setOnClickListener(this::onVKAuthorizationClick)

        instAuthorizationBtn.setOnClickListener(this::onInstAuthorizationClick)
    }

    private fun onLogInClick(view: View) {
        //TODO("Check fields and open intro or main activity")
    }

    private fun onResetPasswordClick(view: View) {
        val intent = Intent(this, ConfirmEmail4PasswordReset::class.java)
        startActivity(intent)
    }

    private fun onRegisterClick(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun onGoogleAuthorizationClick(view: View) {
        //TODO("Open authorization fragment")
    }

    private fun onVKAuthorizationClick(view: View) {
        //TODO("Open authorization fragment")
    }

    private fun onInstAuthorizationClick(view: View) {
        //TODO("Open authorization fragment")
    }
}
