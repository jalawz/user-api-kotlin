package com.menezes.back.end.service

import com.menezes.back.end.dto.UserDTO
import com.menezes.back.end.model.User
import com.menezes.back.end.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getAll(): List<UserDTO> {
        val users = userRepository.findAll()
        return users.map { UserDTO.convert(it) }
    }

    fun findById(userId: Long): UserDTO {
        val user = getUserById(userId)
        return UserDTO.convert(user)
    }

    fun save(userDTO: UserDTO): UserDTO {
        val user =
            userRepository
                .save(User.convert(userDTO.copy(registrationDate = LocalDateTime.now())))
        return UserDTO.convert(user)
    }

    fun delete(userId: Long): UserDTO {
        val user = getUserById(userId)
        userRepository.delete(user)
        return UserDTO.convert(user)
    }

    fun findByCPF(cpf: String): UserDTO {
        val user = userRepository.findByCpf(cpf)
        return user.let { UserDTO.convert(it) }
    }

    fun queryByName(name: String): List<UserDTO> {
        val users = userRepository.queryByNomeLike(name)
        return users.map { UserDTO.convert(it) }
    }

    fun editUser(
        userId: Long,
        userDTO: UserDTO,
    ): UserDTO {
        val user = getUserById(userId)
        val updatedUser =
            userRepository.save(
                user.copy(
                    email = userDTO.cpf,
                    phone = userDTO.phone ?: user.phone,
                    address = userDTO.address ?: user.address,
                ),
            )
        return UserDTO.convert(updatedUser)
    }

    fun getAllPaginated(page: Pageable): Page<UserDTO> {
        val users = userRepository.findAll(page)
        return users.map { UserDTO.convert(it) }
    }

    private fun getUserById(userId: Long): User =
        userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found") }
}
