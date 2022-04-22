package com.autentia

import com.autentia.domain.Task
import com.autentia.repository.TaskRepository
import com.autentia.usecase.expired.CheckAllExpiredTasksUseCase
import io.mockk.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class CheckAllExpiredTasksUseCaseTest {

    @Test
    fun `use case should check expired task`() {
        val taskRepository = mockk<TaskRepository>(relaxUnitFun = true)

        val tareaPendiente = Task("Tarea pendiente", LocalDate.now().plusDays(1))
        val tareaExpirada = Task("Tarea expirada", LocalDate.now().minusDays(1))

        every { taskRepository.findAllTasks() } returns listOf(
            tareaPendiente, tareaExpirada
        )

        justRun { taskRepository.updateTask(any()) }


        val useCase = CheckAllExpiredTasksUseCase(taskRepository)

        useCase.executeUseCase()

        verifyOrder {
            taskRepository.findAllTasks()
            taskRepository.updateTask(tareaExpirada)
        }

        verify(exactly = 0) {
            taskRepository.saveTask(any())
        }
    }

    @Test
    fun `use case should check expired task with mocks`() {
        val taskRepository = mockk<TaskRepository>()
        every { taskRepository.findAllTasks() } returns listOf(mockk {
            every { text } returns "Tarea pendiente"
            every { expirationDate } returns LocalDate.now().plusDays(1)
            every { isExpired() } returns false
            justRun { check() }
        }, mockk {
            every { text } returns "Tarea expirada"
            every { expirationDate } returns LocalDate.now().minusDays(1)
            every { isExpired() } returns true
            justRun { check() }
        })

        justRun { taskRepository.updateTask(any()) }

        val useCase = CheckAllExpiredTasksUseCase(taskRepository)

        useCase.executeUseCase()

        verifyOrder {
            taskRepository.findAllTasks()
            taskRepository.updateTask(any())
        }
    }
}
