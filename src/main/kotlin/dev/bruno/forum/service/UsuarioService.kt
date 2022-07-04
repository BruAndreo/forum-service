package dev.bruno.forum.service

import dev.bruno.forum.exceptions.NotFoundException
import dev.bruno.forum.model.Usuario
import dev.bruno.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository): UserDetailsService {

    fun buscaPorId(id: Long): Usuario {
        return repository.findById(id)
            .orElseThrow { NotFoundException("Usuário não encontrado") }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }

}
