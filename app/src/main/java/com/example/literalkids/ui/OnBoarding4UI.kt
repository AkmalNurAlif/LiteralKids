package com.example.literalkids.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun OnBoarding4UI(navController: NavController) {
    var nama by remember { mutableStateOf("") }
    val isButtonEnabled = nama.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Gambar header dari drawable
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
                    .clickable { navController.navigate(Screen.OnBoarding3.route)
                    }
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

            // Dot indicator
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(6) { index ->
                    if (index != 0) Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = if (index == 3) {
                            Modifier
                                .size(20.dp, 6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFF64D2FF)) // Aktif
                        } else {
                            Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0F7FF)) // Nonaktif
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Nama Pengguna Anda",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0A5470)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Anda selalu dapat mengubahnya nanti.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFB0B0B0)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                placeholder = {
                    Text(
                        "Buat nama pengguna orang tua/pendamping",
                        color = Color(0xFFB0B0B0),
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color(0xFF64D2FF)
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    Log.d("NAV_CHECK", "Nama pengguna orang tua berhasil dimasukkan")
                    if (isButtonEnabled) {
                        navController.navigate(Screen.OnBoarding5.route) //ubah
                    }
                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color(0xFF64D2FF) else Color(0xFFD1D1D1),
                    contentColor = Color.White
                )
            ) {
                Text("Selanjutnya", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnBoarding4UI() {
    OnBoarding4UI(navController = rememberNavController())
}
