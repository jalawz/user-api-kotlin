package com.menezes.back.end.model

import com.menezes.backend.client.dto.UserDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val cpf: String,
    val address: String,
    val email: String,
    val phone: String,
    val registrationDate: LocalDateTime,
) {
    companion object {
        fun convert(dto: UserDTO): User {
            val address = requireNotNull(dto.address) { "Address cannot be null" }
            val phone = requireNotNull(dto.phone) { "Phone cannot be null" }
            val registrationDate =
                requireNotNull(dto.registrationDate) { "Registration date cannot be null" }
            return User(
                name = dto.name,
                cpf = dto.cpf,
                address = address,
                email = dto.email,
                phone = phone,
                registrationDate = registrationDate,
            )
        }
    }
}
