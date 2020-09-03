package com.example.taskmanager.db

import androidx.room.*
import com.example.taskmanager.model.Task

@Dao
interface TaskDAO {

    @Insert
    suspend fun addTask(task : Task)

    @Query("SELECT * FROM task ORDER BY uuid DESC")
    suspend fun getAllTask() : List<Task>

    @Query("SELECT * FROM task WHERE  uuid = :taskId")
    suspend fun getTask(taskId: Int) : Task

    @Query("DELETE FROM task")
    suspend fun deleteAllTask()

    @Query("UPDATE task SET title = :newTitle , description = :newDescription, selectItem = :item WHERE uuid = :id")
    suspend fun updateTask(newTitle:String,newDescription:String,item:String,id:Int)

    @Query("DELETE FROM task WHERE uuid = :id")
    suspend fun deleteTask(id:Int)




}