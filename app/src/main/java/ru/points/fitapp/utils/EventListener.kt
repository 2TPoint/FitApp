package ru.points.fitapp.utils

/**
 * @file EventListener.kt
 * @brief Интерфейс слушателя событий.
 *
 * Определяет метод для обработки событий.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
interface EventListener {
    /**
     * @brief Обрабатывает событие.
     *
     * Вызывается при возникновении события.
     *
     * @param event Событие для обработки.
     */
    fun handle(event: Event)
}

/**
 * @file EventListener.kt
 * @brief Интерфейс события.
 *
 * Базовый интерфейс для всех событий в приложении.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
interface Event