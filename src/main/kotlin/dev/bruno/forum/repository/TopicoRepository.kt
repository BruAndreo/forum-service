package dev.bruno.forum.repository

import dev.bruno.forum.dto.TopicoPorCategoriaDto
import dev.bruno.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>
    @Query("SELECT new dev.bruno.forum.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso c GROUP BY c.categoria")
    fun relatorio(): List<TopicoPorCategoriaDto>

}