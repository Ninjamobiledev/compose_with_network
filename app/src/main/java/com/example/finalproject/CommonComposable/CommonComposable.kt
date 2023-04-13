package com.example.finalproject.CommonComposable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun TopScreen(){

}
@Composable
fun showErrorAlert(errorMsg:String, callback: (() -> Unit)? =null){
    val openDialog = remember { mutableStateOf(true) }
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = { Text("Error") },
        text = { Text(errorMsg) },
        confirmButton = {
            TextButton(onClick = { openDialog.value=false}) {
                Text(text = "Ok")
            }
        }
    )
}
@Composable
fun showLoader() {
  Box(modifier = Modifier.fillMaxSize()){
  CircularProgressIndicator(modifier = Modifier
      .size(40.dp)
      .align(Alignment.Center),color= MaterialTheme.colors.primary)
  }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TopBar(text:String,canGoBack: Boolean, onBackPress: (() -> Unit)? = null) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(4.dp, Color.Black)

    ) {
        if (canGoBack) {
            IconButton(onClick = {
                onBackPress!!.invoke()
            }, modifier = Modifier.padding(end = 3.dp)) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Back icon"
                )
            }
        }
        Text(
            text = text, modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp), style = TextStyle(
                fontSize = TextUnit(
                    20f,
                    TextUnitType.Sp
                ), fontWeight = FontWeight.Bold
            )
        )
    }
}
@Composable
fun VerticalSpacer(value:Int){
    Spacer(modifier = Modifier.height(value.dp))
}
@Composable
fun HorizontalSpacer(value:Int){
    Spacer(modifier = Modifier.width(value.dp))
}
@OptIn(ExperimentalUnitApi::class)
@Composable
fun CustomText(text:String){
    Text(text=text,modifier= Modifier.padding(2.dp), style = TextStyle(fontSize = TextUnit(20f,
        TextUnitType.Sp), color = Color.Gray)
    )
}
@OptIn(ExperimentalUnitApi::class)
@Composable
fun CustomButton(text:String,onClick:()->Unit){

    Button(onClick = { onClick.invoke()}, modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp)
        .clip(
            RoundedCornerShape(20.dp)
        )) {
        Text(text = text,modifier= Modifier.padding(5.dp), style = TextStyle(fontSize = TextUnit(20f, TextUnitType.Sp)))
    }
}
