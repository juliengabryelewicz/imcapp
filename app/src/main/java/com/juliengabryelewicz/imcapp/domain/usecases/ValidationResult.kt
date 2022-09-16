package com.juliengabryelewicz.imcapp.domain.usecases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
