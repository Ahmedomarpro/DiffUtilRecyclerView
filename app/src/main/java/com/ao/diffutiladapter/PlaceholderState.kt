package com.ao.diffutiladapter

sealed class PlaceholderState {
    object Idle : PlaceholderState()
    object Loading : PlaceholderState()
    data class Error(val throwable: Throwable) : PlaceholderState()

}