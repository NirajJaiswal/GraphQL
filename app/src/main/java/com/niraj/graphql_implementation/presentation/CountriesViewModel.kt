package com.niraj.graphql_implementation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niraj.graphql_implementation.domain.DetailCountry
import com.niraj.graphql_implementation.domain.GetCountriesUseCase
import com.niraj.graphql_implementation.domain.GetCountryUseCase
import com.niraj.graphql_implementation.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code)
                )
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update {
            it.copy(
                selectedCountry = null
            )
        }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailCountry? = null
    )
}