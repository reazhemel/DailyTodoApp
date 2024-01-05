package com.example.dailytodoapp.di

import android.app.Application
import androidx.room.Room
import com.example.dailytodoapp.data.DailyTaskDao
import com.example.dailytodoapp.data.DailyTaskDatabase
import com.example.dailytodoapp.data.DailyTaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DailyTaskModule {

   @Provides
   @Singleton
   fun provideDailyTaskDatabase(application: Application): DailyTaskDatabase{
       return Room.databaseBuilder(
           application,
           DailyTaskDatabase::class.java,
           "daily_task_table"
       ).build()
   }

   @Provides
   @Singleton
   fun provideDailyTaskDao(database: DailyTaskDatabase):
           DailyTaskDao = database.dailyTaskDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dailyTaskDao: DailyTaskDao):
            DailyTaskRepository = DailyTaskRepository(dailyTaskDao)

}

