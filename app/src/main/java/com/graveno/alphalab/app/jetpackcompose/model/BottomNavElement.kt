package com.graveno.alphalab.app.jetpackcompose.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavElement(
    val name : String?,
    val contentDescription : String = name ?: "Bottom Nav Icon",
    val icon : ImageVector,
    val badgeCount : Int = 0,
    val route : String,
)
