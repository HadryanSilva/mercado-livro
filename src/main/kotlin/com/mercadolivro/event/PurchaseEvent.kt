package com.mercadolivro.event

import com.mercadolivro.model.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchase: Purchase
) : ApplicationEvent(source)