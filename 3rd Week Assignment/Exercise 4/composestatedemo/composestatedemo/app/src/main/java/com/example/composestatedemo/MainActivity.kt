package com.example.composestatedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    // ---- Existing lifecycle logging (from earlier exercises) ----
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LifecycleDemo", "onCreate called")

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    override fun onStart()   { super.onStart();   Log.d("LifecycleDemo", "onStart called") }
    override fun onResume()  { super.onResume();  Log.d("LifecycleDemo", "onResume called") }
    override fun onPause()   { super.onPause();   Log.d("LifecycleDemo", "onPause called") }
    override fun onStop()    { super.onStop();    Log.d("LifecycleDemo", "onStop called") }
    override fun onDestroy() { super.onDestroy(); Log.d("LifecycleDemo", "onDestroy called") }
}

/**
 * Top-level composable that owns the NavController and defines
 * the navigation graph for the entire app.
 *
 * rememberNavController() creates a NavHostController that survives
 * recomposition and is scoped to this composable's lifecycle.
 *
 * NavHost is the container that swaps composable screens in and out
 * based on the current back-stack entry.
 */
@Composable
fun AppNavigation() {
    // 1. Create the NavController — the central object that tracks
    //    which screen is showing and manages the back stack.
    val navController = rememberNavController()

    // 2. NavHost defines the navigation graph.
    //    startDestination = the route shown when the app launches.
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Home   // type-safe route object, not a string!
    ) {
        // 3. Register each destination using the reified composable<T> function.
        //    The type parameter matches the @Serializable route object.

        composable<AppRoutes.Home> {
            HomeScreen(
                onNavigateToDetail = {
                    // Navigate forward — pushes Detail onto the back stack.
                    navController.navigate(AppRoutes.Detail)
                }
            )
        }

        composable<AppRoutes.Detail> {
            DetailScreen(
                onNavigateBack = {
                    // Pop the current screen off the back stack,
                    // returning to the previous screen (Home).
                    navController.popBackStack()
                }
            )
        }
    }
}