package com.example.composestatedemo

import kotlinx.serialization.Serializable

/**
 * Sealed class that defines every screen (route) in the app.
 *
 * Each nested object represents one destination. Using @Serializable
 * lets Navigation Compose serialize/deserialize these objects
 * automatically — no manual string routes needed.
 *
 * "data object" (not "object") is used because it generates
 * correct equals/hashCode/toString for a singleton.
 */
sealed class AppRoutes {

    /** The home / landing screen. */
    @Serializable
    data object Home : AppRoutes()

    /** The detail screen (no arguments for now). */
    @Serializable
    data object Detail : AppRoutes()
}