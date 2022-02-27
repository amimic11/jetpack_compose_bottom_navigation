package com.graveno.alphalab.app.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.graveno.alphalab.app.jetpackcompose.AppConstants.chat
import com.graveno.alphalab.app.jetpackcompose.AppConstants.home
import com.graveno.alphalab.app.jetpackcompose.AppConstants.settings
import com.graveno.alphalab.app.jetpackcompose.model.BottomNavElement
import com.graveno.alphalab.app.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    JetpackComposeTheme(
        darkTheme = true,
    ) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    elements = ArrayList(
                        listOf(
                            BottomNavElement(
                                route = home,
                                contentDescription = "this is home screen",
                                name = "Home",
                                icon = Icons.Default.Home
                            ),
                            BottomNavElement(
                                route = chat,
                                contentDescription = "this is chat screen",
                                name = "Chat",
                                icon = Icons.Default.Email
                            ),
                            BottomNavElement(
                                route = settings,
                                contentDescription = "this is setting screen",
                                name = "Setting",
                                icon = Icons.Default.Settings
                            )
                        )
                    ),
                    navController = navController,
                    onElementSelected = { element ->
                        navController.navigate(element.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.08f)
                )
            }
        ) {
            NavigationHandler(navHostController = navController)
        }
    }
}

//region compose...
@Composable
fun NavigationHandler(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = home
    ) {
        composable(
            route = home
        ) {
            HomeScreen()
        }
        composable(
            route = chat
        ) {
            ChatScreen()
        }
        composable(
            route = settings
        ) {
            SettingScreen()
        }
    }
}

@Composable
fun BottomNavBar(
    elements : ArrayList<BottomNavElement>,
    navController: NavController,
    modifier: Modifier,
    onElementSelected : (BottomNavElement) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Cyan,
        elevation = 7.dp
    ) {
        elements.forEach { element ->
            //get current selected compose...
            val selected = element.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onElementSelected(element) },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Black,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        when {
                            element.badgeCount > 0 -> {
                                BadgedBox(
                                    badge = {
                                        Text(text = element.badgeCount.toString())
                                    }
                                ) {
                                    Icon(
                                        imageVector = element.icon,
                                        contentDescription = element.name
                                    )
                                }
                            }
                            else -> {
                                Icon(
                                    imageVector = element.icon,
                                    contentDescription = element.name
                                )
                            }
                        }
                        when (selected) {
                            true -> {
                                Text(
                                    text = element.name ?: "",
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.Cursive,
                                    fontSize = 19.sp
                                )
                            }
                            else -> {}
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Setting Screen")
    }
}
//endregion