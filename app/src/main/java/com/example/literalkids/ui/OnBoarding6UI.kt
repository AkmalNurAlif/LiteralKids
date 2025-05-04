package com.example.literalkids.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.zIndex

@Composable
fun OnBoarding6UI(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_background),
            contentDescription = "Header Background",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.OnBoarding5.route) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tombol_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Kembali",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(120.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(6) { index ->
                    if (index != 0) Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = if (index == 5) {
                            Modifier
                                .size(20.dp, 6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFF64D2FF))
                        } else {
                            Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0F7FF))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Akses Cerita Lebih Banyak",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0A5470)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text(
                    text = "Yuk Gunakan Fitur Premium untuk",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFB0B0B0)
                )
                Text(
                    text = "Petualangan yang Lebih Menarik.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFB0B0B0)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            val packages = listOf(
                Triple("Paket Ceria", "Rp25.000", "Langganan Selama 1 Bulan"),
                Triple("Paket Hebat", "Rp65.000", "Langganan Selama 3 Bulan"),
                Triple("Paket Juara", "Rp120.000", "Langganan Selama 12 Bulan")
            )
            val originalPrices = listOf("", "Rp75.000", "Rp300.000")

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                packages.forEachIndexed { index, pkg ->
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        if (index == 1) {
                            Box(
                                modifier = Modifier
                                    .offset(y = (-8).dp)
                                    .zIndex(1f)
                                    .background(Color(0xFF5AD8FF), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Terlaris",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .height(180.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 1.dp,
                                    color = if (index == 1) Color(0xFF5AD8FF) else Color(0xFFD5D5D5),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(Color.White)
                                .padding(12.dp)
                                .padding(top = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = pkg.first,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF0A5470)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    if (originalPrices[index].isNotEmpty()) {
                                        Text(
                                            text = originalPrices[index],
                                            fontSize = 10.sp,
                                            color = Color(0xFFB0B0B0),
                                            style = LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough)
                                        )
                                    }

                                    Text(
                                        text = pkg.second,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF0A5470)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = pkg.third,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF0A5470),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                                Button(
                                    onClick = { Log.d("Paket", "Beli ${pkg.first}") },
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF64D2FF),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        text = "Beli",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lewati",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF5AD8FF),
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Homepage.route)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnBoarding6UI() {
    OnBoarding6UI(navController = rememberNavController())
}