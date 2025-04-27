package com.example.literalkids.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen

@Composable
fun RegisterUI(navController: NavController) {
    var email by remember { mutableStateOf("") }
    val isEmailValid = email.contains("@") && email.contains(".")
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CurvedHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
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

            Text("Daftar", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                trailingIcon = {
                    if (isEmailValid) {
                        Image(
                            painter = painterResource(id = R.drawable.tombol_check),
                            contentDescription = "Valid",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64D2FF))
            ) {
                Text("Buat Akun", color = Color.White, fontSize = 16.sp)
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
                Text("Sudah punya akun?", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Login",
                    color = Color(0xFF64D2FF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = 16.dp, y = 40.dp)
                .size(40.dp)
                .background(Color(0xFF64D2FF), shape = CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.tombol_back),
                contentDescription = "Back",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
