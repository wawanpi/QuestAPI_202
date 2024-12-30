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
import com.example.pertemuan12.ui.viewmodel.InsertUiEvent
import com.example.pertemuan12.ui.viewmodel.UpdateUiState




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