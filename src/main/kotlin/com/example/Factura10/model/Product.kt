package com.example.Factura10.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null   //description_one en la base de datos
    var brand: String? = null   //address
    var price: Double? = null
    var stock: Long? = null


}