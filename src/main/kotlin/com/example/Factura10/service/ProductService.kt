package com.example.Factura10.service

import com.example.Factura10.dto.ProductDto
import com.example.Factura10.mapper.ProductMapper
import com.example.Factura10.model.Product
import com.example.Factura10.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository
    fun list ():List<Product>{
        return productRepository.findAll()
    }
    fun list (pageable: Pageable,product: Product):Page<Product>{
        val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("description"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return productRepository.findAll(Example.of(product, matcher), pageable)
    }
    fun listDto() : List<ProductDto> {
        val productList = productRepository.findAll()
        val productDtoList = mutableListOf<ProductDto>()
        productList.map { product ->
            val productDto = ProductMapper.mapToDto(product)
            productDtoList.add(productDto)

        }

        return productDtoList
    }

    fun save(product: Product): Product{
        try{
            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(product: Product): Product{
        try {
            productRepository.findById(product.id)
                    ?: throw Exception("ID no existe")

            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(product: Product): Product{
        try{
            val response = productRepository.findById(product.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                description=product.description //un atributo del modelo
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Product?{
        return productRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = productRepository.findById(id)
                    ?: throw Exception("ID no existe")
            productRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}