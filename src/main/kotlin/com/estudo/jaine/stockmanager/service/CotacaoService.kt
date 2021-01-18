package com.estudo.jaine.stockmanager.service

import com.estudo.jaine.stockmanager.model.Cotacao
import com.estudo.jaine.stockmanager.model.CotacaoDTO

interface CotacaoService {

    fun create(cotacao: CotacaoDTO)
    fun getById(id: String): CotacaoDTO?
    fun getAll(): List<CotacaoDTO>
}