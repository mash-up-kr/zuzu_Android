package com.mashup.zuzu.compose.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.material.placeholder
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.ReviewShareCard
import com.mashup.zuzu.ui.review.getDrinkCategoryColor
import com.mashup.zuzu.ui.review.getDrinkCategoryToColorWithKorean
import java.util.*

/**
 * @Created by 김현국 2022/08/20
 */

@Composable
fun ReviewCard(
    reviewShareCard: ReviewShareCard,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(396.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            ProofTheme.color.gray500,
                            ProofTheme.color.gray500
                        )
                    ),
                    alpha = 0.6f,
                    shape = RoundedCornerShape(12.dp)
                )
                .align(Alignment.BottomCenter)
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                BlurWithOuterHeightImage(blurOuterHeight = 740.dp.value) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(reviewShareCard.wine.imageUrl).build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_share_logo),
                contentDescription = "",
                modifier = Modifier
                    .padding(12.dp)
                    .size(70.dp, 24.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 30.dp),
                horizontalAlignment = Alignment.End
            ) {
                val categoryColor = getDrinkCategoryColor(reviewShareCard.wine.category)

                Text(
                    text = "NO.002",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.gray500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .background(
                            color = Color(categoryColor),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp)
                )

                Text(
                    text = reviewShareCard.userReview.createdAt.split("T")[0],
                    style = ProofTheme.typography.headingXS,
                    color = Color(categoryColor),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(
                            color = ProofTheme.color.gray500,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp)
                )
            }

            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                ProofTheme.color.gray500,
                                ProofTheme.color.gray500
                            )
                        ),
                        alpha = 0.6f,
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    ),
//                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Drink Description
                 */
                /**
                 * Drink Description
                 */

                /**
                 * Drink Description
                 */
                /**
                 * Drink Description
                 */
                /**
                 * Drink Description
                 */
                /**
                 * Drink Description
                 */
                /**
                 * Drink Description
                 */

                /**
                 * Drink Description
                 */
                Text(
                    text = reviewShareCard.wine.name,
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.headingS,
                    maxLines = 2,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = ProofTheme.color.black)
                )

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */

                /**
                 * Drink Info
                 */
                Text(
                    text = reviewShareCard.wine.category,
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.headingXS,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    text = "ALC ${reviewShareCard.wine.alc}% . FROM reviewShareCard.wine.origin",
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.buttonM,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = ProofTheme.color.black)
                )

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */

                /**
                 * Review Info
                 */
                Text(
                    text = "${reviewShareCard.userReview.mood} ${reviewShareCard.userReview.weather},${reviewShareCard.userReview.time}" +
                        "\n${reviewShareCard.userReview.isHeavy}|${reviewShareCard.userReview.isBitter}|${reviewShareCard.userReview.isBurning}|${reviewShareCard.userReview.isStrong}",
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.bodyS,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}

@Composable
fun ShareReviewCardWithRenderScript(
    modifier: Modifier = Modifier
        .padding(top = 16.dp, end = 47.dp, start = 24.dp, bottom = 24.dp)
        .fillMaxSize(),
    reviewShareCard: ReviewShareCard,
    blurImage: Bitmap?
) {
    var rowheight by remember { mutableStateOf<Dp?>(null) }
    var fullHeight by remember { mutableStateOf<Dp?>(null) }
    Box(modifier = modifier) { // 총 카드의 크기
        Box( // 뒷배경 카드
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(color = ProofTheme.color.gray500, shape = RoundedCornerShape(12.dp))
                .align(Alignment.BottomCenter)

        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .align(Alignment.CenterHorizontally)
                    .drawWithContent {
                        this@drawWithContent.drawContent()
                        fullHeight = size.height.toDp()
                        rowheight = (size.height / 6f).toDp()
                    }

            ) {
                val blurOuterHeight = with(LocalDensity.current) {
                    rowheight?.let { fullHeight?.minus(it) }
                        ?.toPx()
                }

                if (blurOuterHeight != null) {
                    Box {
                        SubcomposeAsyncImage(
                            model = reviewShareCard.wine.imageUrl,
                            contentDescription = null
                        ) {
                            val state = painter.state
                            if (state is AsyncImagePainter.State.Loading) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .clip(shape = RoundedCornerShape(16.dp))
                                        .placeholder(
                                            visible = true,
                                            color = ProofTheme.color.gray600
                                        )
                                )
                            }
                            if (state is AsyncImagePainter.State.Success) {
                                BlurImageInShareCardWithRenderScript(
                                    blurOuterHeight = blurOuterHeight,
                                    content = {
                                        AsyncImage(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(12.dp)),
                                            model = reviewShareCard.wine.imageUrl,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    },
                                    blurImageComposable = {
                                        if (blurImage != null) {
                                            Image(
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .fillMaxWidth(),
                                                bitmap = blurImage.asImageBitmap(),
                                                contentScale = ContentScale.Crop,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                )

                                rowheight?.let {
                                    Box(
                                        modifier = Modifier.fillMaxWidth().height(it).align(Alignment.BottomCenter)
                                            .background(color = ProofTheme.color.gray500.copy(alpha = 0.7f), shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(start = 15.dp, end = 15.dp).align(Alignment.Center),
                                            text = reviewShareCard.wine.name,
                                            color = ProofTheme.color.white,
                                            style = ProofTheme.typography.headingS,
                                            maxLines = 2
                                        )
                                    }
                                }
                            }
                        }
                    }

                    rowheight.let {
                        if (rowheight != null) {
                            Box(
                                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = rowheight!! + 10.dp, end = 20.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.End
                                ) {
                                    val categoryColor = getDrinkCategoryToColorWithKorean(reviewShareCard.wine.category)

                                    Text(
                                        text = "NO. " + reviewShareCard.userReview.id.toString().padStart(3, '0'),
                                        style = ProofTheme.typography.headingXS,
                                        color = ProofTheme.color.gray500,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(bottom = 14.dp)
                                            .background(
                                                color = Color(categoryColor),
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .padding(6.dp)
                                    )

                                    Text(
                                        text = reviewShareCard.userReview.createdAt.split("T")[0],
                                        style = ProofTheme.typography.headingXS,
                                        color = Color(categoryColor),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(
                                                color = ProofTheme.color.gray500,
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .padding(6.dp)
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.padding(start = 22.dp, top = 22.dp).width(75.dp).height(25.dp).align(Alignment.TopStart)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.ic_share_logo),
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ProofTheme.color.black)
            )
            Text(
                text = reviewShareCard.wine.category,
                color = ProofTheme.color.white,
                style = ProofTheme.typography.headingXS,
                modifier = Modifier.padding(horizontal = 8.dp).padding(top = 12.dp)
            )

            Text(
                text = "ALC ${reviewShareCard.wine.alc}% . FROM ${reviewShareCard.wine.origin}",
                color = ProofTheme.color.white,
                style = ProofTheme.typography.buttonM,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ProofTheme.color.black)
            )
            Text(
                text = "${reviewShareCard.userReview.mood.uppercase(Locale.getDefault())} ${reviewShareCard.userReview.weather.uppercase(Locale.getDefault())}, ${reviewShareCard.userReview.time.uppercase(Locale.getDefault())}" +
                    "\n${reviewShareCard.userReview.isHeavy.uppercase(Locale.getDefault())} | ${reviewShareCard.userReview.isBitter.uppercase(Locale.getDefault())} | ${reviewShareCard.userReview.isBurning.uppercase(Locale.getDefault())} | ${reviewShareCard.userReview.isStrong.uppercase(Locale.getDefault())}",
                color = ProofTheme.color.white,
                style = ProofTheme.typography.bodyS,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}

@Composable
fun ShareReviewCard(
    modifier: Modifier = Modifier
        .padding(top = 16.dp, end = 47.dp, start = 24.dp, bottom = 24.dp)
        .fillMaxSize(),
    reviewShareCard: ReviewShareCard
) {
    var rowheight by remember { mutableStateOf<Dp?>(null) }
    var fullHeight by remember { mutableStateOf<Dp?>(null) }
    Box(modifier = modifier) { // 총 카드의 크기
        Box( // 뒷배경 카드
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(color = ProofTheme.color.gray500, shape = RoundedCornerShape(12.dp))
                .align(Alignment.BottomCenter)

        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .align(Alignment.CenterHorizontally)
                    .drawWithContent {
                        this@drawWithContent.drawContent()
                        fullHeight = size.height.toDp()
                        rowheight = (size.height / 6f).toDp()
                    }

            ) {
                val blurOuterHeight = with(LocalDensity.current) {
                    rowheight?.let { fullHeight?.minus(it) }
                        ?.toPx()
                }

                if (blurOuterHeight != null) {
                    Box {
                        SubcomposeAsyncImage(
                            model = reviewShareCard.wine.imageUrl,
                            contentDescription = null
                        ) {
                            val state = painter.state

                            if (state is AsyncImagePainter.State.Loading) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .clip(shape = RoundedCornerShape(16.dp))
                                        .placeholder(
                                            visible = true,
                                            color = ProofTheme.color.gray600
                                        )
                                )
                            }
                            if (state is AsyncImagePainter.State.Success) {
                                BlurImageInShareCard(blurOuterHeight = blurOuterHeight) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(12.dp)),
                                        model = reviewShareCard.wine.imageUrl,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                rowheight?.let {
                                    Box(
                                        modifier = Modifier.fillMaxWidth().height(it).align(Alignment.BottomCenter)
                                            .background(color = ProofTheme.color.gray500.copy(alpha = 0.7f))
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(start = 15.dp, end = 15.dp).align(Alignment.Center),
                                            text = reviewShareCard.wine.name,
                                            color = ProofTheme.color.white,
                                            style = ProofTheme.typography.headingS,
                                            maxLines = 2
                                        )
                                    }
                                }
                            }
                        }
                    }

                    rowheight.let {
                        if (rowheight != null) {
                            Box(
                                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = rowheight!! + 10.dp, end = 20.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.End
                                ) {
                                    val categoryColor = getDrinkCategoryToColorWithKorean(reviewShareCard.wine.category)

                                    Text(
                                        text = "NO. " + reviewShareCard.userReview.id.toString().padStart(3, '0'),
                                        style = ProofTheme.typography.headingXS,
                                        color = ProofTheme.color.gray500,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(bottom = 14.dp)
                                            .background(
                                                color = Color(categoryColor),
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .padding(6.dp)
                                    )

                                    Text(
                                        text = reviewShareCard.userReview.createdAt.split("T")[0],
                                        style = ProofTheme.typography.headingXS,
                                        color = Color(categoryColor),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(
                                                color = ProofTheme.color.gray500,
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .padding(6.dp)
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.padding(start = 22.dp, top = 22.dp).width(75.dp).height(25.dp).align(Alignment.TopStart)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.ic_share_logo),
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ProofTheme.color.black)
            )
            Text(
                text = reviewShareCard.wine.category,
                color = ProofTheme.color.white,
                style = ProofTheme.typography.headingXS,
                modifier = Modifier.padding(horizontal = 8.dp).padding(top = 12.dp)
            )

            Text(
                text = "ALC ${reviewShareCard.wine.alc}% . FROM ${reviewShareCard.wine.origin}",
                color = ProofTheme.color.white,
                style = ProofTheme.typography.buttonM,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ProofTheme.color.black)
            )
            Text(
                text = "${reviewShareCard.userReview.mood.uppercase(Locale.getDefault())} ${reviewShareCard.userReview.weather.uppercase(Locale.getDefault())}, ${reviewShareCard.userReview.time.uppercase(Locale.getDefault())}" +
                    "\n${reviewShareCard.userReview.isHeavy.uppercase(Locale.getDefault())} | ${reviewShareCard.userReview.isBitter.uppercase(Locale.getDefault())} | ${reviewShareCard.userReview.isBurning.uppercase(Locale.getDefault())} | ${reviewShareCard.userReview.isStrong.uppercase(Locale.getDefault())}",
                color = ProofTheme.color.white,
                style = ProofTheme.typography.bodyS,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1C1C26)
@Composable
fun PreviewReviewCard() {
    ProofTheme {
        ShareReviewCard(
            reviewShareCard = ReviewShareCard(),
            modifier = Modifier
                .width(312.dp)
                .height(472.dp).clip(RoundedCornerShape(12.dp))
        )
    }
}
