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
    // LocalContext.current gives us access to the Android Context,
    // which we need to show a Toast message
    val context = LocalContext.current

    // Scaffold provides the structural layout for a Material Design screen.
    // We pass in the different "slots" as parameters.
    Scaffold(
        // TopAppBar sits at the top of the screen with the app title
        topBar = {
            TopAppBar(
                title = { Text("Compose Foundations") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },

        // BottomAppBar sits at the bottom, often used for navigation or actions
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

        // FloatingActionButton "floats" above content, typically for primary actions
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Toast.makeText creates a small popup message
                    // LENGTH_SHORT means it disappears after a brief moment
                    Toast.makeText(context, "FAB Clicked!", Toast.LENGTH_SHORT).show()
                }
            ) {
                // Icon displays a vector graphic inside the FAB
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add item"
                )
            }
        }
    ) { innerPadding ->
        // The content lambda receives innerPadding which accounts for
        // space taken by top bar, bottom bar, etc.
        // We MUST apply this padding to avoid content being hidden behind bars.

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // Critical: apply scaffold's padding
                .padding(16.dp),        // Additional padding for aesthetics
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First Text element with headline styling
            Text(
                text = "Welcome to Jetpack Compose",
                style = MaterialTheme.typography.headlineMedium
            )

            // Second Text element with body styling
            Text(
                text = "This screen demonstrates Scaffold layout with top bar, " +
                        "bottom bar, and floating action button.",
                style = MaterialTheme.typography.bodyLarge
            )

            // Button with padding modifier for touch target size
            Button(
                onClick = {
                    Toast.makeText(context, "Button pressed!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Click Me")
            }
        }
    }
}