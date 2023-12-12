package com.example.lab3
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.lab3.R

class SecondActivity : AppCompatActivity() {
    //при создании
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val counterValue = intent.getIntExtra("counter_value", 0)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val backButton: Button = findViewById(R.id.backButton)

        resultTextView.text = "Итого: $counterValue"
        backButton.setOnClickListener { goToFirstActivity() }
    }
    //переход на первую активность
    private fun goToFirstActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}