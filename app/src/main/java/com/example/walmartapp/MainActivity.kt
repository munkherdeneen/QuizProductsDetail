package com.example.walmartapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        var user1 = User("Jon", "Catania", "jon@gmail.com", "passjon")
        var user2 = User("Lebron", "James", "james@hotmail.com", "passjames")
        var user3 = User("Carl", "Smith", "smith@gmail.com", "passsmith")
        var user4 = User("Aaron", "Giff", "aaron@gmail.com", "passaaron")
        var user5 = User("Murali", "Dunlap", "murali@miu.edu", "passmurali")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)

        loginbutton.setOnClickListener {
            if(checkLogin(emailtextbox.text.toString(), passtextbox.text.toString())) {
                val shoppingIntent = Intent(this, ShopCategory::class.java)
                shoppingIntent.putExtra("username", emailtextbox.text.toString())
                startActivity(shoppingIntent)
            }
            else {
                Toast.makeText(applicationContext, "Username or Password NOT matching", Toast.LENGTH_LONG).show()
            }
        }

        createaccbutton.setOnClickListener {
            val signUpIntent = Intent(this, RegisterActivity::class.java)
            getLaunch.launch(signUpIntent)
        }

        forgottext.setOnClickListener {
            forgotPassword()
        }
    }

    private fun checkLogin(username: String, password: String): Boolean {
        for (user in users) {
            if (user.username == username && user.password == password) {
                return true
            }
        }
        return false
    }

    private var getLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val sUser = data!!.getSerializableExtra("user")
            val newUser = sUser as User

            users.add(newUser)
        }
    }

    private fun findByUserEmail(email: String): User {
        for (user in users) {
            if (user.username == email) {
                return user
            }
        }
        return User("","","", "")
    }

    private fun forgotPassword() {
        val userMail = emailtextbox.text.toString()
        val user = findByUserEmail(userMail)

        if(user.password.isNotEmpty()) {
            val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            sendIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(userMail))
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Reset password")
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Your password is: ${user.password}")
            startActivity(Intent.createChooser(sendIntent, "Sending email: "))
        }
        else {
            Toast.makeText(applicationContext, "Can not found user with email: $userMail", Toast.LENGTH_LONG).show()
        }
    }
}