package dev.bruno.forum.service

import dev.bruno.forum.dto.RespostaForm
import dev.bruno.forum.mapper.RespostaFormMapper
import dev.bruno.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val repository: RespostaRepository,
    private val respostaFormMapper: RespostaFormMapper
) {

    fun salvar(respostaForm: RespostaForm) {
        val resposta = respostaFormMapper.map(respostaForm)
        repository.save(resposta)
    }

}