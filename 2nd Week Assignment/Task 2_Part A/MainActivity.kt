package com.example.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // MaterialTheme wraps our UI to provide consistent styling
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose Foundations") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Text(
                    text = "© 2025 Compose Learning App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "FAB Clicked!", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add item"
                )
            }
        }
    ) { innerPadding ->
        // Using a scrollable column for content that might exceed screen height
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header section
            Text(
                text = "Welcome to Jetpack Compose",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Using InfoCard multiple times with different content
            // This demonstrates the reusability of our component
            InfoCard(
                title = "What is Jetpack Compose?",
                description = "Jetpack Compose is Android's modern toolkit for building " +
                        "native UI. It simplifies and accelerates UI development with " +
                        "less code, powerful tools, and intuitive Kotlin APIs."
            )

            InfoCard(
                title = "Declarative UI",
                description = "With Compose, you describe what your UI should look like, " +
                        "and the framework handles the rest. When data changes, " +
                        "Compose automatically updates the UI to reflect those changes."
            )

            InfoCard(
                title = "Composable Functions",
                description = "The building blocks of Compose are composable functions. " +
                        "They're regular Kotlin functions annotated with @Composable " +
                        "that emit UI elements when called."
            )

            // Demonstrating the alternative card style
            InfoCardAlt(
                title = "Alternative Styling",
                description = "This card uses a border and background instead of " +
                        "elevation, showing how different styling approaches " +
                        "can achieve varied visual effects."
            )

            // Button maintained from Task 1
            Button(
                onClick = {
                    Toast.makeText(context, "Button pressed!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Click Me")
            }
        }
    }
}