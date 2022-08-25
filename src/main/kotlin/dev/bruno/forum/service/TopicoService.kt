package dev.bruno.forum.service

import dev.bruno.forum.dto.AtualizacaoTopicoForm
import dev.bruno.forum.dto.TopicoForm
import dev.bruno.forum.dto.TopicoPorCategoriaDto
import dev.bruno.forum.dto.TopicoView
import dev.bruno.forum.exceptions.NotFoundException
import dev.bruno.forum.mapper.TopicoFormMapper
import dev.bruno.forum.mapper.TopicoViewMapper
import dev.bruno.forum.model.Topico
import dev.bruno.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    @Cacheable(cacheNames = ["topicos"], key = "#root.method.name")
    // Don't make sense put cache in topicos, make sense in cursos
    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = nomeCurso?.let {
            repository.findByCursoNome(nomeCurso, paginacao)
        } ?: repository.findAll(paginacao)


        return topicos.map { topico -> topicoViewMapper.map(topico)}
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException("Topico não encontrado")}

        return topicoViewMapper.map(topico)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val t = topicoFormMapper.map(topicoForm)
        repository.save(t)
        return topicoViewMapper.map(t)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topicoOriginal = repository.findById(form.id)
            .orElseThrow { NotFoundException("Topico não encontrado") }

        topicoOriginal.titulo = form.titulo
        topicoOriginal.mensagem = form.mensagem
        topicoOriginal.dataAlteracao = LocalDateTime.now()

        return topicoViewMapper.map(topicoOriginal)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}
