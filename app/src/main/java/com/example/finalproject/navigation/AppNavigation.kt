package com.example.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.data.viewmodel.DataViewModel
import com.example.finalproject.screens.ScreenA
import com.example.finalproject.screens.ScreenB
import com.example.finalproject.screens.ScreenC

@Composable
fun AppNavigation( modifier:Modifier,navController: NavHostController, viewModel: DataViewModel){
    NavHost( navController = navController, startDestination = TopLevel.Home.path) {
        composable(route =TopLevel.Home.path){
           ScreenA(viewModel,modifier)
        }

        composable(route=TopLevel.Search.path){
            ScreenB(viewModel,modifier)
        }
        composable(route=TopLevel.Trending.path){
            ScreenC(viewModel,modifier)
        }

    }
}