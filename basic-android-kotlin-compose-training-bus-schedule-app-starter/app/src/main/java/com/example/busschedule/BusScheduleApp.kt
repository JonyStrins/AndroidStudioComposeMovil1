package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.DataBase

class BusScheduleApp: Application() {
    val database: DataBase by lazy { DataBase.getDatabase(this) }
}