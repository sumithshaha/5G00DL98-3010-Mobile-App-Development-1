package com.example.helloworld

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

/**
 * A simple login form demonstrating Column, Row, and Spacer usage.
 *
 * @param onLoginClick Callback invoked when the user clicks Login
 * @param onCancelClick Callback invoked when the user clicks Cancel
 * @param modifier Optional modifier for external layout customization
 */
@Composable
fun LoginForm(
    onLoginClick: (username: String, password: String) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State variables to hold the current text in each field.
    // "remember" keeps the value across recompositions.
    // "mutableStateOf" makes the value observable so the UI updates when it changes.
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Column arranges all form elements vertically
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Greeting text at the top of the form
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Please sign in to continue",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Spacer creates vertical space between the greeting and form fields
        // This is more explicit than using padding when you want exact spacing
        Spacer(modifier = Modifier.height(24.dp))

        // Username text field
        OutlinedTextField(
            value = username,
            onValueChange = { newValue ->
                // This lambda is called every time the user types
                username = newValue
            },
            label = { Text("Username") },
            placeholder = { Text("Enter your username") },
            singleLine = true,  // Prevents multi-line input
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email  // Shows @ symbol on keyboard
            )
        )

        // Spacer between the two text fields
        Spacer(modifier = Modifier.height(16.dp))

        // Password text field with visual transformation to hide characters
        OutlinedTextField(
            value = password,
            onValueChange = { newValue ->
                password = newValue
            },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            // PasswordVisualTransformation replaces characters with dots
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        // Spacer before the buttons
        Spacer(modifier = Modifier.height(24.dp))

        // Row arranges the two buttons horizontally
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center  // Centers the buttons
        ) {
            // Cancel button using OutlinedButton for visual distinction
            OutlinedButton(
                onClick = onCancelClick
            ) {
                Text("Cancel")
            }

            // Spacer creates horizontal space between buttons
            Spacer(modifier = Modifier.width(16.dp))

            // Login button is the primary action, so it uses filled Button
            Button(
                onClick = {
                    onLoginClick(username, password)
                }
            ) {
                Text("Login")
            }
        }
    }
}