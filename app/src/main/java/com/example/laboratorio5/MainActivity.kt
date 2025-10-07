package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.laboratorio5.ui.detail.PokemonDetailScreen
import com.example.laboratorio5.ui.list.PokemonListScreen
import com.example.laboratorio5.ui.theme.Laboratorio5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio5Theme {
                Surface {
                    PokemonApp()
                }
            }
        }
    }
}

@Composable
fun PokemonApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            PokemonListScreen(
                onPokemonClick = { id, name ->
                    navController.navigate("detail/$id/$name")
                }
            )
        }

        composable(
            route = "detail/{id}/{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            PokemonDetailScreen(
                id = id,
                name = name,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}