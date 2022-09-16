package com.juliengabryelewicz.imcapp.domain.usecases

class ValidateWeight {

    fun execute(weight: String): ValidationResult {
        if(weight.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Le poids est obligatoire"
            )
        }

        if(weight.replace(',', '.').toFloat() < 20.0 || weight.replace(',', '.').toFloat() > 600.0) {
            return ValidationResult(
                successful = false,
                errorMessage = "Veuillez donner un poids entre 20 et 600 kg"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}