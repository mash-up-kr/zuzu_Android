package com.mashup.zuzu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.network.NetworkMonitor
import com.mashup.zuzu.ui.worldcup.WorldcupActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProofTheme {
                // A surface container using the 'background' color from the theme
                val systemUiController = rememberSystemUiController()

                val viewModel: MainActivityViewModel = hiltViewModel()

                val visible = viewModel.connection.collectAsState()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color(0xFF1C1C26)
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ZuzuApp(
                        onWorldCupButtonClick = {
                            startActivity(Intent(this, WorldcupActivity::class.java))
                        }
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AnimatedVisibility(
                            visible = !visible.value,
                            modifier = Modifier.align(Alignment.TopCenter)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .background(color = ProofTheme.color.primary300)
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "네트워크 연결을 확인해주세요",
                                    style = ProofTheme.typography.bodyS,
                                    color = ProofTheme.color.white
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
