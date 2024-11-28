package com.menezes.back.end.controller

import com.menezes.back.end.dto.UserDTO
import com.menezes.back.end.service.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping
    fun getUsers(): List<UserDTO> {
        return userService.getAll()
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long,
    ): UserDTO {
        return userService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(
        @Valid @RequestBody userDTO: UserDTO,
    ): UserDTO {
        return userService.save(userDTO)
    }

    @GetMapping("/{cpf}/cpf")
    fun findByCpf(
        @PathVariable cpf: String,
    ): UserDTO {
        return userService.findByCPF(cpf)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(
        @PathVariable id: Long,
    ) {
        userService.delete(id)
    }

    @GetMapping("/search")
    fun queryByName(
        @RequestParam(name = "name", required = true) name: String,
    ): List<UserDTO> {
        return userService.queryByName(name)
    }

    @PatchMapping("/{id}")
    fun editUser(
        @PathVariable id: Long,
        @RequestBody userDTO: UserDTO,
    ): UserDTO {
        return userService.editUser(id, userDTO)
    }

    @GetMapping("/pageable")
    fun getUsersPaginated(pageable: Pageable): Page<UserDTO> {
        return userService.getAllPaginated(pageable)
    }
}
