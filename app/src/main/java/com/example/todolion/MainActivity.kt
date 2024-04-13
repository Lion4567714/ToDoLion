package com.example.todolion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolion.ui.theme.ToDoLionTheme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoLionTheme {
                Scaffold(
                    modifier = Modifier.padding(24.dp)
                ) { padding -> Home(Modifier.padding(padding)) }
            }
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    Column {
        Greeting("Trash", modifier)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Fuck you, $name!",
        modifier = modifier,
        color = Color(0xFFFFFFFF)
    )
}
