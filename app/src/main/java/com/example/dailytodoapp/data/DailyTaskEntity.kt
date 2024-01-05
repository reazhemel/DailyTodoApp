package com.example.dailytodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_task_table")
class DailyTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskName: String,
    val isTaskDone: Boolean
)