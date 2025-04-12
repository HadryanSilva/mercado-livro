package com.mercadolivro.service

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.model.Purchase
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    val purchaseRepository: PurchaseRepository,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchase: Purchase): Purchase {
        val purchaseSaved = purchaseRepository.save(purchase)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseSaved))
        return purchaseSaved
    }

    fun update(purchase: Purchase): Purchase {
        return purchaseRepository.save(purchase)
    }

}