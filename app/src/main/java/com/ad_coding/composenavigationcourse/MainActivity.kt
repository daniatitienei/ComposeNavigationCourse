package com.ad_coding.composenavigationcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ad_coding.composenavigationcourse.ui.theme.ComposeNavigationCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationCourseTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.screenOne
                ) {
                    composable(route = Route.screenOne) {
                        ScreenOne(
                            navigateToScreenTwo = {
                                navController.navigate(Route.screenThree) {
                                    popUpTo(route = Route.screenOne) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable(route = Route.screenTwo) {
                        ScreenTwo(
                            navigateToScreenThree = {
                                navController.navigate(Route.screenThree)
                            },
                            navigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(route = Route.screenThree) {
                        ScreenThree(
                            navigateBack = {
                                navController.popBackStack()
                            },
                            navigateBackToScreenOne = {
                                navController.popBackStack(
                                    route = Route.screenOne,
                                    inclusive = false
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

object Route {
    const val screenOne = "screenOne"
    const val screenTwo = "screenTwo"
    const val screenThree = "screenThree"
}

@Composable
fun ScreenOne(
    navigateToScreenTwo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (true) Modifier
                    .fillMaxSize()
                    .width(20.dp) else Modifier.fillMaxSize()
            )
            .run {
                fillMaxSize()
                fillMaxWidth()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 1")
        Button(onClick = navigateToScreenTwo) {
            Text(text = "Navigate to next screen")
        }
    }
}

@Composable
fun ScreenTwo(
    navigateToScreenThree: () -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 2")
        Button(onClick = navigateToScreenThree) {
            Text(text = "Navigate to next screen")
        }
        Button(onClick = navigateBack) {
            Text(text = "Navigate back")
        }
    }
}

@Composable
fun ScreenThree(
    navigateBack: () -> Unit,
    navigateBackToScreenOne: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 3")
        Button(onClick = navigateBack) {
            Text(text = "Navigate back")
        }
        Button(onClick = navigateBackToScreenOne) {
            Text(text = "Navigate back to screen 1")
        }
    }
}