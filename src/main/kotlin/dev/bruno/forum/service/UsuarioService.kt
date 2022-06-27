package dev.bruno.forum.service

import dev.bruno.forum.model.Usuario
import dev.bruno.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscaPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }

}
