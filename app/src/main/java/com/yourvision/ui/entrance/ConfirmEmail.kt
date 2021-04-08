package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.yourvision.R
import com.yourvision.databinding.ActivityConfirmEmailBinding
import com.yourvision.ui.utilities.ScaleAnimator
import com.yourvision.ui.utilities.ViewTimer

class ConfirmEmail : AppCompatActivity() {
    private var isResend = false
    private val timer = ViewTimer()
    private val animator = ScaleAnimator()

    private val binding: ActivityConfirmEmailBinding by lazy {
        ActivityConfirmEmailBinding.inflate(layoutInflater)
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

    override fun onPause() {
        super.onPause()
        animator.stop()
        timer.stop()
    }

    private fun buttonInit() {
        binding.sendBtn.setOnClickListener(this::onSendCodeClick)
        binding.confirmBtn.setOnClickListener(this::onConfirmClick)
    }

    private fun onSendCodeClick(view: View) {
        if (!isResend) {
            //TODO Check fields and send code

            if (view is Button) {
                startTimer(view)
            }
            isResend = true
        } else {
            //TODO Resend code
        }
    }

    private fun onConfirmClick(view: View) {
        //TODO Check fields and open Reset Password activity
        timer.stop()
        val intent = Intent(this, ResetPassword::class.java)
        startActivity(intent)
    }

    private fun startTimer(button: Button) {
        button.isClickable = false

        binding.confirmBtn.visibility = Button.VISIBLE
        binding.confirmBtn.isClickable = false

        animator.initAndStart(
            binding.confirmBtn,
            button.width,
            button.height,
            speed = 0.17,
            delay = 100
        ) {
            timer.initAndStart(15, button) {
                button.setText(R.string.resend_btn_str)
                button.isClickable = true
            }
            binding.confirmBtn.setText(R.string.confirm_btn_str)
            binding.confirmBtn.isClickable = true
        }
    }
}