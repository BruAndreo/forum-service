package dev.bruno.forum.mapper

import dev.bruno.forum.dto.RespostaForm
import dev.bruno.forum.model.Resposta
import dev.bruno.forum.service.TopicoService
import dev.bruno.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private val topicoService: TopicoService,
    private val usuarioService: UsuarioService
) : Mapper<RespostaForm, Resposta> {

    override fun map(t: RespostaForm): Resposta {
        return Resposta(
            mensagem = t.mensagem,
            topico = topicoService.getById(t.idTopico),
            autor = usuarioService.buscaPorId(t.idAutor)
        )
    }
}