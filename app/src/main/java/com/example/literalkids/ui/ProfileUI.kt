package com.example.literalkids.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen

data class UserData(
    val id: String = "",
    val fullName: String = "",
    val username: String = "",
    val level: Int = 1,
    val currentXp: Int = 0,
    val maxXp: Int = 100,
    val age: Int = 0,
    val gender: String? = null,
    val schoolLevel: String = "",
    val birthDate: String = "",
    val avatarUrl: Int = 0,
    val coins: Int = 0,
    val phoneNumber: String = "",
    val occupation: String = "",
    val relationship: String = "",
    val ownedAvatars: List<Int> = emptyList(),
    val type: String = "",
    val isLoading: Boolean = false
)

@Composable
fun ProfileUI(
    navController: NavController,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var isLoggedIn by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    val childState = UserData(
        id = "user11",
        fullName = "Levi Annora",
        username = "leviannora",
        level = 7,
        currentXp = 90,
        maxXp = 100,
        age = 5,
        gender = "perempuan",
        schoolLevel = "TK",
        birthDate = "7/4/2025",
        avatarUrl = R.drawable.default_avatar,
        coins = 450,
        type = "child",
        ownedAvatars = listOf(
            R.drawable.default_avatar
        )
    )

    val parentState = UserData(
        id = "user12",
        fullName = "Adinda Febyola",
        username = "febydinda",
        level = 38,
        birthDate = "1995-02-20",
        avatarUrl = R.drawable.parent_avatar,
        phoneNumber = "082198765432",
        occupation = "Ibu Rumah Tangga",
        relationship = "Ibu",
        type = "parent",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF5AD8FF),
                            Color(0xFFDE99FF)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Profil",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoggedIn) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Akun Anak",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D7193)
                )

                UserProfileCard(
                    name = childState.fullName,
                    username = "@" + childState.username,
                    avatarUrl = childState.avatarUrl,
                    onEditClick = { navController.navigate(Screen.ChildProfile.route) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Akun Orang Tua/Pendamping",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D7193)
                )

                UserProfileCard(
                    name = parentState.fullName,
                    username = "@" + parentState.username,
                    avatarUrl = parentState.avatarUrl,
                    onEditClick = { navController.navigate(Screen.ParentProfile.route) }
                )

                Spacer(modifier = Modifier.height(16.dp))

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
                            Text("Periode Berlangganan:" ,color = Color.White, fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("10/04/2025 - 10/07/2025", color = Color.White, fontSize = 10.sp)

                        }
                        Spacer(modifier = Modifier.weight(1f)) // mendorong ke kanan

                        Text(
                            text = "Perbarui Langganan",
                            fontSize = 8.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                     navController.navigate(Screen.Subscription.route)
                                }
                                .padding(start = 8.dp)
                        )
                    }
                }

            }
        } else {
            Button(
                onClick = {
                    isLoggedIn = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5DCCF8)
                )
            ) {
                Text(
                    text = "Daftar Akun",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            @Suppress("DEPRECATION")
            OutlinedButton(
                onClick = {
                    isLoggedIn = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(Color(0xFF5DCCF8))
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF5DCCF8)
                )
            ) {
                Text(
                    text = "Login Akun",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Tentang Akun",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Tentang Akun Saya",
            subtitle = "Informasi Dasar Seputar Akun Pengguna",
            endIcon = Icons.Default.ChevronRight,
//            onClick = { navController.navigate(Screen.AccountInfo.route) }
            onClick = { showUnavailablePageToast(context) },
        )

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifikasi",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Notifikasi",
            subtitle = "Pengaturan Notifikasi Aplikasi",
            endIcon = Icons.Default.ChevronRight,
//            onClick = { navController.navigate(Screen.Notifications.route) }
            onClick = { showUnavailablePageToast(context) },
        )

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = "Bahasa",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Bahasa",
            subtitle = "Sesuaikan Bahasa Dengan Preferensimu",
            endIcon = Icons.Default.ExpandMore,
            onClick = { showLanguageDialog = !showLanguageDialog }
        )

        ProfileMenuToggleItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.DarkMode,
                    contentDescription = "Tampilan",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Tampilan",
            subtitle = "Ubah Tampilan Menjadi Dark Mode",
            isChecked = isDarkMode,
            onCheckedChange = { isDarkMode = !isDarkMode }
        )

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Security,
                    contentDescription = "Kebijakan Privasi",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Kebijakan Privasi",
            subtitle = "Penjelasan Perlindungan Data Akun",
            endIcon = Icons.Default.ChevronRight,
//            onClick = { navController.navigate(Screen.PrivacyPolicy.route) }
            onClick = { showUnavailablePageToast(context) },
        )

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Description,
                    contentDescription = "Syarat Penggunaan",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Syarat Penggunaan",
            subtitle = "Ketentuan & Aturan Penggunaan Aplikasi",
            endIcon = Icons.Default.ChevronRight,
//            onClick = { navController.navigate(Screen.TermsOfService.route) }
            onClick = { showUnavailablePageToast(context) },
        )

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Keamanan",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Keamanan",
            subtitle = "Lakukan Autentikasi Akun Di Sini",
            endIcon = Icons.Default.ChevronRight,
//            onClick = { navController.navigate(Screen.Security.route) }
            onClick = { showUnavailablePageToast(context) },
        )

        ProfileMenuItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Update,
                    contentDescription = "Pembaruan Aplikasi",
                    tint = Color(0xFF5DCCF8),
                    modifier = Modifier.size(24.dp)
                )
            },
            title = "Pembaruan Aplikasi",
            subtitle = "Informasi Versi Dan Fitur Terbaru Aplikasi",
            endIcon = Icons.Default.ChevronRight,
//            onClick = { navController.navigate(Screen.AppUpdate.route) }
            onClick = { showUnavailablePageToast(context) },
        )

        if (isLoggedIn) {
            ProfileMenuItem(
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Keluar",
                        tint = Color(0xFF5DCCF8),
                        modifier = Modifier.size(24.dp)
                    )
                },
                title = "Keluar",
                subtitle = "Untuk Keluar Akun",
                endIcon = Icons.Default.ChevronRight,
                onClick = { isLoggedIn = false }
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation(
            currentRoute = Screen.Profile.route,
            onNavigate = { route ->
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }

    // Language Dialog
    if (showLanguageDialog) {
        LanguageSelectionDialog(
            selectedLanguage = "",
            onLanguageSelected = {  },
            onDismiss = { showLanguageDialog = !showLanguageDialog }
        )
    }
}

@Composable
fun UserProfileCard(
    name: String,
    username: String,
    avatarUrl: Int,
    onEditClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF5AD8FF),
            Color(0xFFDE99FF)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(gradient)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (avatarUrl != 0) {
                Image(
                    painter = painterResource(id = avatarUrl),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.default_avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = username,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }

            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }
        }
    }
}

fun showUnavailablePageToast(context: Context) {
    Toast.makeText(context, "Halaman belum tersedia", Toast.LENGTH_SHORT).show()
}

@Composable
fun ProfileMenuItem(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String,
    endIcon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFEBF9FF)),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1D7193)
            )

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Icon(
            imageVector = endIcon,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
fun ProfileMenuToggleItem(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFEBF9FF)),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1D7193)
            )

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF5DCCF8),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Composable
fun LanguageSelectionDialog(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val languages = listOf("Indonesia", "English", "Arabic", "Spanish", "French")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Pilih Bahasa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                languages.forEach { language ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLanguageSelected(language) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = language == selectedLanguage,
                            onClick = { onLanguageSelected(language) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF5DCCF8)
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = language,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Selesai",
                    color = Color(0xFF5DCCF8),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}