package dev.bruno.forum.repository

import dev.bruno.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {

    fun findByEmail(username: String?): Usuario?
}