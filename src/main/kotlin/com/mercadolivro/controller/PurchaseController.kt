package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.controller.response.PurchaseResponse
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/purchase")
class PurchaseController(
    val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @GetMapping("/customer/{id}")
    fun findAllByCustomer(@PathVariable id: Int): ResponseEntity<List<PurchaseResponse>> {
        val purchases = purchaseService.findAllByCustomer(id)
        val response = purchases.map { purchaseMapper.toResponse(it) }
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun purchase(@RequestBody request: PostPurchaseRequest): ResponseEntity<PurchaseResponse> {
        val purchaseSaved = purchaseService.create(purchaseMapper.toModel(request))
        val response = purchaseMapper.toResponse(purchaseSaved)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}