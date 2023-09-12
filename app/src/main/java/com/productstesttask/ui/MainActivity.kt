package com.productstesttask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.productstesttask.domain.base.navigation.Destination
import com.productstesttask.ui.features.detail.DetailProductScreen
import com.productstesttask.ui.features.main.MainScreen
import com.productstesttask.ui.theme.ProductsTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsTestTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Destination.Splash.route
    ) {
        composable(Destination.Splash.route) {
            AnimatedSplashScreen(navController)
        }
        composable(Destination.Main.route) {
            MainScreen(navController)
        }
        composable(
            route = Destination.ProductDetail.route,
            arguments = listOf(
                navArgument(
                    name = "product_title"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            DetailProductScreen(
                navController = navController,
                productTitle = it.arguments?.getString("product_title").orEmpty()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductsTestTaskTheme {
        Main()
    }
}