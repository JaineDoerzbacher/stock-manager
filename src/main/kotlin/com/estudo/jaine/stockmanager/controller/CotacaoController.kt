package com.estudo.jaine.stockmanager.controller

import com.estudo.jaine.stockmanager.model.Cotacao
import com.estudo.jaine.stockmanager.model.CotacaoDTO
import com.estudo.jaine.stockmanager.model.ErrorMessage
import com.estudo.jaine.stockmanager.model.RespostaJSON
import com.estudo.jaine.stockmanager.service.CotacaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


@RestController
@RequestMapping(value = ["/cotacoes"])
class CotacaoController {

    @Autowired
    lateinit var cotacaoService: CotacaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Any> {

        var cotacao = this.cotacaoService.getById(id)

        return if (cotacao != null)
            return ResponseEntity(cotacao, HttpStatus.OK)
        else return ResponseEntity(
            ErrorMessage("Cotacao nao localizada", "Cotacao ${id} nao localizada"),
            HttpStatus.NOT_FOUND
        )
    }

    @PostMapping()
    fun create(@RequestBody cotacao: CotacaoDTO): ResponseEntity<RespostaJSON> {
        this.cotacaoService.create(cotacao)
        val respostaJSON = RespostaJSON("Ok")
        return ResponseEntity(respostaJSON, HttpStatus.CREATED)
    }


    @GetMapping()
    fun getAll(): ResponseEntity<List<CotacaoDTO>> {

        val list = this.cotacaoService.getAll()
        val status = if (list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(list, status)
    }

}