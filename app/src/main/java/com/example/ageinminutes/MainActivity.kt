package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener { view ->
            //show date picker dialog
            showDatePicker(view)
        }
    }

    private fun showDatePicker(view: View) {
        //get a Calendar with current date and time to use to initialize the date picker
        val cal = java.util.Calendar.getInstance()
        val day = cal.get(java.util.Calendar.DAY_OF_MONTH)
        val month = cal.get(java.util.Calendar.MONTH)
        val year = cal.get(java.util.Calendar.YEAR)

        //initialize the DatePickerDialog
      val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedmonth+1}/$selectedyear"
                binding.selectedDateTxt.text = selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val dateInMins = theDate!!.time/60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMins = currentDate!!.time/60000

                val differenceInMinutes = currentDateToMins - dateInMins

                binding.ageInMinutesTxt.text = differenceInMinutes.toString()

            }, year, month, day
        )
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}