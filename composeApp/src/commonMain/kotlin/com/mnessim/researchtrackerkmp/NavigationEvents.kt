package com.mnessim.researchtrackerkmp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NavigationEvents {
    private val _navigateToDetails = MutableStateFlow<Long?>(null)
    val navigateToDetails: StateFlow<Long?> = _navigateToDetails

    fun triggerNavigateToDetails(id: Long?) {
        _navigateToDetails.value = id
    }
}