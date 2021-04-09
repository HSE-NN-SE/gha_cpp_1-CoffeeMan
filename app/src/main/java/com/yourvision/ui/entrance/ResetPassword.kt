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
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.yourvision.R
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

        initialization()
    }

    private fun initialization() {
        binding.confirmBtn.setOnClickListener(this::onConfirmBtnClick)
        setEditTextListeners()
    }

    private fun setEditTextListeners() {
        setEditTextListener(binding.editTextPassword, binding.strokePassword)
        setEditTextListener(binding.editTextConfirmPassword, binding.strokeConfirmPassword)
    }

    private fun emptyFieldsCheck() : Boolean {
        return isEmpty(binding.editTextPassword, binding.strokePassword) or
                isEmpty(binding.editTextConfirmPassword, binding.strokeConfirmPassword)
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

    private fun onConfirmBtnClick(view: View) {
        closeKeyboard()
        if(!emptyFieldsCheck() && isEqualsFields()) {
            //TODO check fields and open Authorization activity
            val intent = Intent(this, Authorization::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun closeKeyboard() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextPassword.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.editTextConfirmPassword.windowToken, 0)
    }

    override fun onBackPressed() {
        closeKeyboard()
        val intent = Intent(this, Authorization::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}