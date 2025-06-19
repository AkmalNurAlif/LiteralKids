package com.example.literalkids.ui.bacaBuku

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen
import com.example.literalkids.viewmodel.BacaCeritaViewModel
import java.util.Locale

@Composable
fun BacaCeritaScreenUI(
    navController: NavController,
    context: Context = LocalContext.current,
    viewModel: BacaCeritaViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentCerita = uiState.currentCerita

    // Inisialisasi TTS di UI karena butuh Context
    val tts = remember {
        TextToSpeech(context) {}.apply {
            setLanguage(Locale("id", "ID"))
        }
    }

    // Sinkronkan TTS dengan ViewModel
    LaunchedEffect(uiState.isSpeaking, currentCerita) {
        if (uiState.isSpeaking) {
            tts.speak(currentCerita.text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            tts.stop()
        }
    }

    // Dispose TTS saat Composable dihancurkan
    DisposableEffect(Unit) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background gambar
        Image(
            painter = painterResource(id = currentCerita.backgroundRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Ruang untuk page indicator
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF7DE2FC), Color(0xFFB9B6FF))
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.tombol_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.navigate(Screen.HomeBacaCerita.route) }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Kancil Mencuri Ketimun",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Banner Judul dan Tombol
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f)
                ) {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = size.width
                        val height = size.height
                        val cutSize = 20f

                        val path = Path().apply {
                            moveTo(0f, 0f)
                            lineTo(width - cutSize, 0f)
                            lineTo(width, height / 2)
                            lineTo(width - cutSize, height)
                            lineTo(0f, height)
                            close()
                        }

                        drawPath(
                            path = path,
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF5AD8FF), Color(0xFFDE99FF))
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = currentCerita.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    onClick = { /* TODO: Bookmark */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark),
                        contentDescription = "Bookmark",
                        tint = Color(0xFF00C2FF),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    onClick = { viewModel.toggleSpeaking() },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (uiState.isSpeaking) R.drawable.on_audio else R.drawable.mute_audio
                        ),
                        contentDescription = "TTS",
                        tint = Color(0xFF00C2FF),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(200.dp))

            // Isi Cerita
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentCerita.text,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Page indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            PageIndicatorBaca(
                currentPage = uiState.currentPage,
                onPageChange = { viewModel.setPage(it) },
                pageCount = uiState.ceritaPages.size
            )
        }

        // Tombol "Selesai" di halaman terakhir
        if (uiState.currentPage == uiState.ceritaPages.lastIndex) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { viewModel.showFinishDialog() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7DE2FC)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Selesai", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Dialog "Yeay Cerita Selesai!"
        if (uiState.showFinishDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.hideFinishDialog() },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.hideFinishDialog()
                            navController.navigate(Screen.Quiz.route)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7DE2FC)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_quiz),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Lanjut Kuis", color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                },
                title = {
                    Text(
                        "Yeay Cerita Selesai!",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00475D),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                text = {
                    Text(
                        "Sekarang, Yuk Uji Seberapa Hebat Pemahaman Ceritamu Lewat Kuis!",
                        color = Color(0xFF00475D),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.robot_kuis),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                },
                containerColor = Color.White,
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}