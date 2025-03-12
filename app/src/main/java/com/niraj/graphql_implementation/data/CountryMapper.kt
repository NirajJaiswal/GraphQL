package com.niraj.graphql_implementation.data

import com.niraj.CountriesQuery
import com.niraj.CountryQuery
import com.niraj.graphql_implementation.domain.DetailCountry
import com.niraj.graphql_implementation.domain.SimpleCountry

fun CountryQuery.Country.toDetailCountry(): DetailCountry {
    return DetailCountry(
        name = name,
        code = code,
        capital = capital ?: "No capital",
        emoji = emoji,
        currency = currency ?: "No currency",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        name = name,
        code = code,
        capital = capital ?: "No capital",
        emoji = emoji
        )
}
