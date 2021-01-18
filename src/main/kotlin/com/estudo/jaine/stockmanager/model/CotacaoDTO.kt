package com.estudo.jaine.stockmanager.model

import java.time.LocalDate

data class CotacaoDTO(

    val id: String,
    val quotes: Map<LocalDate, Double>
)
