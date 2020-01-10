package com.example.rrhh_si2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_appointment.*
import java.util.*

class CreateAppointmentActivity : AppCompatActivity() {

    private var selectedCalendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)

        btnNext.setOnClickListener {
            cvStep1.visibility = View.GONE
            cvStep2.visibility = View.VISIBLE
}
        btnConfirmAppointment.setOnClickListener {
            Toast.makeText(this, "Cita registrada correctamente!", Toast.LENGTH_SHORT).show()
            finish()
        }

        val specialityOptions = arrayOf("Speciality A","Speciality B","Speciality C")
        spinnerSpecialities.adapter =  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, specialityOptions)

        val doctorOptions = arrayOf("Doctor A","Doctor B","Doctor C")
        spinnerDoctors.adapter =  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, doctorOptions)
    }

    fun onClickScheduledDate(v: View?){
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener{datePicker, y, m, d  ->
            //Toast.makeText(this, "$y-$m-$d", Toast.LENGTH_SHORT).show()
            selectedCalendar.set(y, m, d)
            etScheduledDate.setText(
                resources.getString(
                    R.string.date_format,
                    y,
                    m.twoDigits(),
                    d.twoDigits()
                )
            )
            displayRadioButtons()
        }

        val datePickerDialog =  DatePickerDialog(this, listener, year, month, dayOfMonth)
        val datePicker = datePickerDialog.datePicker

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        datePicker.minDate = calendar.timeInMillis   //+1
        calendar.add(Calendar.DAY_OF_MONTH, 29)
        datePicker.maxDate = calendar.timeInMillis  //+30nov

        //show dialog
        datePickerDialog.show()
    }

    private fun displayRadioButtons(){
        /*radioGroup.clearCheck()
        radioGroup.removeAllViews()
        radioGroup.checkedRadioButtonId*/
        selectedRadioButton = null
        radioGroupLeft.removeAllViews()
        radioGroupRight.removeAllViews()



        val hours = arrayListOf("3:00 pm","3:30 pm","4:00 pm","3:30 pm")

        var goToLeft = true

        hours.forEach{
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            radioButton.setOnClickListener{view ->
                selectedRadioButton?.isChecked = false

                selectedRadioButton = view as RadioButton?
                selectedRadioButton?.isChecked = true

            }
            if(goToLeft)
                radioGroupLeft.addView(radioButton)
            else
                radioGroupRight.addView(radioButton)
            goToLeft = !goToLeft
        }


    }


    private fun Int.twoDigits() = if(this<=9) "0$this" else this.toString()
}