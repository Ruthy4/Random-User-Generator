package com.example.randomusergenerator.view

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {
    fun createGraph(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController)
}
