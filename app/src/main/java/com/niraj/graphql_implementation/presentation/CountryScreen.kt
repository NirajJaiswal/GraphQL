package com.niraj.graphql_implementation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.niraj.graphql_implementation.domain.DetailCountry
import com.niraj.graphql_implementation.domain.SimpleCountry

@Composable
fun CountryScreen(
    state: CountriesViewModel.CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onSelectCountry(country.code)
                            }
                            .padding(16.dp))

                }
            }
        }

        if (state.selectedCountry != null) {
            CountryDialog(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(Color.White)
                    .padding(30.dp),
                country = state.selectedCountry,
                onDismiss = onDismissCountryDialog
            )
        }
    }
}

@Composable
fun CountryDialog(
    modifier: Modifier = Modifier,
    country: DetailCountry,
    onDismiss: () -> Unit
) {
    val joinedLanguage = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row {
                Text(
                    text = country.emoji,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(26.dp))

                Text(
                    text = country.name,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Language(s): $joinedLanguage")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(26.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = country.capital,
                fontSize = 20.sp
            )

        }
    }
}
