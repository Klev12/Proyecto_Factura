package com.example.Factura10.controller

import com.example.Factura10.model.Client
import com.example.Factura10.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")   //endpoint
class ClientController {
    @Autowired
    lateinit var clientService: ClientService

    /*@GetMapping
    fun list ():List <Client>{
        return clientService.list()
    }*/
    @GetMapping
    fun list (client: Client, pageable: Pageable):ResponseEntity<*>{
        val response= clientService.list(pageable,client)
        return ResponseEntity(response, HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody client: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.save(client), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody client: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.update(client), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody client: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.updateName(client), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(clientService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return clientService.delete(id)
    }


}

