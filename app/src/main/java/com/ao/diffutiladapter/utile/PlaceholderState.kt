package com.ao.diffutiladapter.utile

sealed class PlaceholderState {
    object Idle : PlaceholderState()
    object Loading : PlaceholderState()
    data class Error(val throwable: Throwable) : PlaceholderState()

}