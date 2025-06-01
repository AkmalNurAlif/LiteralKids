package com.example.literalkids.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.literalkids.R

class ArticleDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticleDetailScreen()
        }
    }
}

@Composable
fun ArticleDetailScreen() {
    // Membungkus seluruh konten dengan scrollable modifier
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)  // Menghapus padding di sekitar seluruh konten
            .verticalScroll(rememberScrollState()) // Menambahkan scroll
    ) {
        // Header Artikel
        ArticleHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Artikel Content
        ArticleContent()

        Spacer(modifier = Modifier.height(24.dp))

        // Komentar Section
        CommentSection()
    }
}

@Composable
fun ArticleHeader() {
    // Header Gradien tanpa padding kiri dan kanan
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF56CCF2), Color(0xFFB36FF1))
                )
            )
            .padding(vertical = 24.dp),  // Padding vertikal tetap ada
        contentAlignment = Alignment.Center
    ) {
        Text("Bagaimana Membiasakan Anak Membaca Setiap Hari?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun ArticleContent() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)  // Menghapus padding kiri dan kanan untuk seluruh konten
    ) {
        // Gambar artikel
        Image(
            painter = painterResource(id = R.drawable.anak),  // Ganti dengan id gambar artikel sesuai
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth() // Mengisi seluruh lebar
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Teks kategori artikel
        Text(
            text = "Kebiasaan",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A4A5A)
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Teks judul artikel
        Text(
            text = "Bagaimana Membiasakan Anak Membaca Setiap Hari?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A4A5A)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Artikel body
        Text(
            text = "Membiasakan anak membaca setiap hari memang bukan hal yang instan. Perlu pendekatan yang menyenangkan agar kegiatan membaca terasa seperti waktu bermain, bukan kewajiban. Salah satu caranya adalah dengan memilih buku yang sesuai minat dan usia anak. Cerita bergambar dengan tokoh lucu atau dongeng penuh imajinasi bisa menarik perhatian anak dan membuatnya antusias membaca halaman demi halaman.\n\n" +
                    "Orang tua juga bisa menjadikan membaca sebagai rutinitas harian, seperti sebelum tidur atau setelah makan siang. Bacalah bersama dan beri ruang bagi anak untuk ikut bercerita ulang. Aktivitas ini tidak hanya membangun kebiasaan membaca, tetapi juga mempererat hubungan emosional antara anak dan orang tua. Ingat, kunci utamanya adalah konsistensi dan suasana yang menyenangkan!",
            fontSize = 16.sp,
            color = Color(0xFF1A4A5A),
            lineHeight = 24.sp
        )
    }
}

@Composable
fun CommentSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Judul bagian komentar
        Text(
            text = "Komentar",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A4A5A),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Komentar 1
        CommentItem(
            name = "Rina Sumala",
            time = "3 Hari Yang Lalu",
            comment = "Wah, bener banget! aku mulai rutin bacain buku buat anakku sebelum tidur, sekarang dia malah yang minta dibacain terus setiap malam. ❤ buku cerita bergambar memang jadi favoritnya!",
            likes = 45,
            dislikes = 2
        )

        // Komentar 2
        CommentItem(
            name = "Rina Sumala",
            time = "3 Hari Yang Lalu",
            comment = "Sama, bu! anak saya juga paling semangat kalau tokohnya lucu dan bisa ikut suara-suara hewan. jadi bonding banget ya waktu bacain bareng!",
            likes = 14,
            dislikes = 0
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Comment input field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Tulis komentar...") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF76D0FF),
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}

@Composable
fun CommentItem(name: String, time: String, comment: String, likes: Int, dislikes: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Nama dan waktu komentar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A4A5A)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = time,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        // Teks komentar
        Text(
            text = comment,
            fontSize = 16.sp,
            color = Color(0xFF1A4A5A),
            lineHeight = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Like & Dislike Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "$likes 👍",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "$dislikes 👎",
                fontSize = 14.sp,
                color = Color.Gray
            )
            TextButton(onClick = { /* Handle reply action */ }) {
                Text(
                    text = "Balas",
                    fontSize = 14.sp,
                    color = Color(0xFF76D0FF)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleDetailScreen() {
    ArticleDetailScreen()
}
