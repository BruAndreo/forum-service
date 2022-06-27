package dev.bruno.forum.service

import dev.bruno.forum.model.Curso
import dev.bruno.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscaPorId(id: Long): Curso {
        return repository.getReferenceById(id)
    }

}
