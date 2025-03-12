package com.niraj.graphql_implementation.domain

data class DetailCountry(
    val name: String,
    val code: String,
    val capital: String,
    val emoji: String,
    val currency: String,
    val languages: List<String>,
    val continent: String
)
