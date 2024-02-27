package ru.points.fitapp.utils

interface EventListener {
    fun handle(event: Event)
}

interface Event