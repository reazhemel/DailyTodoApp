package com.example.dailytodoapp.ui.fragments.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytodoapp.data.DailyTaskEntity
import com.example.dailytodoapp.data.DailyTaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DailyTaskViewModel @Inject constructor(
    private val dailyTaskRepository: DailyTaskRepository
): ViewModel() {
    private val _dailyTaskList =  MutableStateFlow<List<DailyTaskEntity>>(emptyList())
    val dailyTaskList = _dailyTaskList.asStateFlow()

    init {
        getDailyTaskList()
    }

    private fun getDailyTaskList() {
        viewModelScope.launch {
            dailyTaskRepository.getTodoList().collect{dailyTaskList->
                _dailyTaskList.value = dailyTaskList
            }
        }
    }

    fun updateDailyTask(dailyTaskEntity: DailyTaskEntity){
        viewModelScope.launch {
            dailyTaskRepository.updateDailyTask(dailyTaskEntity = dailyTaskEntity)
        }
    }
}