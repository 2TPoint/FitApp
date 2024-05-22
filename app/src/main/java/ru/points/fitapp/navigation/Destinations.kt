package ru.points.fitapp.navigation

/**
 * @file Destinations.kt
 * @brief Объект для определения маршрутов навигации в приложении.
 *
 * Этот объект содержит константы, которые используются для идентификации страниц навигации в приложении.
 * Он позволяет легко ссылаться на различные экраны приложения при настройке навигации.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */

const val TRAINING_ARGUMENT_ID_KEY = "id"

object Destinations {
    /**
     * Маршрут к странице списка упражнений.
     */
    const val EXERCISE_LIST_PAGE = "EXERCISE_LIST_PAGE"

    /**
     * Маршрут к странице настроек.
     */
    const val SETTINGS_PAGE = "SETTINGS_PAGE"
    const val FOOD_SUMMARY_PAGE = "FOOD_SUMMARY_PAGE"
    const val FOOD_SEARCH_PAGE = "FOOD_SEARCH_PAGE"
    const val CURRENT_TRAINING_PAGE = "CURRENT_TRAINING_PAGE"
}