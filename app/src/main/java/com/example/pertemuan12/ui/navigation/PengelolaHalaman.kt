package com.example.pertemuan12.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pertemuan12.ui.view.* // Import semua layar (screen) dan destinasi

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController() // Objek controller buat navigasi
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route, // Start screen (Home)
        modifier = Modifier
    ) {
        // **Destinasi Home**: layar utama aplikasi
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { // Fungsi buat navigasi ke Entry Screen
                    navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = { nim -> // Navigasi ke layar detail dengan parameter NIM
                    navController.navigate("detail/$nim")
                }
            )
        }

        // **Destinasi Entry**: form mahasiswa baru
        composable(DestinasiEntry.route) {
            EntryMhsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) { // Navigasi balik ke Home
                        popUpTo(DestinasiHome.route) {
                            inclusive = true // Menghapus stack untuk menghindari double back
                        }
                    }
                }
            )
        }

        // **Destinasi Detail**: detail mahasiswa (mengambil parameter NIM)
        composable("detail/{nim}") { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: "" // Ambil argumen 'nim' dari rute
            DetailMhsScreen(
                nim = nim,
                navigateBack = { navController.popBackStack() }, // Balik ke layar sebelumnya
                onUpdateClick = { nim ->
                    navController.navigate("update/$nim") // Navigasi ke layar update data
                }
            )
        }

        // **Destinasi Update**: edit data mahasiswa
        composable("update/{nim}") { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: "" // Ambil argumen 'nim'
            UpdateScreen(
                nim = nim,
                navigateBack = { navController.popBackStack() } // Balik ke layar sebelumnya
            )
        }
    }
}
