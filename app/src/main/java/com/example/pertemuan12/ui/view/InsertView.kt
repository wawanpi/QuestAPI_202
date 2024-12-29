package com.example.pertemuan12.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.ui.costumwidget.CostumeTopAppBar
import com.example.pertemuan12.ui.navigation.DestinasiNavigasi
import com.example.pertemuan12.ui.viewmodel.InsertUiEvent
import com.example.pertemuan12.ui.viewmodel.InsertUiState
import com.example.pertemuan12.ui.viewmodel.InsertViewModel
import com.example.pertemuan12.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

// Navigasi tujuan buat layar ini
object DestinasiEntry : DestinasiNavigasi {
    override val route = "item_entry" // Rute navigasi ke layar ini
    override val titleRes = "Entry Mhs" // Judul di TopAppBar
}

// Komponen utama layar Entry Mahasiswa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryMhsScreen(
    navigateBack: () -> Unit, // Fungsi navigasi balik
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory) // ViewModel terintegrasi
) {
    val coroutineScope = rememberCoroutineScope() // Buat ngelola coroutine
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() // Scroll top bar efek smooth
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), // Scroll support buat UI
        topBar = { // AppBar custom
            CostumeTopAppBar(
                title = DestinasiEntry.titleRes, // Judul dari object DestinasiEntry
                canNavigateBack = true, // Tampilkan tombol back
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack // Aksi saat tombol back di klik
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState, // Data dari ViewModel
            onSiswaValueChange = viewModel::updateInsertMhsState, // Update state sesuai input
            onSaveClick = { // Simpan data dan navigasi balik
                coroutineScope.launch {
                    viewModel.insertMhs() // Call fungsi save data di ViewModel
                    navigateBack() // Balik ke layar sebelumnya
                }
            },
            modifier = Modifier
                .padding(innerPadding) // Biar nggak ketabrak AppBar
                .verticalScroll(rememberScrollState()) // Support scroll buat form panjang
                .fillMaxWidth()
        )
    }
}

// EntryBody = Layout form + tombol Simpan
@Composable
fun EntryBody(
    insertUiState: InsertUiState, // Data state dari ViewModel
    onSiswaValueChange: (InsertUiEvent) -> Unit, // Fungsi buat update state
    onSaveClick: () -> Unit, // Aksi saat tombol Simpan diklik
    modifier: Modifier = Modifier // Styling tambahan opsional
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp), // Jarak antar elemen
        modifier = modifier.padding(12.dp) // Padding biar rapi
    ) {
        FormInput( // Bagian form inputnya
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button( // Tombol Simpan
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small, // Style tombol
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan") // Teks tombol
        }
    }
}

// Bagian form input data mahasiswa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent, // Data input user
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {}, // Fungsi buat update input
    enabled: Boolean = true // Apakah input bisa diedit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar input field
    ) {
        // Input Nama
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange = { onValueChange(insertUiEvent.copy(nama = it)) },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input NIM
        OutlinedTextField(
            value = insertUiEvent.nim,
            onValueChange = { onValueChange(insertUiEvent.copy(nim = it)) },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input Jenis Kelamin
        OutlinedTextField(
            value = insertUiEvent.jenisKelamin,
            onValueChange = { onValueChange(insertUiEvent.copy(jenisKelamin = it)) },
            label = { Text("Jenis Kelamin") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input Alamat
        OutlinedTextField(
            value = insertUiEvent.alamat,
            onValueChange = { onValueChange(insertUiEvent.copy(alamat = it)) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input Kelas
        OutlinedTextField(
            value = insertUiEvent.kelas,
            onValueChange = { onValueChange(insertUiEvent.copy(kelas = it)) },
            label = { Text("Kelas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input Angkatan
        OutlinedTextField(
            value = insertUiEvent.angkatan,
            onValueChange = { onValueChange(insertUiEvent.copy(angkatan = it)) },
            label = { Text("Angkatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Reminder buat isi semua data
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp) // Biar rapi
            )
        }

        // Divider buat pemisah
        Divider(
            thickness = 8.dp, // Tebal garis
            modifier = Modifier.padding(12.dp) // Jarak biar gak mepet
        )
    }
}
