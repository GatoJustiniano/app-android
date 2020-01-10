package com.example.rrhh_si2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rrhh_si2.model.Appointment
import kotlinx.android.synthetic.main.activity_appointments.*

class AppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(1,"Medico a", "12/01/2020","3:00 pm")
        )
        appointments.add(
            Appointment(2,"Medico b", "15/01/2020","4:00 pm")
        )
        appointments.add(
            Appointment(3,"Medico c", "16/01/2020","5:00 pm")
        )

        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter = AppointmentAdapter(appointments)

    }
}
