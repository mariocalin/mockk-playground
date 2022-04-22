package com.autentia.test

import com.autentia.repository.TaskRepository
import com.autentia.usecase.create.CreateTaskRequest
import com.autentia.usecase.create.CreateTaskUseCase
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class CreateTaskUseCaseTest {

    @Test
    fun `use case should create task`() {
        val taskRepository = mockk<TaskRepository>()
        every { taskRepository.saveTask(any()) } returns Unit

        val taskText = "Realizar tutorial de mockk en adictosaltrabajo.com"
        val usecase = CreateTaskUseCase(taskRepository)

        val request = CreateTaskRequest(taskText)
        val createdTask = usecase.executeUseCase(request)

        verify(exactly = 1) { taskRepository.saveTask(any()) }
        assert(createdTask.text == taskText)
    }

}
