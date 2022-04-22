package com.autentia.domain

import java.time.LocalDate

internal class Task(
    val text: String,
    val expirationDate: LocalDate = LocalDate.now().plusDays(7),
    var checked: Boolean = false
) {

    constructor(text: String, expirationDate: LocalDate) : this(
        text, expirationDate, false
    )

    fun check() {
        checked = true;
    }

    fun isExpired(): Boolean = expirationDate.isBefore(LocalDate.now())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }




}
