package com.mashup.zuzu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mashup.zuzu.ui.home.ZuzuApp
import com.mashup.zuzu.ui.theme.ProofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProofTheme {
                // A surface container using the 'background' color from the theme

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Black
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ProofTheme.colors.Black
                ) {
                    ZuzuApp()
                }
            }
        }
    }
}
