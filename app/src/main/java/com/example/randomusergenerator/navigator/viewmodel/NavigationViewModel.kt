package com.example.randomusergenerator.navigator.viewmodel

import androidx.lifecycle.ViewModel
import com.example.randomusergenerator.navigator.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    val navigationManager: NavigationManager
) : ViewModel()
