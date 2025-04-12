package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.extension.toModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController(
    val service: CustomerService
) {
    @GetMapping("/hello")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, Customers!")
    }

    @GetMapping("/list")
    fun getAllCustomers(@RequestParam name: String?): ResponseEntity<List<CustomerResponse>> {
        val customers = service.getAllCustomers(name)
        return ResponseEntity.ok(customers)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<CustomerResponse> {
        val customer = service.findById(id)?.toResponse()
        return ResponseEntity.ok(customer)
    }

    @PostMapping
    fun create(@RequestBody @Valid customer: PostCustomerRequest): ResponseEntity<CustomerResponse> {
        val response = service.create(customer.toModel()).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping
    fun update(@RequestBody @Valid customer: PutCustomerRequest): ResponseEntity<Void> {
        val customerSaved = service.findById(customer.id)
        service.update(customer.toModel(customerSaved))
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}