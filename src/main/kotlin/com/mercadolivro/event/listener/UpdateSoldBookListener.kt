package com.mercadolivro.event.listener

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(
    val bookService: BookService
) {

    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        val purchase = purchaseEvent.purchase
        purchase.books.forEach { book ->
            bookService.updateSoldBook(book)
        }
        println("Atualizando livros vendidos para a compra ${purchase.id}")
    }

}