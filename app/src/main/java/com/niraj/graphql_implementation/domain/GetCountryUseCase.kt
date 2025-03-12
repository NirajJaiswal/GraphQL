package com.niraj.graphql_implementation.domain

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailCountry? {
        return countryClient.getCountry(code)
    }

}