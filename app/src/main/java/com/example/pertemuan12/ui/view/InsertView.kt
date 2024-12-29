package com.example.pertemuan12.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField // Input field dengan border
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pertemuan12.ui.viewmodel.InsertUiEvent
import com.example.pertemuan12.ui.viewmodel.InsertUiState

// Entry form untuk input mahasiswa
@Composable
fun EntryBody(
    insertUiState: InsertUiState, // State dari view model
    onSiswaValueChange: (InsertUiEvent) -> Unit, // Fungsi buat update state waktu user input
    onSaveClick: () -> Unit, // Fungsi waktu tombol "Simpan" di-klik
    modifier: Modifier = Modifier // Styling tambahan opsional
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp), // Jarak antar elemen
        modifier = modifier.padding(12.dp) // Padding biar nggak terlalu mepet
    ) {
        FormInput( // Komponen form input
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button( // Tombol simpan data
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small, // Bentuk tombol
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan") // Label tombol
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Fitur eksperimental
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent, // Data input user
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {}, // Update input
    enabled: Boolean = true // Apakah inputnya aktif
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar input
    ) {
        // Input field buat nama
        OutlinedTextField(
            value = insertUiEvent.nama, // Nama sekarang
            onValueChange = { onValueChange(insertUiEvent.copy(nama = it)) }, // Update nama
            label = { Text("Nama") }, // Label
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true // Satu baris aja
        )

        // Input field buat NIM
        OutlinedTextField(
            value = insertUiEvent.nim,
            onValueChange = { onValueChange(insertUiEvent.copy(nim = it)) },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input field buat jenis kelamin
        OutlinedTextField(
            value = insertUiEvent.jenisKelamin,
            onValueChange = { onValueChange(insertUiEvent.copy(jenisKelamin = it)) },
            label = { Text("Jenis Kelamin") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input field buat alamat
        OutlinedTextField(
            value = insertUiEvent.alamat,
            onValueChange = { onValueChange(insertUiEvent.copy(alamat = it)) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input field buat kelas
        OutlinedTextField(
            value = insertUiEvent.kelas,
            onValueChange = { onValueChange(insertUiEvent.copy(kelas = it)) },
            label = { Text("Kelas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input field buat angkatan
        OutlinedTextField(
            value = insertUiEvent.angkatan,
            onValueChange = { onValueChange(insertUiEvent.copy(angkatan = it)) },
            label = { Text("Angkatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Pesan reminder buat isi data (opsional kalau enabled)
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp) // Biar pesannya gak nempel
            )
        }

        // Divider buat pemisah antar grup
        Divider(
            thickness = 8.dp, // Tebal divider
            modifier = Modifier.padding(12.dp) // Jarak dari elemen lain
        )
    }
}
