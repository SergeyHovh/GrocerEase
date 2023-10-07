package com.example.grocerease.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/main")
class MainController {

    @GetMapping("hello")
    fun hello() = ResponseEntity.status(200).body("Hello World")

}