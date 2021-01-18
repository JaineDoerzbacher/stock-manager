package com.estudo.jaine.stockmanager.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "TBL_COTACAO")
data class Cotacao (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val nome: String,
    val data: LocalDate,
    val valor: Double
)