package com.vpr.gasoline_prices_app.ui

sealed class Screen(val route: String) {

    object DatesScreen : Screen("dates")
    object DetailsScreen: Screen("prices")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")

            }
        }
    }
}