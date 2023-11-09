package com.example.uts

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class edit_profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etData1: EditText = findViewById(R.id.profile_name)
        val etData2: EditText = findViewById(R.id.weight)
        val etData3: EditText = findViewById(R.id.weight)
        val btnedit: Button = findViewById(R.id.continueBtn02)

        btnedit.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("Deklan Malik Akbar", etData1.text.toString())
            intent.putExtra("180", etData2.text.toString())
            intent.putExtra("60", etData3.text.toString())
            startActivity(intent)
        }
    }
}
