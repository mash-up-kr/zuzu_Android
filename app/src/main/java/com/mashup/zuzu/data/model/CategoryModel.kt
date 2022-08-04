package com.mashup.zuzu.data.model

/**
 * @Created by 김현국 2022/07/20
 */

data class Category(
    val imageUrl: String,
    val title: String
)

val categoryList = listOf(
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "전체"),
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "맥주"),
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "소주"),
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "위스키"),
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "와인"),
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "칵테일"),
    Category(imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8d9a19a-fbe3-46d7-a932-85757534d51b/Image.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T073845Z&X-Amz-Expires=86400&X-Amz-Signature=dd1273f0dc845a32f824eaa00113ceaeabf0f5bf05cba6c8f95b0d24034fe088&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Image.png%22&x-id=GetObject", title = "전통주")
)
