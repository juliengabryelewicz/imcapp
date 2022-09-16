package com.juliengabryelewicz.imcapp.domain.usecases

class ValidateHeight {

    fun execute(height: String): ValidationResult {
        if(height.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "La taille est obligatoire"
            )
        }

        if(height.replace(',', '.').toFloat() < 60.0 || height.replace(',', '.').toFloat() > 300.0) {
            return ValidationResult(
                successful = false,
                errorMessage = "Veuillez donner une taille entre 60 et 300 cm"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}