package dev.bruno.forum.controller

import dev.bruno.forum.dto.AtualizacaoTopicoForm
import dev.bruno.forum.dto.TopicoForm
import dev.bruno.forum.dto.TopicoView
import dev.bruno.forum.service.TopicoService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid topicoForm: TopicoForm) {
        service.cadastrar(topicoForm)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm) {
        service.atualizar(form)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

}
