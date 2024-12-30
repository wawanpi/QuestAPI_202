package com.example.pertemuan12.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun DetailScreen(
    nim: String,
    navigateBack: () -> Unit,
    onUpdateClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val detailUiState = viewModel.detailUiState.collectAsState().value

    // Load data mahasiswa saat pertama kali layar ditampilkan
    LaunchedEffect(nim) {
        viewModel.getDetailMahasiswa(nim)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Detail Mahasiswa",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (detailUiState) {
                is DetailUiState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())
                is DetailUiState.Success -> DetailContent(
                    mahasiswa = detailUiState.mahasiswa,
                    onUpdateClick = onUpdateClick,
                    modifier = Modifier.fillMaxSize()
                )
                is DetailUiState.Error -> OnError(retryAction = { viewModel.getDetailMahasiswa(nim) })
            }
        }
    }
}

@Composable
fun DetailContent(
    mahasiswa: Mahasiswa,
    onUpdateClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Nama: ${mahasiswa.nama}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "NIM: ${mahasiswa.nim}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Kelas: ${mahasiswa.kelas}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Angkatan: ${mahasiswa.angkatan}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Alamat: ${mahasiswa.alamat}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Button(
            onClick = { onUpdateClick(mahasiswa.nim) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Data")
        }
    }
}


@Composable
fun OnLoadingDetail(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = "Loading...")
    }
}

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
