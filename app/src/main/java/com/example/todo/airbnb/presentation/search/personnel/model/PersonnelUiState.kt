package com.example.todo.airbnb.presentation.search.personnel.model

import com.example.todo.airbnb.domain.model.Personnel

data class PersonnelUiState(
    val showAlertMessage: Boolean,
    val personnel: Personnel
) {

    companion object {

        fun defaultOf() = PersonnelUiState(
            false,
            Personnel(0, 0, 0)
        )
    }
}