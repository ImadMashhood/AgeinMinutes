package us.rushbmarketing.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateText: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    private var tvAgeInMinutesText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateText = findViewById(R.id.tvSelectedDateText)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInMinutesText = findViewById(R.id.tvAgeInMinutesText)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                        val selectedDate = "${selectedMonth+1}/$selectedDay/$selectedYear"
                        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                        val theDate = sdf.parse(selectedDate)
                        theDate?.let {
                            val selectedDateInMinutes = theDate.time/60000
                            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                            currentDate?.let {
                                val currentDateInMinutes = currentDate.time/60000
                                val ageInMinutes = currentDateInMinutes-selectedDateInMinutes
                                tvSelectedDate?.text = selectedDate
                                tvSelectedDateText?.text = "Selected Date"
                                tvAgeInMinutes?.text = ageInMinutes.toString()
                                tvAgeInMinutesText?.text = "Age in Minutes"
                            }
                        }
                    }, year, month,day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }


}