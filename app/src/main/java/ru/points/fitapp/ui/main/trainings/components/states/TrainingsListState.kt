package ru.points.fitapp.ui.main.trainings.components.states

import ru.points.fitapp.data.vo.TrainingVo

data class TrainingsListState(
    /**
     * Список тренировок в виде VO.
     */
    val list: List<TrainingVo> = listOf(),

    /**
     * Флаг, указывающий, показано ли всплывающее окно.
     */
    val isPopupShowed: Boolean = false,

    /**
     * Состояние всплывающего окна.
     */
    val popupState: TrainingPopupState = TrainingPopupState()
)
