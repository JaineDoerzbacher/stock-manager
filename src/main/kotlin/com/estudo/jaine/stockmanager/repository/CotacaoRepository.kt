package com.estudo.jaine.stockmanager.repository

import com.estudo.jaine.stockmanager.model.Cotacao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface CotacaoRepository : PagingAndSortingRepository<Cotacao, Long> {


    @Query(
        value = "select c from Cotacao c where c.nome = :nome"
    )
    fun findByNome(@Param("nome") nome: String): List<Cotacao>


}