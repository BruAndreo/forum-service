package dev.bruno.forum.service

import dev.bruno.forum.dto.TopicoForm
import dev.bruno.forum.dto.TopicoView
import dev.bruno.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = listOf(),
    private val cursoService: CursoService,
    private val autorService: UsuarioService
) {

    fun listar(): List<TopicoView> {
        return topicos.map { topico -> TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )}.toList()
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
    }

    fun cadastrar(topicoForm: TopicoForm) {
        topicos = topicos.plus(Topico(
            id = topicos.size.toLong() + 1,
            titulo = topicoForm.titulo,
            mensagem = topicoForm.mensagem,
            curso = cursoService.buscaPorId(topicoForm.idCurso),
            autor = autorService.buscaPorId(topicoForm.idAutor)
        ))
    }
}
