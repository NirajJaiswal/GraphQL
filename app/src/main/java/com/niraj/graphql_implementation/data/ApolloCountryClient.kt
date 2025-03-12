package com.niraj.graphql_implementation.data

import com.apollographql.apollo.ApolloClient
import com.niraj.graphql_implementation.domain.CountryClient
import com.niraj.graphql_implementation.domain.DetailCountry
import com.niraj.graphql_implementation.domain.SimpleCountry
import com.niraj.CountriesQuery
import com.niraj.CountryQuery


class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient.query(CountriesQuery()).execute().data?.countries?.map {
            it.toSimpleCountry()
        }.orEmpty()
    }

    override suspend fun getCountry(code: String): DetailCountry? {
        return apolloClient.query(CountryQuery(code))
            .execute().data?.country?.toDetailCountry()
    }
}