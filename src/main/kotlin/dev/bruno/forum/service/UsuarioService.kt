package dev.bruno.forum.service

import dev.bruno.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(id = 1, nome = "Bruno", email = "bruno@email.com")

        usuarios = mutableListOf(usuario)
    }

    fun buscaPorId(id: Long): Usuario {
        return usuarios.stream().filter { u -> u.id == id }.findFirst().get()
    }

}
