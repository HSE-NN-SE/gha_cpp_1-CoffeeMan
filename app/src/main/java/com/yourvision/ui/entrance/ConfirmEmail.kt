package com.yourvision.ui.entrance

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
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

        initialization()
    }

    override fun onPause() {
        super.onPause()
        animator.stop()
        timer.stop()
    }

    private fun initialization() {
        binding.sendBtn.setOnClickListener(this::onSendCodeClick)
        binding.confirmBtn.setOnClickListener(this::onConfirmClick)

        setEditTextListeners()
    }

    private fun setEditTextListeners() {
        setEditTextListener(binding.editTextEmail, binding.strokeEmail)
        setEditTextListener(binding.editTextCode, binding.strokeCode)
    }

    private fun setEditTextListener(editText: EditText, stroke: FrameLayout) {
        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                stroke.setBackgroundResource(R.drawable.stroke_input_field)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun isEmpty(editText: EditText, stroke: FrameLayout) : Boolean {
        if (editText.text.isEmpty()) {
            stroke.setBackgroundResource(R.drawable.stroke_input_field_error)
            return true
        }
        return false
    }

    private fun onSendCodeClick(view: View) {
        closeKeyboard()
        if (!isEmpty(binding.editTextEmail, binding.strokeEmail)) {
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
    }

    private fun closeKeyboard() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextCode.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.editTextEmail.windowToken, 0)
    }

    private fun onConfirmClick(view: View) {
        closeKeyboard()
        if (!isEmpty(binding.editTextCode, binding.strokeCode)) {
            //TODO Check fields and open Reset Password activity
            timer.stop()
            val intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
        }
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