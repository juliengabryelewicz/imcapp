package com.juliengabryelewicz.imcapp.presentation

data class ImcFormState(
    val height: String = "",
    val heightError: String? = null,
    val weight: String = "",
    val weightError: String? = null,
    val imcText: String? = null
)
