package com.menezes.back.end.dto

import com.menezes.back.end.model.User
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class UserDTO(
    @NotBlank(message = "Nome é obrigatório")
    val name: String,
    @NotBlank(message = "CPF é obrigatório")
    val cpf: String,
    val address: String?,
    @NotBlank(message = "E-mail é obrigatório")
    val email: String,
    val phone: String?,
    val registrationDate: LocalDateTime?,
) {
    companion object {
        fun convert(user: User) =
            UserDTO(
                name = user.name,
                cpf = user.cpf,
                address = user.address,
                email = user.email,
                phone = user.phone,
                registrationDate = user.registrationDate,
            )
    }
}
