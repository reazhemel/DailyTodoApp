package com.example.dailytodoapp.ui.fragments.plantask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dailytask.R
import com.example.dailytask.databinding.FragmentPlanTaskBinding
import com.example.dailytodoapp.data.DailyTaskEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPlanTask : Fragment() {

    private var _binding: FragmentPlanTaskBinding? = null
    private val binding get() = _binding!!

    private val taskPlanViewModel by viewModels<PlanTaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       _binding = FragmentPlanTaskBinding.inflate(layoutInflater)

        binding.addTaskBtn.setOnClickListener {
            val task = binding.taskEditText.text?.trim().toString()
            if (task.isNotEmpty()){
                taskPlanViewModel.insertDailyTask(
                    DailyTaskEntity(
                        taskName = task,
                        isTaskDone = false
                    )
                )
                findNavController().popBackStack()
            }else{
                Toast.makeText(requireActivity(), "Field can not be empty", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }


}