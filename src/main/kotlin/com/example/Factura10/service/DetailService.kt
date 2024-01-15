package com.example.Factura10.service

import com.example.Factura10.model.Detail
import com.example.Factura10.repository.DetailRepository
import com.example.Factura10.repository.InvoiceRepository
import com.example.Factura10.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var productRepository: ProductRepository


    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
    fun save(detail: Detail): Detail{
        try{

            invoiceRepository.findById(detail.invoiceId)
                    ?: throw Exception("Id invoice no encontrada")
            productRepository.findById(detail.productId)
                    ?: throw Exception("Id product no encontrada")
            detailRepository.findById(detail.id)
                    ?: throw Exception("Id product no encontrada")

            val response = detailRepository.save(detail)
            val product = productRepository.findById(detail.id)
            val listDetail = detailRepository.findByInvoiceId(detail.invoiceId)
            val invoicetoVP = invoiceRepository.findById(detail.invoiceId)
            var sum = 0.0
            listDetail.map {items ->
                sum += sum +(detail.price!!.times(detail.quantity!!))
            }
            invoicetoVP?.apply {
                total = sum
            }
            invoiceRepository.save(invoicetoVP!!)
            product?.apply {
                stock = stock?.minus (response.quantity!!)
            }
            productRepository.save(product!!)
            return detailRepository.save(detail)

        }

        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }
    fun update(detail: Detail): Detail{
        try {
            detailRepository.findById(detail.id)
                    ?: throw Exception("ID no existe")

            return detailRepository.save(detail)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(detail: Detail): Detail{
        try{
            val response = detailRepository.findById(detail.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                quantity=detail.quantity //un atributo del modelo
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Detail?{
        return detailRepository.findById(id)
    }
    fun delete(id: Long?): Boolean {
        try {
            val detail = detailRepository.findById(id)
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")

            val product = productRepository.findById(detail.productId)
            product?.apply {
                stock = stock?.plus(detail.quantity!!)
            }
            productRepository.save(product!!)

            detailRepository.deleteById(id!!)

            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el detalle", ex)
        }


    }
}