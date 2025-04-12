package com.mercadolivro.event.listener

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(
    val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        val purchase = purchaseEvent.purchase
        println("Atualizando livros vendidos para a compra ${purchase.id}")
        purchase.books.forEach { book ->
            bookService.updateSoldBook(book)
        }
    }

}