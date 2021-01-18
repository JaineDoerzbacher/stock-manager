package com.estudo.jaine.stockmanager.service.impl

import com.estudo.jaine.stockmanager.model.Cotacao
import com.estudo.jaine.stockmanager.model.CotacaoDTO
import com.estudo.jaine.stockmanager.repository.CotacaoRepository
import com.estudo.jaine.stockmanager.service.CotacaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CotacaoServiceImpl : CotacaoService {

    @Autowired
    lateinit var cotacaoRepository: CotacaoRepository

    override fun create(cotacao: CotacaoDTO) {
        val cotacaoMap = cotacao.quotes
        val indices = cotacaoMap.keys
        for (i in indices) {
            val quote = Cotacao(
                null,
                cotacao.id,
                i,
                cotacaoMap[i] ?: throw IllegalArgumentException("Valor da cotação dodia não encontrado")
            )
            this.cotacaoRepository.save(quote)
        }
    }

    override fun getById(id: String): CotacaoDTO? {
        val cotacoes = cotacaoRepository.findByNome(id)
        val cotacoesMap = HashMap<LocalDate, Double>()
        for (i in cotacoes) {
            cotacoesMap[i.data] = i.valor
        }
        return CotacaoDTO(id, cotacoesMap)
    }

    override fun getAll(): List<CotacaoDTO> {

        val todasCotacoes = cotacaoRepository.findAll().sortedBy { it.nome }
        var nome = ""
        val cotacoesDTO = ArrayList<CotacaoDTO>()
        var cotacoesMap = HashMap<LocalDate, Double>()
        for (cotacao in todasCotacoes) {
            if (nome == "") {
                nome = cotacao.nome
            }
            if (nome != cotacao.nome) {
                val cotacaoDTO = CotacaoDTO(nome, cotacoesMap)
                cotacoesDTO.add(cotacaoDTO)
                nome = cotacao.nome
                cotacoesMap = HashMap<LocalDate, Double>()
            }
            cotacoesMap[cotacao.data] = cotacao.valor

        }
        val cotacaoDTO = CotacaoDTO(nome, cotacoesMap)
        cotacoesDTO.add(cotacaoDTO)
        return cotacoesDTO
    }
}