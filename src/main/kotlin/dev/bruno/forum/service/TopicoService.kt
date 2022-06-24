package dev.bruno.forum.service

import dev.bruno.forum.dto.AtualizacaoTopicoForm
import dev.bruno.forum.dto.TopicoForm
import dev.bruno.forum.dto.TopicoView
import dev.bruno.forum.exceptions.NotFoundException
import dev.bruno.forum.mapper.TopicoFormMapper
import dev.bruno.forum.mapper.TopicoViewMapper
import dev.bruno.forum.model.Topico
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = listOf(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listar(): List<TopicoView> {
        return topicos.map { topico -> topicoViewMapper.map(topico)}.toList()
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.filter { t ->
            t.id == id
        }.first()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val t = topicoFormMapper.map(topicoForm)
        t.id = topicos.size.toLong() + 1
        topicos = topicos.plus(t)
        return topicoViewMapper.map(t)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topicoOriginal = topicos.filter { it.id == form.id }.first()

        val topicoAtualizado = Topico(
            id = topicoOriginal.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topicoOriginal.autor,
            curso = topicoOriginal.curso,
            respostas = topicoOriginal.respostas,
            status = topicoOriginal.status,
            dataCriacao = topicoOriginal.dataCriacao
        )

        topicos = topicos.minus(topicoOriginal).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.filter { it.id == id }.first()

        if (topico.id == null) {
            NotFoundException("ID não encontrado")
        }

        topicos = topicos.minus(topico)
    }
}
