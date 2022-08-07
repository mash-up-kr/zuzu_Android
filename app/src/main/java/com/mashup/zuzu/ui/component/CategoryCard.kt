package com.mashup.zuzu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.categoryList
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/01
 */

@Composable
fun CategoryCard(
    modifier: Modifier,
    category: Category,
    onCategoryClick: (Category) -> Unit,
    childModifier: Modifier?
) {
    if (childModifier != null) {
        Column {
            Box(
                modifier = modifier.clip(CircleShape)
            ) {
                AsyncImage(
                    modifier = childModifier.fillMaxSize().align(Alignment.Center),
                    model = category.imageUrl,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier.width(52.dp).height(21.dp).fillMaxWidth().then(childModifier)
            )
        }
    } else {
        Column(
            modifier = Modifier.clickable { onCategoryClick(category) }
        ) {
            Box(
                modifier = modifier
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize().align(Alignment.Center),
                    model = category.imageUrl,
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 5.dp),
                text = category.title,
                color = ProofTheme.color.white
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryCard() {
    ProofTheme() {
        CategoryCard(
            modifier = Modifier.width(52.dp).height(52.dp)
                .background(color = ProofTheme.color.gray100, shape = RoundedCornerShape(8.dp)),
            category = categoryList[0],
            {},
            childModifier = Modifier.placeholder(
                visible = true,
                color = ProofTheme.color.gray600,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ProofTheme.color.gray500
                )
            )
        )
    }
}
