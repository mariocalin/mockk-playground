package com.autentia.repository

import com.autentia.domain.Task

internal interface TaskRepository {
    fun saveTask(task: Task)
    fun updateTask(task: Task)
    fun findAllTasks(): List<Task>
}
