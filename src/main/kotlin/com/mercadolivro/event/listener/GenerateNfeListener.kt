package com.mercadolivro.event.listener

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
    val purchaseService: PurchaseService,
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        val nfe = UUID.randomUUID().toString()
        val purchase = purchaseEvent.purchase.copy(nfe = nfe)
        println("Gerando NFE para a compra ${purchase.id}")
        purchaseService.update(purchase)
    }

}