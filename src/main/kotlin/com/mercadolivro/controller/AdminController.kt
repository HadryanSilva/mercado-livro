package com.mercadolivro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminController {

    @GetMapping("/report")
    fun getReport(): String {
        return "This is a report. Only admin can see this"
    }

}