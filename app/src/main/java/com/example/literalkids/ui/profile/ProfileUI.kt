package com.example.literalkids.ui.profile

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.literalkids.R
import com.example.literalkids.navigation.Screen
import com.example.literalkids.ui.navbar.BottomNavigation
import com.example.literalkids.viewmodel.ProfileViewModel
import com.example.literalkids.viewmodel.SubscriptionViewModel
import com.example.literalkids.viewmodel.ViewModelFactory

@Composable
fun ProfileUI(
    navController: NavController,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val profileViewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as android.app.Application)
    )
    val subscriptionViewModel: SubscriptionViewModel = viewModel()
    val profileUiState by profileViewModel.uiState.collectAsState()
    val subscriptionUiState by subscriptionViewModel.uiState.collectAsState()

    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("Indonesia") }
    var showLanguageDialog by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = if (isDarkMode) {
            darkColorScheme(
                primary = Color(0xFF5DCCF8),
                background = Color(0xFF121212),
                surface = Color(0xFF1E1E1E),
                onPrimary = Color.White,
                onBackground = Color.White,
                onSurface = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF5DCCF8),
                background = Color.White,
                surface = Color.White,
                onPrimary = Color.White,
                onBackground = Color.Black,
                onSurface = Color.Black
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
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
                    name = profileUiState.childData.fullName,
                    username = "@" + profileUiState.childData.username,
                    avatarUrl = profileUiState.childData.avatarUrl,
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
                    name = profileUiState.parentData.fullName,
                    username = "@" + profileUiState.parentData.username,
                    avatarUrl = profileUiState.parentData.avatarUrl,
                    onEditClick = { navController.navigate(Screen.ParentProfile.route) }
                )

                // Network Status Section
                if (profileUiState.apiError != null && !profileUiState.isApiLoading) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFFFFEBEE),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "Error, aplikasi tidak tersambung dengan wifi",
                                color = Color(0xFFD32F2F),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(
                                onClick = { profileViewModel.refreshProfile() }
                            ) {
                                Text("Coba Lagi", color = Color(0xFF5DCCF8))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val activePlan = subscriptionUiState.activePlan
                if (activePlan != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF7BDDFB), Color(0xFFD7A5FF))
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { navController.navigate(Screen.Subscription.route) }
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.sc_robot),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Yay kamu sedang berlangganan!", color = Color.White, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(activePlan.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Periode Berlangganan:", color = Color.White, fontSize = 10.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(activePlan.subscriptionPeriod ?: "Tidak tersedia", color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF7BDDFB), Color(0xFFD7A5FF))
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { navController.navigate(Screen.Subscription.route) }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Belum ada langganan aktif. Mulai berlangganan sekarang!",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
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
                onClick = { showUnavailablePageToast(context) },
            )

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
                onClick = { /* Logika logout di sini */ }
            )

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

        if (showLanguageDialog) {
            LanguageSelectionDialog(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = { selectedLanguage = it },
                onDismiss = { showLanguageDialog = false }
            )
        }
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
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
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
                                selectedColor = Color(0xFF5DCCF8),
                                unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = language,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
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
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface
    )
}