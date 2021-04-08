package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.yourvision.databinding.ActivityAuthorizationBinding

class Authorization : AppCompatActivity() {

    private val binding: ActivityAuthorizationBinding by lazy {
        ActivityAuthorizationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(binding.root)

        supportActionBar?.hide()

        buttonsInit()
    }

    private fun buttonsInit() {
        binding.registerBtn.setOnClickListener(this::onRegisterClick)
        binding.logInBtn.setOnClickListener(this::onLogInClick)
        binding.resetBtn.setOnClickListener(this::onResetPasswordClick)
        binding.googleAuthorizationBtn.setOnClickListener(this::onGoogleAuthorizationClick)
        binding.vkAuthorizationBtn.setOnClickListener(this::onVKAuthorizationClick)
        binding.instAuthorizationBtn.setOnClickListener(this::onInstAuthorizationClick)
    }

    private fun onLogInClick(view: View) {
        //TODO("Check fields and open intro or main activity")
    }

    private fun onResetPasswordClick(view: View) {
        val intent = Intent(this, ConfirmEmail::class.java)
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
