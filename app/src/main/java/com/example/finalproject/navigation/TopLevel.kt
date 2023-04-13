package com.example.finalproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.finalproject.R

sealed class TopLevel(val title:String,val path:String,val selectedIcon:ImageVector,val unselectedIcon:ImageVector) {
    object Home:TopLevel("Home","home", Icons.Filled.Home,Icons.Rounded.Home)
    object Search:TopLevel("Search","search", Icons.Filled.Search,Icons.Rounded.Search)
    object Trending:TopLevel("User","user", Icons.Filled.Person,Icons.Rounded.Person)

}