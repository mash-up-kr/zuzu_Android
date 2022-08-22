package com.mashup.zuzu.data.model.dummy

import com.mashup.zuzu.data.model.ReviewShareCards
import com.mashup.zuzu.data.model.UserReview
import com.mashup.zuzu.data.model.wines

/**
 * @Created by 김현국 2022/08/20
 */

val dummyUserReview1 = listOf(
    UserReview(
        id = 1
    ),
    UserReview(
        id = 2
    ),
    UserReview(
        id = 3
    ),
    UserReview(
        id = 4
    )
)
val dummyUserReview2 = listOf(

    UserReview(
        id = 5
    ),
    UserReview(
        id = 6
    ),
    UserReview(
        id = 7
    ),
    UserReview(
        id = 8
    )
)
val dummyUserReview3 = listOf(
    UserReview(
        id = 9
    ),
    UserReview(
        id = 10
    ),
    UserReview(
        id = 11
    ),
    UserReview(
        id = 12
    )
)
val dummyUserReview4 = listOf(
    UserReview(
        id = 13
    ),
    UserReview(
        id = 14
    ),
    UserReview(
        id = 15
    ),
    UserReview(
        id = 16
    )
)

object DummyRepo {
    fun getReview(wineId: Long): ReviewShareCards {
        return ReviewShareCards(
            wine = wines[0],
            userReviews = dummyUserReview4
        )
    }
}
