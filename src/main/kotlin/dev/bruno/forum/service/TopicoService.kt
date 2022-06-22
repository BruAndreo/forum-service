package dev.bruno.forum.service

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
}
