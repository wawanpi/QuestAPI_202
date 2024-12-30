package com.example.pertemuan12.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.ui.costumwidget.CostumeTopAppBar
import com.example.pertemuan12.ui.viewmodel.DetailUiState
import com.example.pertemuan12.ui.viewmodel.DetailViewModel
import com.example.pertemuan12.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMhsScreen(
    nim: String,                     // NIM mahasiswa sebagai parameter
    navigateBack: () -> Unit,        // Fungsi navigasi balik
    onUpdateClick: (String) -> Unit, // Fungsi untuk navigasi ke update screen
    modifier: Modifier = Modifier,   // Modifier default
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val detailUiState = viewModel.detailUiState.collectAsState().value

    // Trigger pertama kali saat layar ini dimuat
    LaunchedEffect(nim) {
        viewModel.getDetailMahasiswa(nim)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Detail Mahasiswa",
                canNavigateBack = true,
                navigateUp = navigateBack // Tombol navigasi balik
            )
        }
    ) { innerPadding ->
        // Isi layar berdasarkan state (loading, success, error)
        Box(modifier = Modifier.padding(innerPadding)) {
            when (detailUiState) {
                is DetailUiState.Loading -> OnLoadingDetail(modifier = Modifier.fillMaxSize()) // Kalau loading
                is DetailUiState.Success -> DetailView(
                    mahasiswa = detailUiState.mahasiswa, // Kalau data sukses dimuat
                    onUpdateClick = onUpdateClick,
                    modifier = Modifier.fillMaxSize()
                )
                is DetailUiState.Error -> OnError(retryAction = { viewModel.getDetailMahasiswa(nim) }) // Kalau ada error
            }
        }
    }
}

@Composable
fun DetailView(
    mahasiswa: Mahasiswa,             // Data mahasiswa yang ditampilkan
    onUpdateClick: (String) -> Unit,  // Action buat update data
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Kartu detail mahasiswa
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Teks detail data mahasiswa
                Text(text = "Nama: ${mahasiswa.nama}", style = MaterialTheme.typography.titleLarge)
                Text(text = "NIM: ${mahasiswa.nim}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Kelas: ${mahasiswa.kelas}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Angkatan: ${mahasiswa.angkatan}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Alamat: ${mahasiswa.alamat}", style = MaterialTheme.typography.titleMedium)
            }
        }

        // Tombol FloatingActionButton buat update
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { onUpdateClick(mahasiswa.nim) }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Update Data")
            }
        }
    }
}

// UI saat data loading
@Composable
fun OnLoadingDetail(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}

// UI saat ada error
@Composable
fun OnError(retryAction: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "An error occurred. Please try again.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = retryAction) {
                Text(text = "Retry")
            }
        }
    }
}
