package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.yourvision.databinding.ActivityResetPasswordBinding

class ResetPassword : AppCompatActivity() {

    private val binding: ActivityResetPasswordBinding by lazy {
        ActivityResetPasswordBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.confirmBtn.setOnClickListener(this::onConfirmBtnClick)
    }

    private fun onConfirmBtnClick(view: View) {
        //TODO check fields and open Authorization activity
        val intent = Intent(this, Authorization::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, Authorization::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}