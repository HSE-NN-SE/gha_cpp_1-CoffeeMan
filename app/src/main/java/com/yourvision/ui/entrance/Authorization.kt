package com.yourvision.ui.entrance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.yourvision.R
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

        initialization()
    }

    private fun initialization() {
        binding.registerBtn.setOnClickListener(this::onRegisterClick)
        binding.logInBtn.setOnClickListener(this::onLogInClick)
        binding.resetBtn.setOnClickListener(this::onResetPasswordClick)
        binding.googleAuthorizationBtn.setOnClickListener(this::onGoogleAuthorizationClick)
        binding.vkAuthorizationBtn.setOnClickListener(this::onVKAuthorizationClick)
        binding.instAuthorizationBtn.setOnClickListener(this::onInstAuthorizationClick)

        setEditTextListeners()
    }

    private fun setEditTextListeners() {
        setEditTextListener(binding.editTextUserName, binding.strokeUserName)
        setEditTextListener(binding.editTextPassword, binding.strokePassword)
    }

    private fun emptyFieldsCheck() : Boolean {
        return isEmpty(binding.editTextUserName, binding.strokeUserName) or
                isEmpty(binding.editTextPassword, binding.strokePassword)
    }

    private fun setEditTextListener(editText: EditText, stroke: FrameLayout) {
        editText.addTextChangedListener(object : TextWatcher {
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

    private fun onLogInClick(view: View) {
        closeKeyboard()
        if (!emptyFieldsCheck()) {
            //TODO("Check fields and open intro or main activity")
        }
    }

    private fun onResetPasswordClick(view: View) {
        closeKeyboard()
        val intent = Intent(this, ConfirmEmail::class.java)
        startActivity(intent)
    }

    private fun onRegisterClick(view: View) {
        closeKeyboard()
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun closeKeyboard() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextUserName.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.editTextPassword.windowToken, 0)
    }

    private fun onGoogleAuthorizationClick(view: View) {
        closeKeyboard()
        //TODO("Open authorization fragment")
    }

    private fun onVKAuthorizationClick(view: View) {
        closeKeyboard()
        //TODO("Open authorization fragment")
    }

    private fun onInstAuthorizationClick(view: View) {
        closeKeyboard()
        //TODO("Open authorization fragment")
    }
}
