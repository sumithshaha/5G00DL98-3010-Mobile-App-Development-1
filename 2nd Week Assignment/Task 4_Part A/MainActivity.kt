package com.example.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.helloworld.InfoCard
import com.example.helloworld.LoginForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                // Using stringResource for the app name
                title = { Text(stringResource(R.string.app_name)) },
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
                    text = stringResource(R.string.copyright_text),
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
                    Toast.makeText(
                        context,
                        context.getString(R.string.fab_clicked_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_item_description)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Displaying the image from drawable resources
            // painterResource() loads drawable resources
            Image(
                painter = painterResource(id = R.drawable.ic_compose_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 16.dp)
            )

            // Header using string resources
            Text(
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = stringResource(R.string.welcome_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Info cards using string resources
            InfoCard(
                title = stringResource(R.string.card_compose_title),
                description = stringResource(R.string.card_compose_description)
            )

            InfoCard(
                title = stringResource(R.string.card_declarative_title),
                description = stringResource(R.string.card_declarative_description)
            )

            InfoCard(
                title = stringResource(R.string.card_composables_title),
                description = stringResource(R.string.card_composables_description)
            )

            // Login form (already updated to use string resources)
            LoginForm(
                onLoginClick = { username, password ->
                    // Using getString with format arguments for the toast
                    Toast.makeText(
                        context,
                        context.getString(R.string.login_attempt_message, username),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onCancelClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.login_cancelled_message),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Button using string resources
            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.button_pressed_message),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(stringResource(R.string.click_me_button))
            }
        }
    }
}
