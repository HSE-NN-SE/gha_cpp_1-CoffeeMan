package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.yourvision.R
import com.yourvision.ui.utilities.ScaleAnimator
import com.yourvision.ui.utilities.ViewTimer

class ConfirmEmail4PasswordReset : AppCompatActivity() {
    private var isResend = false
    private var isReset = false
    private val timer = ViewTimer()
    private val animator = ScaleAnimator()

    private val emailField by lazy {
        findViewById<EditText>(R.id.edit_text_email)
    }
    private val codeField by lazy {
        findViewById<EditText>(R.id.edit_text_code)
    }

    private val sendBtn by lazy {
        findViewById<Button>(R.id.send_btn)
    }
    private val confirmBtn by lazy {
        findViewById<Button>(R.id.confirm_btn);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_confirm_email)
        supportActionBar?.hide()

        buttonInit()
    }

    override fun onPause() {
        super.onPause()
        animator.stop()
        timer.stop()
    }

    private fun buttonInit() {
        sendBtn.setOnClickListener(this::onSendCodeClick)
        confirmBtn.setOnClickListener(this::onConfirmClick)
    }

    private fun onSendCodeClick(view: View) {
        if (!isResend) {
            //TODO Check fields and send code

            val button = (view as? Button)
            button?.isClickable = false

            confirmBtn.visibility = Button.VISIBLE
            confirmBtn.isClickable = false

            animator.initAndStart(
                confirmBtn,
                button?.width ?: 0,
                button?.height ?: 0,
                speed = 0.15,
                delay = 100
            ) {
                confirmBtn.setText(R.string.confirm_btn_str)
                confirmBtn.isClickable = true
                timer.initAndStart(15, button) {
                    button?.setText(R.string.resend_btn_str)
                    button?.isClickable = true
                }
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
}