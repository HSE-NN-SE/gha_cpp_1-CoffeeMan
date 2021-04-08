package com.yourvision.ui.entrance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.yourvision.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(binding.root)

        supportActionBar?.hide()

        buttonInit()
    }

    private fun buttonInit() {
        binding.registerBtn.setOnClickListener(this::onRegisterClick)
        binding.logInBtn.setOnClickListener(this::onLogInClick)
    }

    private fun onRegisterClick(view: View) {
        //TODO("Check fields and open introduction activity")
    }

    private fun onLogInClick(view: View) {
        super.onBackPressed()
    }
}