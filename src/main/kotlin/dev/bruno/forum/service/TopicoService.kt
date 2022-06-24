package dev.bruno.forum.service

import dev.bruno.forum.dto.AtualizacaoTopicoForm
import dev.bruno.forum.dto.TopicoForm
import dev.bruno.forum.dto.TopicoView
import dev.bruno.forum.mapper.TopicoFormMapper
import dev.bruno.forum.mapper.TopicoViewMapper
import dev.bruno.forum.model.Topico
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

    fun cadastrar(topicoForm: TopicoForm) {
        val t = topicoFormMapper.map(topicoForm)
        t.id = topicos.size.toLong() + 1
        topicos = topicos.plus(t)
    }

    fun atualizar(form: AtualizacaoTopicoForm) {
        val topicoOriginal = topicos.filter { it.id == form.id }.first()

        topicos = topicos.minus(topicoOriginal).plus(Topico(
            id = topicoOriginal.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topicoOriginal.autor,
            curso = topicoOriginal.curso,
            respostas = topicoOriginal.respostas,
            status = topicoOriginal.status,
            dataCriacao = topicoOriginal.dataCriacao
        ))
    }

    fun deletar(id: Long) {
        val topico = topicos.filter { it.id == id }.first()
        topicos = topicos.minus(topico)
    }
}
