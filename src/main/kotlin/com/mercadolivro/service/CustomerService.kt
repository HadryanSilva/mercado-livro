package com.mercadolivro.service

import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CustomerService(
    val repository: CustomerRepository
) {
    fun getAllCustomers(name: String?): List<CustomerResponse>? {
        val customers = if (name.isNullOrBlank()) {
            repository.findAll().map { it.toResponse() }
        } else {
            repository.findByName(name)?.map { customer -> customer.toResponse()  }
        }
        return customers
    }

    fun getCustomer(id: Int): CustomerResponse? {
        val customer = repository.findById(id).getOrNull()?.toResponse()
        return customer
    }

    fun create(customer: Customer): CustomerResponse {
        val response = repository.save(customer).toResponse()
        return response
    }

    fun update(customer: Customer) {
        val customerModel = customer.id?.let { repository.findById(it).getOrNull() }
            ?: throw Exception("Customer not found")
        customerModel.name = customer.name
        customerModel.email = customer.email
    }

    fun delete(id: Int) {
        repository.deleteById(id)
    }
}