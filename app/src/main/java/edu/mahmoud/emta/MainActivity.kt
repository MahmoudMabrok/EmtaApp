package edu.mahmoud.emta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.mahmoud.emta.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
    }
}