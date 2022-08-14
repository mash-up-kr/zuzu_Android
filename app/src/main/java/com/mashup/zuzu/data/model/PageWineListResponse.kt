package com.mashup.zuzu.data.model

/**
 * @Created by 김현국 2022/08/02
 */
data class PageWineListResponse(
    val total_page: Int,
    val wines: List<Wine>,
    val page: Int
)

val pageWine1 = PageWineListResponse(
    total_page = 3,
    wines = listOf(
        Wine(
            id = 1,
            name = "Page1GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", favorite = true,
            category = "와인"
        ),
        Wine(
            id = 2,
            name = "Page1Jack Daniels",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 3,
            name = "Page1참이슬",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 4,
            name = "Page1로즈와인",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 5,
            name = "Page1진로",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 6,
            name = "Page1Jim Beam",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 7,
            name = "Page1Red Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 8,
            name = "Page1Tintied Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        )
    ),
    page = 1
)

val pageWine2 = PageWineListResponse(
    total_page = 3,
    wines = listOf(
        Wine(
            id = 9,
            name = "Pgae2Blue Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 10,
            name = "Page2Black Beer",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "맥주"
        ),
        Wine(
            id = 11,
            name = "Page2White Beer",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "맥주"
        ),
        Wine(
            id = 12,
            name = "Page2Yellow Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 13,
            name = "Page2Green Beer",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "맥주"
        ),
        Wine(
            id = 14,
            name = "Page2Purple Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 15,
            name = "Page2Cyan Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 16,
            name = "Page2Orange Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        )
    ),
    page = 2
)

val pageWine3 = PageWineListResponse(
    total_page = 3,
    wines = listOf(
        Wine(
            id = 9,
            name = "Page3Blue Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 10,
            name = "Page3Black Beer",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "맥주"
        ),
        Wine(
            id = 11,
            name = "Page3White Beer",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "맥주"
        ),
        Wine(
            id = 12,
            name = "Page3Yellow Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 13,
            name = "Page3Green Beer",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "맥주"
        ),
        Wine(
            id = 14,
            name = "Page3Purple Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 15,
            name = "Page3Cyan Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        ),
        Wine(
            id = 16,
            name = "Page3Orange Wine",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            tags = listOf("뜨는 술", "맛있는 술"),
            description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            favorite = false,
            category = "와인"
        )
    ),
    page = 3
)

object PageWineRepo {
    fun getWineListWithPage(pageNumber: Int, category: String?): PageWineListResponse {
        return if (category == "전체") {
            when (pageNumber) {
                1 -> {
                    pageWine1
                }
                2 -> {
                    pageWine2
                }
                3 -> {
                    pageWine3
                }
                else -> {
                    pageWine1
                }
            }
        } else {
            when (pageNumber) {
                1 -> {
                    pageWine1.copy(
                        wines = pageWine1.wines.filter {
                            it.category == category
                        }
                    )
                }
                2 -> {
                    pageWine2.copy(
                        wines = pageWine2.wines.filter {
                            it.category == category
                        }
                    )
                }
                3 -> {
                    pageWine3.copy(
                        wines = pageWine1.wines.filter {
                            it.category == category
                        }
                    )
                }
                else -> {
                    pageWine1.copy(
                        wines = pageWine1.wines.filter {
                            it.category == category
                        }
                    )
                }
            }
        }
    }
}
