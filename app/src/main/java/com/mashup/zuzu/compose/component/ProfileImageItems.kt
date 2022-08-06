package com.mashup.zuzu.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/18
 */

data class ProfileImage(
    val id: Long,
    val image: Int
)

val profileImages = listOf(
    ProfileImage(
        id = 1L,
        image = R.drawable.img_wine_logo
    ),
    ProfileImage(
        id = 2L,
        image = R.drawable.img_wine_logo
    ),
    ProfileImage(
        id = 3L,
        image = R.drawable.img_wine_logo
    ),
    ProfileImage(
        id = 4L,
        image = R.drawable.img_wine_logo
    ),
    ProfileImage(
        id = 5L,
        image = R.drawable.img_wine_logo
    ),
    ProfileImage(
        id = 6L,
        image = R.drawable.img_wine_logo
    )
)
@Composable
fun ProfileImageItems(
    modifier: Modifier,
    profileImages: List<ProfileImage>,
    onProfileImageClick: (ProfileImage) -> Unit,
    selectedProfileImage: ProfileImage
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(profileImages) { profileImage ->
            if (selectedProfileImage.id == profileImage.id) {
                Box(
                    modifier = Modifier.width(48.dp).height(48.dp).border(3.dp, color = ProofTheme.color.primary200,shape= RoundedCornerShape(12.dp))
                        .background(color = ProofTheme.color.gray400, shape = RoundedCornerShape(12.dp))
                        .clickable {
                            onProfileImageClick(profileImage)
                        },
                )
            } else {
                Box(
                    modifier = Modifier.width(48.dp).height(48.dp)
                        .background(color = ProofTheme.color.gray400, shape = RoundedCornerShape(12.dp))
                        .clickable {
                            onProfileImageClick(profileImage)
                        },
                )
            }
        }
    }
}
