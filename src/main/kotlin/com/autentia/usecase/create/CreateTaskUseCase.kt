package com.autentia.usecase.create

import com.autentia.domain.Task
import com.autentia.repository.TaskRepository

internal class CreateTaskUseCase(val taskRepository: TaskRepository) {
    fun executeUseCase(request: CreateTaskRequest): Task {
        val task = Task(request.text)
        taskRepository.saveTask(task)
        return task
    }
}
