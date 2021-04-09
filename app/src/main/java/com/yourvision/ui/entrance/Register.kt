package com.yourvision.ui.entrance

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.yourvision.R
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

        initialization()
    }

    private fun initialization() {
        binding.registerBtn.setOnClickListener(this::onRegisterClick)
        binding.logInBtn.setOnClickListener(this::onLogInClick)

        setEditTextListeners()
    }

    private fun setEditTextListeners() {
        setEditTextListener(binding.editTextUserName, binding.strokeUserName)
        setEditTextListener(binding.editTextEmail, binding.strokeEmail)
        setEditTextListener(binding.editTextPassword, binding.strokePassword)
        setEditTextListener(binding.editTextConfirmPassword, binding.strokeConfirmPassword)
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

    private fun emptyFieldsCheck() : Boolean {
        return isEmpty(binding.editTextUserName, binding.strokeUserName) or
                isEmpty(binding.editTextEmail, binding.strokeEmail) or
                isEmpty(binding.editTextPassword, binding.strokePassword) or
                isEmpty(binding.editTextConfirmPassword, binding.strokeConfirmPassword)
    }

    private fun isEmpty(editText: EditText, stroke: FrameLayout) : Boolean {
        if (editText.text.isEmpty()) {
            stroke.setBackgroundResource(R.drawable.stroke_input_field_error)
            return true
        }
        return false
    }

    private fun onRegisterClick(view: View) {
        closeKeyboard()
        if (!emptyFieldsCheck() && isEqualsFields()) {
            //TODO("Check fields and open introduction activity")
        }
    }

    private fun onLogInClick(view: View) {
        closeKeyboard()
        super.onBackPressed()
    }

    private fun isEqualsFields() : Boolean =
        if (binding.editTextPassword.text.toString() == binding.editTextConfirmPassword.text.toString()) {
            true
        } else {
            binding
                .strokeConfirmPassword.setBackgroundResource(R.drawable.stroke_input_field_error)
            Toast.makeText(this,
                resources.getText(R.string.different_passwords),
                Toast.LENGTH_SHORT).show()
            false
        }

    private fun closeKeyboard() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextUserName.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.editTextEmail.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.editTextPassword.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.editTextConfirmPassword.windowToken, 0)
    }
}