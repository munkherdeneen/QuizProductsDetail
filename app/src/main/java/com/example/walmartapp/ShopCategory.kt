package com.example.walmartapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.shop_category.*

class ShopCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_category)
        supportActionBar?.title = "Shop by Category"

        var uname = intent.getStringExtra("username")
        statustext.text = uname
    }

    private fun alert(text: String) {
        Toast.makeText(applicationContext, "You have chosen the $text category of shopping", Toast.LENGTH_SHORT).show()
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.elecimg -> alert(getString(R.string.string_elec))
            R.id.cloimg -> alert(getString(R.string.string_clothing))
            R.id.beaimg -> alert(getString(R.string.string_beauty))
            R.id.food -> alert(getString(R.string.string_food))
            else -> alert("Nothing")
        }
    }
}
