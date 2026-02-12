package com.example.composestatedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * STEP 4 — FINAL VERSION. Both counter and name use rememberSaveable.
 *
 * WHAT TO TRY:
 *   1. Type "Alex" → greeting shows "Hello, Alex!"
 *   2. Increment counter to 12.
 *   3. Rotate the device.
 *   4. Both "Hello, Alex!" and "Counter: 12" survive. ✅
 *   5. Press Home, wait 30 seconds, return → still intact.
 *   6. The only way to lose this state is for the user to explicitly
 *      close the app (Back button / swipe from recents).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateDemoScreenFinal() {
    // ✅ Both values survive rotation AND process death.
    var counter by rememberSaveable { mutableIntStateOf(0) }
    var name by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_state_demo)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Dynamic greeting ---
            Text(
                text = if (name.isNotBlank()) {
                    stringResource(R.string.greeting_format, name)
                } else {
                    stringResource(R.string.greeting_default)
                },
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Name input ---
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.label_name)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Counter ---
            Text(
                text = stringResource(R.string.label_counter, counter),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Buttons ---
            Row {
                Button(onClick = { counter++ }) {
                    Text(stringResource(R.string.btn_increment))
                }
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedButton(onClick = { counter = 0 }) {
                    Text(stringResource(R.string.btn_reset))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFinal() {
    MaterialTheme { StateDemoScreenFinal() }
}