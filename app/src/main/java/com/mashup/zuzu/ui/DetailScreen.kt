package com.mashup.zuzu.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.theme.ProofTheme

@Composable
fun DetailScreen(
    navigateToReviewWrite: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(24.dp)
    ) {
        Box() {
            //TODO : Image 만들어주기
        }

        Button(
            onClick = navigateToReviewWrite,
            colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.do_review),
                style = ProofTheme.typography.buttonL,
                color = ProofTheme.color.white
            )
        }
    }
}