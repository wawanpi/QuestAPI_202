package com.example.pertemuan12.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pertemuan12.model.Mahasiswa

@Composable
fun MhsCard(
    mahasiswa: Mahasiswa, // Objek mahasiswa (berisi data seperti nama, nim, dsb)
    modifier: Modifier = Modifier,
    onDeleteClick: (Mahasiswa) -> Unit = {} // Fungsi yang dipanggil ketika tombol delete di klik
) {
    // Elemen kartu (Card)
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium, // Bentuk kartu
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Ketinggian shadow kartu
    ) {
        // Layout vertikal dalam kartu
        Column(
            modifier = Modifier.padding(16.dp), // Isi kartu diberi padding 16 dp
            verticalArrangement = Arrangement.spacedBy(8.dp) // Jarak antar elemen
        ) {
            // Layout horizontal (baris pertama kartu)
            Row(
                modifier = Modifier.fillMaxWidth(), // Baris ini full-width
                verticalAlignment = Alignment.CenterVertically // Vertikal rata tengah
            ) {
                // Tampilkan nama mahasiswa
                Text(
                    text = mahasiswa.nama,
                    style = MaterialTheme.typography.titleLarge // Gaya teks besar
                )
                Spacer(Modifier.weight(1f)) // Spacer fleksibel buat memberi jarak

                // Tombol delete
                IconButton(onClick = { onDeleteClick(mahasiswa) }) {
                    Icon(
                        imageVector = Icons.Default.Delete, // Icon trash
                        contentDescription = null, // Tidak perlu deskripsi
                    )
                }
            }

            // Tampilkan NIM di row pertama
            Text(
                text = mahasiswa.nim,
                style = MaterialTheme.typography.titleMedium
            )

            // Data lain (kelas & alamat) di bawahnya
            Text(
                text = mahasiswa.kelas,
                style = MaterialTheme.typography.titleMedium // Gaya teks medium
            )
            Text(
                text = mahasiswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
