package com.example.literalkids.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen

@Composable
fun ParentActivityScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Header Gradien
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFF56CCF2), Color(0xFFB36FF1))
                    )
                )
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Orang Tua", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Detail Aktivitas",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A4A5A)
        )

        // Dropdown + BarChart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tampilkan Berdasarkan", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))
                var expanded by remember { mutableStateOf(false) }
                var selected by remember { mutableStateOf("Mingguan") }

                Box {
                    Button(
                        onClick = { expanded = true },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF76D0FF))
                    ) {
                        Text(selected, color = Color.White)
                    }

                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        listOf("Harian", "Mingguan", "Bulanan").forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    selected = it
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Hari ini, si kecil telah membaca selama",
                    color = Color(0xFF1A4A5A)
                )
                Text(
                    "1 jam 28 menit",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB36FF1)
                )

                Spacer(modifier = Modifier.height(16.dp))
                BarChart()
            }
        }

        // Donut Charts
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DonutChart(percent = 0.68f, centerText = "68%")
            DonutChart(percent = 0.85f, centerText = "17\nCerita")
        }

        // Artikel Section
        ArticleSection(navController)
    }
}

@Composable
fun DonutChart(percent: Float, centerText: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(140.dp) // Ukuran yang lebih besar untuk visibilitas yang lebih baik
    ) {
        Canvas(modifier = Modifier.size(120.dp)) {

            drawArc(
                color = Color(0xFFE0E0E0),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 24f, cap = StrokeCap.Round) // Lebar garis yang lebih besar untuk tampilan yang lebih rapi
            )

            drawArc(
                color = Color(0xFF76D0FF),
                startAngle = -90f,
                sweepAngle = percent * 360f,
                useCenter = false,
                style = Stroke(width = 24f, cap = StrokeCap.Round) // Lebar garis yang lebih besar untuk tampilan yang lebih rapi
            )
        }

        // Teks di tengah donut chart
        Text(
            centerText,
            fontSize = 18.sp, // Ukuran font yang lebih besar agar lebih terlihat jelas
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB36FF1), // Warna teks ungu agar sesuai dengan desain
            lineHeight = 22.sp, // Jarak antar baris teks agar lebih rapi
            textAlign = androidx.compose.ui.text.style.TextAlign.Center // Penjajaran teks di tengah
        )
    }
}

@Composable
fun BarChart() {
    val data = listOf(0.2f, 0.4f, 0.6f, 0.4f, 0.5f, 0.9f, 0.0f)
    val labels = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
    val barColor = Color(0xFF76D0FF)
    val highlightColor = Color(0xFFB36FF1)

    Column {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            data.forEachIndexed { index, value ->
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height((100 * value).dp)
                        .background(
                            if (index == 5) highlightColor else barColor,
                            RoundedCornerShape(4.dp)
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            labels.forEach { day ->
                Text(day, fontSize = 12.sp, color = Color(0xFF8A8A8A))
            }
        }
    }
}

@Composable
fun ArticleSection(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Artikel",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A2B50)
            ),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ArticleCard(
                title = "Bagaimana Membiasakan Anak Membaca Setiap Hari?",
                category = "Kebiasaan",
                description = "Membaca setiap hari bisa menjadi tantangan bagi anak...",
                navController = navController
            )
            ArticleCard(
                title = "Waktu Membaca Terfavorit Anak",
                category = "Tips Parenting",
                description = "Mengetahui waktu membaca terfavorit anak dapat membantu...",
                navController = navController
            )
        }
    }
}

@Composable
fun ArticleCard(title: String, category: String, description: String, navController: NavController) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(260.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anak),
                    contentDescription = "Ibu dan Anak",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = category, fontSize = 12.sp, color = Color.Gray)

                // Teks judul artikel menjadi clickable
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    modifier = Modifier
                        .clickable {

                            navController.navigate(Screen.ArticleDetail.route)
                        }
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewParentActivityScreen() {
    ParentActivityScreen(navController = rememberNavController())
}
