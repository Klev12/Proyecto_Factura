package com.example.Factura10.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "invoice")
class Invoice{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null   //description_one en la base de datos
    @Column(name="create_at")
    var createAt: Date? = null   //address
    var total: Double? = null
    @Column(name="client_id")
    var clientId: Long? = null

}