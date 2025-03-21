package com.niraj.graphql_implementation.domain

interface CountryClient {

    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailCountry?
}