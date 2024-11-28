package com.menezes.back.end.repository

import com.menezes.back.end.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByCpf(cpf: String): User

    fun queryByNomeLike(name: String): List<User>
}
