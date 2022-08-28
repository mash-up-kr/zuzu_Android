package com.mashup.zuzu.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.UserProfileImages

/**
 * @Created by 김현국 2022/07/18
 */

@Composable
fun ProfileImageItems(
    modifier: Modifier,
    profileImages: List<UserProfileImages>,
    onProfileImageClick: (Int) -> Unit,
    selectedProfileImage: UserProfileImages
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(profileImages) { index, profileImage ->
            if (selectedProfileImage.id == profileImage.id) {
                AsyncImage(
                    model = profileImage.image_url,
                    modifier = Modifier.width(48.dp).height(48.dp).border(1.dp, color = ProofTheme.color.primary200, shape = RoundedCornerShape(12.dp))
                        .background(color = ProofTheme.color.primary400, shape = RoundedCornerShape(12.dp))
                        .clickable {
                            onProfileImageClick(index)
                        },
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    model = profileImage.image_url,
                    modifier = Modifier.width(48.dp).height(48.dp)
                        .background(color = ProofTheme.color.gray400, shape = RoundedCornerShape(12.dp))
                        .clickable {
                            onProfileImageClick(index)
                        },
                    contentDescription = null
                )
            }
        }
    }
}
