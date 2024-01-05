package com.example.dailytodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTaskDao {

    @Insert
    suspend fun insertDailyTask(dailyTaskEntity: DailyTaskEntity)

    @Update
    suspend fun updateDailyTask(dailyTaskEntity: DailyTaskEntity)

    @Query("SELECT * FROM daily_task_table")
    fun getDailyTaskList():Flow<List<DailyTaskEntity>>
}