package com.mashup.zuzu.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
                    text = stringResource(R.string.weather_rain),
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
                    text = stringResource(R.string.weather_snow),
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
                    text = stringResource(R.string.weather_sunny),
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
                    text = stringResource(R.string.weather_cloudy),
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
                    text = stringResource(R.string.date_dinner),
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
                    text = stringResource(R.string.date_afternoon),
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
                    text = stringResource(R.string.date_night),
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
                    text = stringResource(R.string.date_dawn),
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
                    text = stringResource(R.string.partner_solo),
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
                    text = stringResource(R.string.partner_friends),
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
                    text = stringResource(R.string.partner_couple),
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
                    text = stringResource(R.string.partner_party),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }
    }

}

@Composable
fun GroupSelectOption(
    navigateTastePage: (String) -> Unit
) {
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
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_friendly),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_serious),
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
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_romantic),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_crazy),
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
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_groomy),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_congratulations),
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
fun SoloSelectOption(
    navigateTastePage: (String) -> Unit
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
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_happy),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_serious),
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
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_groomy),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_crazy),
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
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_taste),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = {
                    navigateTastePage("123")
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
                    text = stringResource(R.string.mood_self_cong),
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }

        }
    }
}

@Composable
fun TasteSelectOption() {
    Column() {
        val radioTitles = listOf(
            Pair("가벼워요", "무거워요"),
            Pair("달아요", "써요"),
            Pair("은은한 술맛", "찐한 술맛"),
            Pair("부드러운 목넘김", "화끈거리는 목넘김")
        )

        radioTitles.forEach { title ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 34.dp, end = 34.dp, bottom = 16.dp)
            ) {
                Text(
                    text = title.first,
                    color = ProofTheme.color.white
                )

                Text(
                    text = title.second,
                    color = ProofTheme.color.white
                )
            }

            var state by remember {
                mutableStateOf("")
            }

            val radioButtons = listOf(
                Pair("1", 34),
                Pair("2", 28),
                Pair("3", 24),
                Pair("4", 24),
                Pair("5", 28),
                Pair("6", 34)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 34.dp)
            ) {
                radioButtons.forEach { radioButton ->
                    val item = radioButton.first
                    val radius = radioButton.second

                    IconToggleButton(checked = state == item, onCheckedChange = { state = item }) {
                        Icon(
                            painter = painterResource(
                                if (state == item) {
                                    R.drawable.ic_taste_toggle_on
                                } else {
                                    R.drawable.ic_taste_toggle_off
                                }
                            ), contentDescription = "", modifier = Modifier
                                .width(radius.dp)
                                .height(radius.dp),
                            tint = Color.Unspecified
                        )
                    }

                }
            }

        }

    }
}

@Composable
fun SummarySelectOption() {

}