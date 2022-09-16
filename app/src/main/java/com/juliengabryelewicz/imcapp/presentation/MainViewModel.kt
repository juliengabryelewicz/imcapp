package com.juliengabryelewicz.imcapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.juliengabryelewicz.imcapp.domain.usecases.ValidateHeight
import com.juliengabryelewicz.imcapp.domain.usecases.ValidateWeight

class MainViewModel(
    private val validateHeight: ValidateHeight = ValidateHeight(),
    private val validateWeight: ValidateWeight = ValidateWeight(),
): ViewModel() {

    var state by mutableStateOf(ImcFormState())

    fun onEvent(event: ImcFormEvent) {
        when(event) {
            is ImcFormEvent.HeightChanged -> {
                state = state.copy(height = event.height)
            }
            is ImcFormEvent.WeightChanged -> {
                state = state.copy(weight = event.weight)
            }
            is ImcFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val heightResult = validateHeight.execute(state.height)
        val weightResult = validateWeight.execute(state.weight)

        val hasError = listOf(
            heightResult,
            weightResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                imcText = null,
                heightError = heightResult.errorMessage,
                weightError = weightResult.errorMessage
            )
            return
        }

        val heightMeter : Float = state.height.replace(',', '.').toFloat() / 100
        val imcValue : Float = state.weight.replace(',', '.').toFloat()/(heightMeter*heightMeter)

        val imcText: String = when {
            imcValue < 18.5 -> "Maigreur"
            imcValue in (18.50..24.99) -> "Corpulence normale"
            imcValue in (25.00..29.99) -> "Surpoids"
            imcValue in (30.00..34.99) -> "Obésite modérée"
            imcValue in (35.00..39.99) -> "Obésite sévère"
            else -> "Obésité morbide"
        }

        state = state.copy(
            imcText = imcText,
            heightError = null,
            weightError = null
        )
    }
}