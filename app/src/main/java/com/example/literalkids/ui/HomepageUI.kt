package com.example.literalkids.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.literalkids.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

@Composable
fun HomepageUI() {
    val primaryTextColor = Color(0xFF0A5470)
    val gradientColors = Brush.horizontalGradient(listOf(Color(0xFF5AD8FF), Color(0xFFDE99FF)))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF5AD8FF),
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.robot_icon),
                        contentDescription = "Robot Icon",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            "Halo, Levi!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            "Siap mengeksplor cerita hari ini?",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .width(330.dp)
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cari cerita yang kamu mau...", color = Color.Gray)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Slider Section
        HeroSliderSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(brush = gradientColors)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Progres Bacamu Saat Ini", fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.White)
                    ) {
                        val progress = 0.68f

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(progress)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    Brush.horizontalGradient(
                                        colorStops = arrayOf(
                                            0.0f to Color(0xFFDE99FF), // 100%
                                            0.8f to Color(0xFF855C99)  // 80%
                                        )
                                    )
                                )
                        )

                    Text(
                            text = "68%",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text("Yuk baca lebih banyak cerita untuk capai target bulananmu!", fontSize = 12.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Image(
                    painter = painterResource(id = R.drawable.robot_megaphone),
                    contentDescription = "Robot Progress",
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Papan Peringkat
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Papan Peringkat",
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Next Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
        }
        Text("Yuk, Asah Terus Minat Bacamu Dan Jadi Yang Terbaik", color = Color(0xFFB0B0B0), fontSize = 12.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(brush = gradientColors)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Levi Annora", fontWeight = FontWeight.Bold, color = Color.White)
                    Text("@leviannora", fontSize = 12.sp, color = Color.White)
                }
                Image(painter = painterResource(id = R.drawable.ic_crown_white), contentDescription = "Crown", modifier = Modifier.padding(horizontal = 4.dp).size(20.dp))
                Text("Level 7", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lanjutkan Cerita
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Yuk Lanjutkan Ceritamu!",
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Next Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            item {
                StoryCard(R.drawable.kancil_buaya, "Si Kancil dan Buaya", 0.8f)
            }
            item {
                StoryCard(R.drawable.buaya_kerbau, "Buaya dan Kerbau", 0.6f)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rekomendasi
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Rekomendasi Untuk Yang Suka Si Kancil",
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Next Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            item { StoryThumb(R.drawable.kancil_ketimun, "Komedi", "Kancil Mencuri Ketimun", "4,2 Ribu Dibaca") }
            item { StoryThumb(R.drawable.danau_toba, "Legenda", "Asal Usul Danau Toba", "2,1 Ribu Dibaca") }
            item { StoryThumb(R.drawable.timun_mas, "Mitos", "Timun Mas dan Buto Ijo", "1,8 Ribu Dibaca") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Paling Populer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Paling Populer",
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Next Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            item { StoryThumb(R.drawable.lutung, "Fantasi", "Petualangan Lutung", "6,8 Ribu Dibaca") }
            item { StoryThumb(R.drawable.pipit, "Fabel", "Si Burung Pipit Penolong", "9,2 Ribu Dibaca") }
            item { StoryThumb(R.drawable.putri_bambu, "Legenda", "Putri Bambu Gunung Arjuna", "8,9 Ribu Dibaca") }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun StoryCard(imageRes: Int, title: String, progress: Float) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .border(1.dp, Color(0xFFD5D5D5), shape = RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp), // no padding kiri
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

        Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A5470),
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFE7E7E7)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progress)
                            .clip(RoundedCornerShape(50))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF5AD8FF), Color(0xFF368299))
                                )
                            )
                    )

                    Text(
                        text = "${(progress * 100).toInt()}%",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .align(Alignment.CenterStart)
                    )
                }
            }
        }
    }
}

@Composable
fun StoryThumb(
    imageRes: Int,
    category: String,
    title: String,
    views: String
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        border = BorderStroke(1.dp, Color(0xFFD5D5D5)) // ✅ border abu
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(
                    text = category,
                    fontSize = 10.sp,
                    color = Color(0xFF808080)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A5470)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = views,
                    fontSize = 10.sp,
                    color = Color(0xFF5AD8FF)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeroSliderSection() {
    val images = listOf(
        R.drawable.banner_kancil,
        R.drawable.banner_istana,
        R.drawable.banner_pohon
    )
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(20.dp))
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Slider Image",
                modifier = Modifier.fillMaxSize()
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            activeColor = Color(0xFF5AD8FF),
            inactiveColor = Color(0xFFDFF5FF)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomepageUIPreview() {
    HomepageUI()
}