package dev.bruno.forum.service

import dev.bruno.forum.model.Resposta
import dev.bruno.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository
) {

    fun salvar(resposta: Resposta) = respostaRepository.save(resposta)

}