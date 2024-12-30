package com.example.pertemuan12.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.ui.costumwidget.CostumeTopAppBar
import com.example.pertemuan12.ui.viewmodel.InsertUiEvent
import com.example.pertemuan12.ui.viewmodel.PenyediaViewModel
import com.example.pertemuan12.ui.viewmodel.UpdateUiState
import com.example.pertemuan12.ui.viewmodel.UpdateViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    nim: String, // Parameter buat ambil NIM (ID unik mahasiswa)
    navigateBack: () -> Unit, // Fungsi navigasi balik ke layar sebelumnya
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory) // ViewModel yang ngelola data & logic
) {
    val coroutineScope = rememberCoroutineScope() // CoroutineScope buat trigger background job

    // Jalankan efek waktu pertama kali masuk layar
    LaunchedEffect(nim) {
        viewModel.loadMahasiswaData(nim) // Panggil fungsi load data di ViewModel
    }

    // Scaffold untuk susunan UI
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Update Mahasiswa", // Judul di AppBar
                canNavigateBack = true, // Tombol Back aktif
                navigateUp = navigateBack // Fungsi back
            )
        }
    ) { innerPadding ->
        // Isi layar form update
        UpdateBody(
            updateUiState = viewModel.uiState, // Ambil UI state dari ViewModel
            onSiswaValueChange = viewModel::updateInsertMhsState, // Handle perubahan input
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateMhs() // Panggil ViewModel buat update data
                    navigateBack() // Balik ke layar sebelumnya
                }
            },
            modifier = Modifier
                .padding(innerPadding) // Atur padding biar gak tabrakan sama AppBar
                .verticalScroll(rememberScrollState()) // Bikin layout bisa discroll
                .fillMaxWidth() // Layout full width
        )
    }
}



@Composable
fun UpdateBody(
    updateUiState: UpdateUiState, // State untuk data yang ditampilkan
    onSiswaValueChange: (InsertUiEvent) -> Unit, // Callback pas user ngubah input
    onSaveClick: () -> Unit, // Callback pas user klik "Simpan"
    modifier: Modifier = Modifier
) {
    // Susunan form & tombol secara vertikal
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp), // Jarak antar elemen 18dp
        modifier = modifier.padding(12.dp) // Margin luar 12dp
    ) {
        // Input form data mahasiswa
        FormInput(
            insertUiEvent = updateUiState.insertUiEvent, // Data awal input dari state
            onValueChange = onSiswaValueChange, // Handle pas user ubah input
            modifier = Modifier.fillMaxWidth()
        )
        // Tombol "Simpan"
        Button(
            onClick = onSaveClick, // Trigger fungsi "Simpan"
            shape = MaterialTheme.shapes.small, // Bentuk tombol rounded kecil
            modifier = Modifier.fillMaxWidth() // Tombol lebar penuh
        ) {
            Text(text = "Simpan") // Tulisan di tombol
        }
    }
}