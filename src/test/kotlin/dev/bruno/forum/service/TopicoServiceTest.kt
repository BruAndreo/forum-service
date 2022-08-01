package dev.bruno.forum.service

import dev.bruno.forum.mapper.TopicoFormMapper
import dev.bruno.forum.mapper.TopicoViewMapper
import dev.bruno.forum.model.TopicoTest
import dev.bruno.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.PageImpl

class TopicoServiceTest {

    private val topicos = PageImpl(listOf(TopicoTest.build()))

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }

    val topicoViewMapper: TopicoViewMapper = mockk()
    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(topicoRepository, topicoViewMapper, topicoFormMapper)
}