package com.mercadolivro.event

import com.mercadolivro.model.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    purchase: Purchase
) : ApplicationEvent(source)