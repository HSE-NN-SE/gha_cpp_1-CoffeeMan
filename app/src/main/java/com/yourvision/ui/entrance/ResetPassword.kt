package com.yourvision.ui.entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.yourvision.R

class ResetPassword : AppCompatActivity() {
    private val passwordField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_password)
    }

    private val confirmField: EditText by lazy {
        findViewById<EditText>(R.id.edit_text_confirm_password)
    }

    private val confirmBtn: Button by lazy {
        findViewById<Button>(R.id.confirm_btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_reset_password)
        supportActionBar?.hide()

        confirmBtn.setOnClickListener(this::onConfirmBtnClick)
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