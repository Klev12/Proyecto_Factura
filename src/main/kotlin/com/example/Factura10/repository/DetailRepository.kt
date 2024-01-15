package com.example.Factura10.repository

import com.example.Factura10.model.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository : JpaRepository<Detail, Long?> {

    fun findById (id: Long?): Detail?
    fun findByInvoiceId (invoiceId: Long?) : List<Detail>

}