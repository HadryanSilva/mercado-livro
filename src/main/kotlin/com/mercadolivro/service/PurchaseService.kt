package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.model.Purchase
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    val purchaseRepository: PurchaseRepository,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    fun findAllByCustomer(customerId: Int): List<Purchase> {
        return purchaseRepository.findAllByCustomerId(customerId)
    }

    fun create(purchase: Purchase): Purchase {
        validateBookStatus(purchase)
        val purchaseSaved = purchaseRepository.save(purchase)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseSaved))
        return purchaseSaved
    }

    fun update(purchase: Purchase): Purchase {
        return purchaseRepository.save(purchase)
    }

    fun validateBookStatus(purchase: Purchase) {
        purchase.books.forEach { book ->
            if (book.status != BookStatus.ACTIVE) {
                throw BadRequestException(
                    Errors.ML203.message.format(book.id),
                    Errors.ML203.code
                )
            }
        }
    }

}