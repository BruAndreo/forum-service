package dev.bruno.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RespostaForm(
    @field:NotEmpty(message = "Mensagem não pode ser deixada em branco")
    @field:Size(min = 5)
    val mensagem: String,

    @field:NotNull
    val idTopico: Long,

    @field:NotNull
    val idAutor: Long
)
