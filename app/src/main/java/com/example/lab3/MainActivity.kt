package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //при создании
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //объявление фрагмента
        val mainFragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, mainFragment)
            .commitNow()
    }
}
