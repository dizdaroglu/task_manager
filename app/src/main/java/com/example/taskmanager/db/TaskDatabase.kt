package com.example.taskmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanager.model.Task

@Database(entities = [Task::class],version = 1)
abstract class TaskDatabase:RoomDatabase() {

    abstract  fun getTaskDao() : TaskDAO

    companion object {
        @Volatile private var instance : TaskDatabase ? = null

        private  val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun  buildDatabase (context: Context)= Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "taskdatabase"
        ).build()
    }


}