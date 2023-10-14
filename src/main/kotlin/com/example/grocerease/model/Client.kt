package com.example.grocerease.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "clients")
data class Client(

    @Id
    @GeneratedValue
    val id: Long,

    @Column(name = "username")
    val username: String,

    @Column(name = "dob")
    val dateOfBirth: LocalDate
)