package com.example.todolion

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todolion.ui.theme.ToDoLionTheme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color(0xFF000000).toArgb()
            window.statusBarColor = Color(0xFF000000).toArgb()
            window.navigationBarColor = Color(0xFF000000).toArgb()
            ToDoLionTheme {
                Scaffold(
                    topBar = { TopBar() },
                    containerColor = Color(0xFF000000),
                ) { padding -> Home(Modifier.padding(padding)) }
            }
        }
    }
}

private val showDialog = mutableStateOf(false)
private val myText = mutableStateOf("")
//private val progress by remember { mutableStateOf(0.33f) }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("My Title")}
    )
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showDialog.value) {
                Alert()
            }

            Button(
                onClick = {
                    showDialog.value = true
                }
            ) {
                Text("Press me!")
            }
        }
        OutlinedTextField(
            value = myText.value,
            onValueChange = { myText.value = it },
            label = { Text("My Label")}
        )

        var progress by remember { mutableFloatStateOf(0.0f) }
        LinearProgressIndicator(
            progress = progress,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { progress += 0.1f }
            ) {
                Text("+")
            }
            Button(
                onClick = { progress -= 0.1f }
            ) {
                Text("-")
            }
        }

        val scope = rememberCoroutineScope()
        Button(
            onClick = {
                scope.launch {
                    load { lambProgress -> progress = lambProgress }
                }
            }
        ) {
            Text("Press me to begin loading")
        }
    }
}

suspend fun load(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Composable
fun Alert() {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showDialog.value = false
                }
            ) {
                Text(myText.value)
            }
        }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Fuck you, $name!",
        modifier = modifier,
        color = Color(0xFFFFFFFF)
    )
}
