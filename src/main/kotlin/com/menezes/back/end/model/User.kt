package com.menezes.back.end.model

import com.menezes.back.end.dto.UserDTO
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
            requireNotNull(dto.address) { "Address cannot be null" }
            requireNotNull(dto.phone) { "Phone cannot be null" }
            requireNotNull(dto.registrationDate) { "Registration date cannot be null" }
            return User(
                name = dto.name,
                cpf = dto.cpf,
                address = dto.address,
                email = dto.email,
                phone = dto.phone,
                registrationDate = dto.registrationDate,
            )
        }
    }
}
