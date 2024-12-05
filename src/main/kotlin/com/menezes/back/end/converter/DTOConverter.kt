package com.menezes.back.end.converter

import com.menezes.back.end.model.User
import com.menezes.backend.client.dto.UserDTO

object DTOConverter {

    fun convert(user: User) = UserDTO(
        name = user.name,
        address = user.address,
        cpf = user.cpf,
        registrationDate = user.registrationDate,
        email = user.email,
        phone = user.phone
    )
}