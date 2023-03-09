package com.prasanth.composemultiplatapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }



}
@Composable
internal fun MainScreen(){
    LazyColumn {
        for (i in 0 until 10)
        {
            item {
                Box(modifier = Modifier.padding(10.dp)) {
                Card(modifier = Modifier.fillMaxWidth().height(100.dp).align(Alignment.Center), backgroundColor = Color.LightGray) {
                    Column {
                        Spacer(Modifier.height(5.dp))
                        Text(modifier = Modifier.padding(5.dp), text = "This is the $i th element", color = Color.White)
                    }
                }
                }
            }
        }
    }
}

