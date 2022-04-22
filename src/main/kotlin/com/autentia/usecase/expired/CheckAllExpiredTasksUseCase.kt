package com.autentia.usecase.expired

import com.autentia.repository.TaskRepository

internal class CheckAllExpiredTasksUseCase(val taskRepository: TaskRepository) {

    fun executeUseCase() {
        val listOftasks = taskRepository.findAllTasks()

        for (task in listOftasks) {
            if (task.isExpired()) {
                task.check()
                taskRepository.updateTask(task)
            }
        }

    }

}
