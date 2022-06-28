package dev.bruno.forum.service

import dev.bruno.forum.model.Curso
import dev.bruno.forum.repository.CursoRepository
import dev.bruno.forum.exceptions.NotFoundException
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscaPorId(id: Long): Curso {
        return repository.findById(id)
            .orElseThrow { NotFoundException("Curso não encontrado") }
    }

}
