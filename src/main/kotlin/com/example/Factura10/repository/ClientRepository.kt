package com.example.Factura10.repository

import com.example.Factura10.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long?> {

    fun findById (id: Long?): Client?


}