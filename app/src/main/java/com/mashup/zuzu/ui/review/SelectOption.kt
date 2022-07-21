package com.mashup.zuzu.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.theme.ProofTheme

@Composable
fun WeatherSelectOption(
    navigateDateSelectPage: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    navigateDateSelectPage("비 오는 날")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "비 오는 날",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateDateSelectPage("눈 오는 날")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "눈 오는 날",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    navigateDateSelectPage("맑은 날")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "맑은 날",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateDateSelectPage("흐린 날")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "흐린 날",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }
    }
}

@Composable
fun DateSelectOption(
    navigatePartnerPage: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    navigatePartnerPage("저녁에")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "저녁에",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigatePartnerPage("대낮에")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "대낮에",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    navigatePartnerPage("한밤중에")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "한밤중에",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigatePartnerPage("새벽에")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "새벽에",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }
    }

}

@Composable
fun PartnerSelectOption(
    navigateGroupPage: (String) -> Unit,
    navigateSoloPage: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    navigateSoloPage("혼자서")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "혼자서",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateGroupPage("친구/지인과")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "친구/지인과",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    navigateGroupPage("연인과")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "연인과",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateGroupPage("회식/단체 모임")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "회식/단체 모임",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }
    }

}

@Composable
fun GroupSelectOption() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "친근한/웃기는",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "엄격진지한",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "로맨틱한",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "광란의",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "우울한",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "축하하는",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }

        Text(
            text = "혹시 몇 차였어요?",
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "1차",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "2차",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "3차 이상",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }

}

@Composable
fun SoloSelectOption() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "즐거운",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "엄격진지한",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "우울한",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "광란의",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "음미하는",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_review_write_weather_rain),
                    contentDescription = ""
                )

                Text(
                    text = "셀프선물",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }
    }
}

@Composable
fun TasteSelectOption() {

}

@Composable
fun SummarySelectOption() {

}