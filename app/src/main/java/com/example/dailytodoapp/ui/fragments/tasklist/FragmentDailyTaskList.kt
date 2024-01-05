package com.example.dailytodoapp.ui.fragments.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailytask.databinding.FragmentDailyTaskListBinding
import com.example.dailytodoapp.data.DailyTaskEntity
import com.example.dailytodoapp.ui.fragments.tasklist.adapter.DailyTaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentDailyTaskList : Fragment() {

    private var _binding: FragmentDailyTaskListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel by viewModels<DailyTaskViewModel>()
    private lateinit var taskAdapter: DailyTaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyTaskListBinding.inflate(layoutInflater)
        taskAdapter = DailyTaskAdapter(
            onCheckBoxClicked = {task, isDone->
                taskViewModel.updateDailyTask(
                    dailyTaskEntity = DailyTaskEntity(
                        id = task.id,
                        taskName = task.taskName,
                        isTaskDone = task.isTaskDone
                    )
                )
            }
        )

        binding.addTaskFabBtn.setOnClickListener {
            findNavController().navigate(FragmentDailyTaskListDirections.actionFragmentDailyTaskListToFragmentPlanTask())
        }

        setUpDailyTaskAdapter()
        observeDailyTaskList()

        return binding.root
    }

    private fun observeDailyTaskList() {
        binding.tasListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.tasListRecyclerView.setHasFixedSize(true)
        binding.tasListRecyclerView.adapter = taskAdapter
    }

    private fun setUpDailyTaskAdapter() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                taskViewModel.dailyTaskList.collect{task->
                    taskAdapter.submitDailyTaskList(task)
                }

            }
        }
    }

}