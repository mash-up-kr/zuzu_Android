package com.mashup.zuzu.ui.review

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.compose.component.ShareReviewCard
import com.mashup.zuzu.compose.component.ShareReviewCardWithRenderScript
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.ReviewShareCard
import com.mashup.zuzu.util.saveBitmapToStorage
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@Composable
fun ReviewShareCardRoute(
    viewModel: ReviewShareCardViewModel,
    onButtonClick: () -> Unit
) {
    val reviewShareCard by viewModel.reviewShareCardState.collectAsState()

    ReviewShareCardScreen(
        reviewShareCard,
        onButtonClick
    )
}

@Composable
fun ReviewShareCardScreen(
    reviewShareCard: ReviewShareCard,
    onButtonClick: () -> Unit
) {
    val captureController = rememberCaptureController()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 54.dp)
    ) {
        Text(
            text = "리뷰 완성",
            style = ProofTheme.typography.headingXL,
            color = ProofTheme.color.white,
            textAlign = TextAlign.Center
        )

        Text(
            text = "proof 테이스팅 노트를 자랑해보세요.",
            style = ProofTheme.typography.bodyL,
            color = ProofTheme.color.gray200,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            ReviewCard(
                reviewShareCard = reviewShareCard,
                captureController = captureController,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            Button(
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray400),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text(
                    text = "확인",
                    style = ProofTheme.typography.buttonL,
                    textAlign = TextAlign.Center,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = { captureController.capture(Bitmap.Config.ARGB_8888) },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(2f)
                    .height(52.dp)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "이미지로 공유",
                    style = ProofTheme.typography.buttonL,
                    textAlign = TextAlign.Center,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun ReviewCard(
    reviewShareCard: ReviewShareCard,
    captureController: CaptureController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Capturable(
        controller = captureController,
        onCaptured = { imageBitmap, throwable ->
            if (imageBitmap != null) {
                saveBitmapToStorage(
                    context = context,
                    bitmap = imageBitmap.asAndroidBitmap()
                )
            }
            if (throwable != null) {
                // error
            }
        }
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ShareReviewCard(
                reviewShareCard = reviewShareCard,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 10.dp)
            )
        } else {
            ShareReviewCardWithRenderScript(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 10.dp),
                reviewShareCard = reviewShareCard,
                blurImage = reviewShareCard.wine.bitmap
            )
        }
    }
}

fun getDrinkCategoryColor(category: String): Long {
    val color = when (category) {
        "Whisky" -> 0xFFDFF8A2
        "Wine" -> 0xFFC4BEFF
        "Cocktail" -> 0xFFF5C0E6
        "Traditional" -> 0xFFFDE9A4
        "Soju" -> 0xFFC0DFF5
        "Beer" -> 0xFFFFD9BE
        else -> 0xFFC0F5E2
    }

    return color
}

fun getDrinkCategoryToColorWithKorean(category: String): Long {
    val color = when (category) {
        "위스키" -> 0xFFDFF8A2
        "와인" -> 0xFFC4BEFF
        "칵테일" -> 0xFFF5C0E6
        "전통주" -> 0xFFFDE9A4
        "소주" -> 0xFFC0DFF5
        "맥주" -> 0xFFFFD9BE
        else -> 0xFFC0F5E2
    }
    return color
}
