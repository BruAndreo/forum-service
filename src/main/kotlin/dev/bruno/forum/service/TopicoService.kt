package dev.bruno.forum.service

import dev.bruno.forum.dto.TopicoDTO
import dev.bruno.forum.model.Curso
import dev.bruno.forum.model.Topico
import dev.bruno.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = mutableListOf(),
    private val cursoService: CursoService,
    private val autorService: UsuarioService
) {

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
    }

    fun cadastrar(topicoDto: TopicoDTO) {
        topicos = topicos.plus(Topico(
            id = topicos.size.toLong() + 1,
            titulo = topicoDto.titulo,
            mensagem = topicoDto.mensagem,
            curso = cursoService.buscaPorId(topicoDto.idCurso),
            autor = autorService.buscaPorId(topicoDto.idAutor)
        ))
    }
}
