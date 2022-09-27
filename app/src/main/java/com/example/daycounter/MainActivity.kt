package com.example.daycounter

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnEnd = findViewById<Button>(R.id.btnEnd)

        val today = GregorianCalendar()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DATE)

        var tvStart = findViewById<TextView>(R.id.tvStart)
        var tvEnd = findViewById<TextView>(R.id.tvEnd)

        tvStart.setText("from ${year}.${month+1}.${day}")
        tvEnd.setText("to ${year}.${month+1}.${day}")

        val calStart = Calendar.getInstance()
        val calEnd = Calendar.getInstance()

        btnStart.setOnClickListener {

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    tvStart.setText("from ${year}.${month+1}.${dayOfMonth}")
                    calStart.set(year, month+1, dayOfMonth)
                }
            }, year, month, day)
            dlg.show()
        }

        btnEnd.setOnClickListener {

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    tvEnd.setText("to ${year}.${month+1}.${dayOfMonth}")
                    calEnd.set(year, month+1, dayOfMonth)

                    findViewById<TextView>(R.id.tvDay).setText(
                        (TimeUnit.MILLISECONDS.toDays(calEnd.timeInMillis - calStart.timeInMillis)+1).toString()
                    )
                }
            }, year, month, day)
            dlg.show()
        }
    }
}