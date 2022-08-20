package dev.bruno.forum.controller

import dev.bruno.forum.config.JWTUtil
import dev.bruno.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val RECURSO = "/topicos/"
    }

    @BeforeEach
    fun setUp() {
        token = generateToken()

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    private fun generateToken() : String? {
        val authorities = mutableListOf(Role(1, "LEITURA_ESCRITA"))
        return jwtUtil.generateToken("bruno@email.com", authorities)
    }

    @Test
    fun `deve retornar 400 quando requisição estiver sem token`() {
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `deve retornar 200 quando requisição estiver com token válido`() {
        mockMvc.get(RECURSO) {
            headers { token?.let { this.setBearerAuth(it) } }
        }.andExpect {
            status { is2xxSuccessful() }
        }
    }
}