package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.yourvision.R

class Authorization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_authorization)

        supportActionBar?.hide()

        val registerBtn = findViewById<Button>(R.id.register_btn)
        registerBtn.setOnClickListener(this::onRegisterClick)
    }

    private fun onSingInClick(view: View) {
        TODO("Check fields and open intro or main activity")
    }

    private fun onResetPasswordClick(view: View) {
        TODO("Open reset password activity")
    }

    private fun onRegisterClick(view: View) {
        val intent = Intent(this, Register::class.java)
        Log.e("reg", "try to start")
        startActivity(intent)
    }

    private fun onGoogleAuthorizationClick(view: View) {
        TODO("Open authorization fragment")
    }

    private fun onVKAuthorizationClick(view: View) {
        TODO("Open authorization fragment")
    }

    private fun onInstagramAuthorizationClick(view: View) {
        TODO("Open authorization fragment")
    }
}
