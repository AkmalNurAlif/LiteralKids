package com.example.literalkids.ui

import android.util.Log
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen

@Composable
fun CurvedHeader() {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp + statusBarHeight)
            .padding(top = statusBarHeight)
    ) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, height * 0.85f)
            cubicTo(
                width * 0.20f, height * 0.10f,
                width * 0.75f, height * 0.04f,
                width, height * 0.3f
            )
            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(path = path, color = Color(0xFF64D2FF))
    }
}

@Composable
fun LoginUI(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .zIndex(0f) // base layer
    ) {
        CurvedHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .zIndex(1f), // content layer
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = "Profile Placeholder"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Log In", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Lupa password?", color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    Log.d("NAV_CHECK", "Login ditekan")
                    navController.navigate(Screen.OnBoarding1.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64D2FF))
            ) {
                Text("Login", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(1f)) { Divider(color = Color.LightGray) }
                Text(" atau ", fontSize = 12.sp, color = Color.Gray)
                Box(modifier = Modifier.weight(1f)) { Divider(color = Color.LightGray) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = { /* Login dengan Google */ },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 150.dp, height = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Google")
                }

                OutlinedButton(
                    onClick = { /* Login dengan Facebook */ },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 150.dp, height = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_logo),
                        contentDescription = "Facebook Logo",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Facebook")
                }
            }

            Spacer(modifier = Modifier.height(100.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Belum punya akun?", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Daftar",
                    color = Color(0xFF64D2FF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Register.route)
                    }
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginUI() {
    // Gunakan dummy NavController untuk preview
    LoginUI(navController = rememberNavController())
}