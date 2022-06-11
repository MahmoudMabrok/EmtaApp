package edu.mahmoud.emta

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edDate by lazy { findViewById<EditText>(R.id.edDate) }
        val tvResult by lazy { findViewById<TextView>(R.id.tvResult) }


        findViewById<Button>(R.id.button).setOnClickListener {
            val dateString = edDate.text.toString()
            val dateObject = DateOperation.strToDate(dateString)
            dateObject?.let {
                val diff = DateOperation.getDifferenceTillNow(it)
                tvResult.text = "$diff days"
            }
        }


    }
}