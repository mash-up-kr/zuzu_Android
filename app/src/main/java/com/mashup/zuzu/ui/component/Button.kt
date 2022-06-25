package com.mashup.zuzu.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/23
 * @Time 5:28 오후
 */

@Composable
fun BackButton(
    modifier: Modifier,
    navController: NavHostController
) {
    Button(
        onClick = { navController.popBackStack() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.Black
        ),
        shape = CircleShape,
        modifier = modifier,
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}

@Composable
fun CloseButton(
    modifier: Modifier,
    navController: NavHostController
) {
    Button(
        onClick = { navController.popBackStack() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.Black
        ),
        shape = CircleShape,
        modifier = modifier,
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Close,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCloseButton() {
    ZuzuAndroidTheme() {
        CloseButton(modifier = Modifier, navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackButton() {
    ZuzuAndroidTheme() {
        BackButton(modifier = Modifier, navController = rememberNavController())
    }
}
