package com.example.composestatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StateDemoScreen()
                }
            }
        }
    }
}

/**
 * Step 1: Counter with remember (rotation problem)
 *
 * This composable demonstrates the problem with using `remember` for state
 * that needs to survive configuration changes like device rotation.
 *
 * The counter value is stored using `remember { mutableStateOf(0) }`.
 * When the device is rotated, the Activity is recreated (destroyed and created again),
 * which causes all composables to be recomposed from scratch. Since `remember` only
 * survives recomposition (not Activity recreation), the counter resets to 0.
 */
@Composable
fun StateDemoScreen() {
    // Step 1: Using remember - state will be LOST on rotation
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Greeting header
        Text(
            text = "State Demo - remember",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Counter display
        Text(
            text = "Counter: $count",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Increment button
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { count++ }) {
                Text("Increment")
            }

            // Reset button
            OutlinedButton(onClick = { count = 0 }) {
                Text("Reset")
            }
        }
    }
}

/*
 * WRITTEN EXPLANATION (Step 1):
 *
 * Why did the counter reset on rotation?
 *
 * When the device is rotated, Android performs an Activity recreation — the current
 * Activity is destroyed (onDestroy) and a new instance is created (onCreate). This is
 * a configuration change. The `remember` function only preserves state across
 * recompositions within the same Activity instance. Since the Activity is destroyed
 * and recreated, all `remember`-based state is lost, and the counter resets to its
 * initial value of 0.
 */
