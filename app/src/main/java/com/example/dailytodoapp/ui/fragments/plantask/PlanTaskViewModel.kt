package com.example.dailytodoapp.ui.fragments.plantask

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytodoapp.data.DailyTaskEntity
import com.example.dailytodoapp.data.DailyTaskRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlanTaskViewModel @Inject constructor(
    private val dailyTaskRepository: DailyTaskRepository
): ViewModel() {

    fun insertDailyTask(task: DailyTaskEntity){
        viewModelScope.launch{
            dailyTaskRepository.insertDailyTask(dailyTaskEntity = task)
        }
    }
}