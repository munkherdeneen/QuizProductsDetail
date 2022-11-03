package com.example.walmartapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()  {
    private var user: User = User("", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        button_register.setOnClickListener {
            if (checkValidation()) {
                Toast.makeText(applicationContext, "Account created successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent()
//                intent.putExtra("user", user)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun setValues() {
        user.username = username.text.toString()
        user.lastname = lastname.text.toString()
        user.password = password.text.toString()
        user.firstname = firstname.text.toString()
    }

    private fun checkValidation(): Boolean {
        setValues()
        if (user.firstname.isEmpty() || user.lastname.isEmpty() || user.username.isEmpty() || user.password.isEmpty()) {
            Toast.makeText(applicationContext, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
