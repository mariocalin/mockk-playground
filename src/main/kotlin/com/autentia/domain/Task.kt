package com.autentia.domain

import java.time.LocalDate

internal class Task(
    val text: String,
    var priorty: Priority = Priority.LOW,
    val expirationDate: LocalDate = LocalDate.now(),
    var checked: Boolean = false
) {

    constructor(text: String, priorty: Priority, expirationDate: LocalDate) : this(
        text, priorty, expirationDate, false
    )

    fun check() {
        checked = true;
    }
}
