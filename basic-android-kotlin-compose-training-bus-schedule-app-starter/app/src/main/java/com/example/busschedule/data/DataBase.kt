package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BusSchedule::class), version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun busDao(): BusDao

    companion object{
        @Volatile
        private var Instancia: DataBase? = null

        fun getDatabase(context: Context): DataBase{
            return Instancia ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    DataBase::class.java,
                    "database"
                ).createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instancia = it
                    }
            }
        }
    }
}