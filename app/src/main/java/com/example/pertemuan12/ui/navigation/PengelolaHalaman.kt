package com.example.pertemuan12.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pertemuan12.ui.view.DestinasiEntry
import com.example.pertemuan12.ui.view.DestinasiHome
import com.example.pertemuan12.ui.view.EntryMhsScreen
import com.example.pertemuan12.ui.view.HomeScreen

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route, // Titik masuk aplikasi
        modifier = Modifier
    ) {
        // Destinasi Home
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = {
                    // Aksi tambahan saat detail di-klik (bisa ditambahkan di sini)
                }
            )
        }

        // Destinasi Entry (form mahasiswa baru)
        composable(DestinasiEntry.route) {
            EntryMhsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true // Menghapus stack lama (HomeScreen)
                        }
                    }
                }
            )
        }
    }
}

