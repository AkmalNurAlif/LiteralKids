package com.example.literalkids.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.literalkids.R



@Composable
fun UIsubcription(navController: NavController) {
        Log.d("NAV_CHECK", "Masuk ke SubscriptionUI")
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF7BDDFB), Color(0xFFD7A5FF))
        
    )
    Log.d("NAV_CHECK", "Masuk ke Subscription screen")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header dengan tombol kembali dan judul
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBackground)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tombol_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Langganan",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            // Paket Aktif
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF7BDDFB), Color(0xFFD7A5FF))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.sc_robot),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Yay kamu sedang berlangganan!", color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Paket Hebat", color = Color.White, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Periode Berlangganan: 10/04/2025 - 10/07/2025", color = Color.White, fontSize = 10.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Kode Referral dengan pointer gambar dan center alignment
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF5AD8FF), Color(0xFFDE99FF))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(), // Memastikan Column mengisi seluruh lebar
                    horizontalAlignment = Alignment.CenterHorizontally // Menyelarakan semua elemen di tengah
                ) {
                    Text(
                        text = "Kode Referral",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(), // Memastikan teks ini memanfaatkan lebar penuh dan terletak di tengah
                        textAlign = TextAlign.Center // Menjaga teks tetap di tengah
                    )

                    Spacer(modifier = Modifier.height(8.dp)) // Memberikan jarak antara teks "Kode Referral" dan "VK2Z4A"

                    Row(
                        horizontalArrangement = Arrangement.Center, // Menyelaraskan elemen di dalam Row ke tengah
                        verticalAlignment = Alignment.CenterVertically, // Menyelaraskan elemen vertikal
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) // Memastikan Row memanfaatkan lebar penuh dan elemen di dalamnya terletak di tengah
                    ) {
                        Text(
                            text = "VK2Z4A",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterVertically), // Menjaga teks di tengah secara vertikal
                            textAlign = TextAlign.Center // Memastikan teks ini tetap di tengah secara horizontal
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Memberikan ruang antara teks dan gambar copy
                        Image(
                            painter = painterResource(id = R.drawable.copy), // Gambar pointer copy
                            contentDescription = "Copy",
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.CenterVertically) // Menjaga gambar tetap sejajar secara vertikal dengan teks
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp)) // Memberikan jarak antara Row dan teks deskripsi

                    Text(
                        text = "Gunakan kode referral di atas dan dapatkan berbagai hadiah menarik setiap temanmu bergabung dengan Literalkids!",
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth() // Memastikan teks ini memanfaatkan lebar penuh dan terletak di tengah
                    )
                }
            }





            Spacer(modifier = Modifier.height(24.dp))

            // Kartu Langganan
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    SubscriptionCard("Paket Ceria", "Rp25.000 Selama 1 Bulan", selected = true)
                }
                item {
                    SubscriptionCard("Paket Hebat", "Rp65.000 Selama 3 Bulan", selected = false, savings = "Hemat Hingga Rp10.000")
                }
                item {
                    SubscriptionCard("Paket Juara", "Rp180.000 Selama 12 Bulan", selected = false, savings = "Setara 4 Bulan Gratis")
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Tombol
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = false,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Sedang Berlangganan", color = Color.White)
            }
        }
    }
}

@Composable
fun SubscriptionCard(title: String, price: String, selected: Boolean, savings: String? = null) {
    val borderColor = if (selected) Color(0xFF00AEEF) else Color.LightGray
    val backgroundColor = Color.White
    val checkIcon = if (selected) R.drawable.check_blue else null
    val blue900 = Color(0xFF003049)

    Box(
        modifier = Modifier
            .width(180.dp)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Box {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Judul
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = blue900
                )

                // Harga + durasi
                Row {
                    Text(
                        text = price.substringBefore("Selama").trim(), // "Rp65.000"
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue900
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = price.substringAfter("Rp").substringAfter(" ").trim(), // "Selama 3 Bulan"
                        fontSize = 12.sp,
                        color = blue900,
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                }

                // Savings (jika ada) -> ditampilkan di bawah harga
                if (savings != null) {
                    Text(
                        text = savings,
                        fontSize = 10.sp,
                        color = Color(0xFF003049),
                        fontWeight = FontWeight.Bold
                    )
                }

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Fitur
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FeatureItem("Akses Semua Cerita")
                    FeatureItem("Kuis Interaktif")
                    FeatureItem("Statistik & Laporan Untuk Orang Tua")
                }
            }

            // Checklist biru di pojok kanan atas
            if (checkIcon != null) {
                Image(
                    painter = painterResource(id = checkIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(20.dp)
                )
            }
        }
    }
}




@Composable
fun FeatureItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.checklist), // Icon abu-abu
            contentDescription = null,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF003049)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSubscriptionScreen() {
//    SubscriptionUI()
//}

