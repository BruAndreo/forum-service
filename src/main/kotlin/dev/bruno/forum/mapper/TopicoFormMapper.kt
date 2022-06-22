package dev.bruno.forum.mapper

import dev.bruno.forum.dto.TopicoForm
import dev.bruno.forum.model.Topico
import dev.bruno.forum.service.CursoService
import dev.bruno.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val autorService: UsuarioService
): Mapper<TopicoForm, Topico> {

    override fun map(t: TopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscaPorId(t.idCurso),
            autor = autorService.buscaPorId(t.idAutor)
        )
    }

}
