package com.example.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.CommonComposable.TopBar
import com.example.finalproject.data.viewmodel.DataViewModel
import com.example.finalproject.navigation.AppNavigation
import com.example.finalproject.navigation.TopLevel
import com.example.finalproject.ui.theme.FinalProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectTheme {
                // A surface container using the 'background' color from the theme
                val currentSelected= remember {
                    mutableStateOf<TopLevel>(TopLevel.Home)
                }
                val navController= rememberNavController()
                Scaffold(Modifier.fillMaxSize(),
                    topBar = {TopBar(text =currentSelected.value.title , canGoBack = false)},
                bottomBar ={bottomNavigation(listOf(TopLevel.Home,TopLevel.Search,TopLevel.Trending),currentSelected.value){
                    currentSelected.value=it
                   navController.navigate(it.path){
                       popUpTo(navController.graph.startDestinationId)
                       launchSingleTop=true
                   }
                }} ) {paddingValues->
                    val viewModel:DataViewModel by viewModels()
                    AppNavigation(navController = navController, viewModel =viewModel, modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues))
                    }
                }

            }
        }
    }
    @Composable
    private fun bottomNavigation(
        list: List<TopLevel>,
        currentSelected: TopLevel,
        onNavigateToDestination: (TopLevel) -> Unit,

    ) {
        BottomNavigation() {
            list.forEach {
                BottomNavigationItem(selected = currentSelected==it, onClick = { onNavigateToDestination.invoke(it) }, alwaysShowLabel = true, icon ={
                    if(currentSelected==it){
                      Icon(imageVector = it.selectedIcon, contentDescription = "selected icon")
                    }
                    else{
                        Icon(imageVector = it.unselectedIcon, contentDescription = "unselected icon")
                    }
                })
            }
        }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinalProjectTheme {
        Greeting("Android")
    }
}