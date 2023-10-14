package com.example.grocerease.exceptions

import org.springframework.http.HttpStatus

open class ResponseException(val httpStatus: HttpStatus, message: String) : RuntimeException(message)