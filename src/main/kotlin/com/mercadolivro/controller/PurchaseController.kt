package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostPurchaseRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/purchase")
class PurchaseController {

    @PostMapping
    fun purchase(@RequestBody request: PostPurchaseRequest): ResponseEntity<Void> {

        return ResponseEntity.ok().build()
    }

}