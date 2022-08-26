package dev.bruno.forum.controller

import dev.bruno.forum.dto.RespostaForm
import dev.bruno.forum.model.Resposta
import dev.bruno.forum.service.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearerAuth")
class RespostaController(
    private val service: RespostaService
) {

    @PostMapping
    fun salvar(@RequestBody @Valid respostaForm: RespostaForm) = service.salvar(respostaForm)

}