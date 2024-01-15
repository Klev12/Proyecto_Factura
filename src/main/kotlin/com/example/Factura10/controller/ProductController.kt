package com.example.Factura10.controller

import com.example.Factura10.model.Client
import com.example.Factura10.model.Product
import com.example.Factura10.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")   //endpoint
class ProductController {
    @Autowired
    lateinit var productService: ProductService

    /*@GetMapping
    fun list ():List <Product>{
        return productService.list()
    }*/
    @GetMapping
    fun list (product: Product, pageable: Pageable):ResponseEntity<*>{
        val response= productService.list(pageable,product)
        return ResponseEntity(response, HttpStatus.OK)
    }
    @GetMapping("list-dto")
    fun listDto(): ResponseEntity<*>{
        return ResponseEntity(productService.listDto(), HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(product), HttpStatus .OK)
    }
    @PutMapping
    fun update (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(product), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateName(product), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(productService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return productService.delete(id)
    }

}