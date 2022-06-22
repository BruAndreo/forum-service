package dev.bruno.forum.service

import dev.bruno.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(var cursos: List<Curso>) {

    init {
        val curso = Curso(id = 1, nome = "Kotlin com Spring Boot", categoria = "Kotlin")

        cursos = mutableListOf(curso)
    }

    fun buscaPorId(id: Long): Curso {
        return cursos.filter { c -> c.id == id }.first()
    }

}
