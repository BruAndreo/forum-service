package dev.bruno.forum.service

import dev.bruno.forum.mapper.TopicoFormMapper
import dev.bruno.forum.mapper.TopicoViewMapper
import dev.bruno.forum.model.Topico
import dev.bruno.forum.model.TopicoTest
import dev.bruno.forum.model.TopicoViewTest
import dev.bruno.forum.repository.TopicoRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicoServiceTest {

    private val topicos = PageImpl(listOf(TopicoTest.build()))

    private val paginacao: Pageable = mockk()

    private val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }

    private val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    private val topicoFormMapper: TopicoFormMapper = mockk()

    private val topicoService = TopicoService(topicoRepository, topicoViewMapper, topicoFormMapper)

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.listar("Kotlin Avançado", paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), paginacao) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }
}