package dev.bruno.forum.service

import dev.bruno.forum.exceptions.NotFoundException
import dev.bruno.forum.model.Usuario
import dev.bruno.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscaPorId(id: Long): Usuario {
        return repository.findById(id)
            .orElseThrow { NotFoundException("Usuário não encontrado") }
    }

}
