package com.example.taskmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name="selectItem")
    val selectItem:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}