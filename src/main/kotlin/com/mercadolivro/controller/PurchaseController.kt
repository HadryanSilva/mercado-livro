package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.controller.response.PurchaseResponse
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/purchase")
class PurchaseController(
    val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    fun purchase(@RequestBody request: PostPurchaseRequest): ResponseEntity<PurchaseResponse> {
        val purchaseSaved = purchaseService.create(purchaseMapper.toModel(request))
        val response = purchaseMapper.toResponse(purchaseSaved)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}