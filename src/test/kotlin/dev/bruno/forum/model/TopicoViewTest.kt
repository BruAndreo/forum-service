package dev.bruno.forum.model

import dev.bruno.forum.dto.TopicoView
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Kotlin Básico",
        mensagem = "Aprendendo Kotlin Básico",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDateTime.now()
    )
}