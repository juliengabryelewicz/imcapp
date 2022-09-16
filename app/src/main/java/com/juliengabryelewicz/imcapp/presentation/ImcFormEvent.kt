package com.juliengabryelewicz.imcapp.presentation

sealed class ImcFormEvent {
    data class HeightChanged(val height: String) : ImcFormEvent()
    data class WeightChanged(val weight: String) : ImcFormEvent()
    object Submit: ImcFormEvent()
}
