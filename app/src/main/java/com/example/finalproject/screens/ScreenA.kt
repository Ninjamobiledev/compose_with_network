package com.example.finalproject.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.finalproject.CommonComposable.*
import com.example.finalproject.Greeting
import com.example.finalproject.data.model.GithubRepository
import com.example.finalproject.data.viewmodel.DataViewModel
import com.example.finalproject.ui.theme.FinalProjectTheme

@Composable
fun ScreenA(viewModel: DataViewModel, modifier: Modifier) {
    val list=remember{
        viewModel.trendingRepos
    }.collectAsLazyPagingItems()

        
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)){
            items(list) { item->
                item?.let {
                    Log.e("error","items received")
                    listItems(repo = item)
                }

            }
        }
        when(list.loadState.refresh){
            is LoadState.Loading-> showLoader()
            is LoadState.Error-> showErrorAlert(errorMsg =""+(list.loadState.refresh as LoadState.Error).error)
            else -> {}
        }
        when(list.loadState.append){
            is LoadState.Loading-> showLoader()
            is LoadState.Error-> {
                showErrorAlert(errorMsg = "Error in loading further")
                list.retry()
            }
            else->{}
        }
    }
   

@Composable
fun listItems(repo:GithubRepository){
    var visibility= remember {
        repo.visibility=="public"
    }
    val overflow= remember {
        mutableStateOf(false)
    }
    val expanded= remember {
        mutableStateOf(false)
    }
 Card( modifier = Modifier
     .fillMaxSize()
     .padding(8.dp)
     .clip(RoundedCornerShape(30.dp))
     .border(
         3.dp,
         Color.Black, RoundedCornerShape(30.dp)
     )
     .padding(20.dp)) {
     Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
         Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
             CustomText(text =repo.name )
             Row(){
                 Icon( imageVector = if (visibility) Icons.Default.Lock
                 else Icons.Default.Check, contentDescription = "visibility Icon")
                 HorizontalSpacer(value = 5)
                 Text(text = repo.visibility)
             }
         }
         VerticalSpacer(value = 10)
         repo.description?.let {description->
             Column() {
                 Text(text = description, maxLines =if(overflow.value&&expanded.value) Int.MAX_VALUE else 3, overflow = TextOverflow.Clip, onTextLayout = {
                    if(it.hasVisualOverflow){
                        overflow.value=true
                        expanded.value=true
                    }
                 })
                 if(expanded.value){
                     ClickableText(AnnotatedString("more...")){
                      expanded.value=!expanded.value
                     }
                 }


             }
         }
         VerticalSpacer(value = 10)

         Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly){
             repo.language?.let {
                 CustomText(text =repo.language )
             }
             Row(){
                 Icon(imageVector = Icons.Default.Star, contentDescription ="stars" )
                 HorizontalSpacer(value = 5)
                 CustomText(text =repo.stars.toString() )
             }
             CustomText(text =repo.owner.userName)

         }
     }

 }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}