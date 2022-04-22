package com.autentia.repository

import com.autentia.domain.Task

interface TaskRepository {
    fun saveTask(task: Task)
    fun updateTask(task: Task)
    fun findAllTasks(): List<Task>
}
