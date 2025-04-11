package com.mercadolivro.service

import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val repository: CustomerRepository,
    val bookService: BookService
) {
    fun getAllCustomers(name: String?): List<CustomerResponse>? {
        val customers = if (name.isNullOrBlank()) {
            repository.findAll().map { it.toResponse() }
        } else {
            repository.findByNameContaining(name)?.map { customer -> customer.toResponse()  }
        }
        return customers
    }

    fun findById(id: Int?): Customer {
        val customer = repository.findById(id!!)
            .orElseThrow { Exception("Customer not found") }
        return customer
    }

    fun create(customer: Customer): Customer {
        val response = repository.save(customer)
        return response
    }

    fun update(customer: Customer) {
        if (!repository.existsById(customer.id!!)) {
            throw Exception("Customer not found")
        }

        repository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.DELETED
        repository.save(customer)
    }
}