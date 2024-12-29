package com.example.pertemuan12.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pertemuan12.R
import com.example.pertemuan12.model.Mahasiswa

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    // Menampilkan gambar ikon loading (misalnya gambar koneksi error yang ada tulisan "loading")
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading), // Icon loading error
        contentDescription = stringResource(R.string.loading) // Deskripsi gambar untuk aksesibilitas
    )
}


@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    // Kolom sebagai layout utama
    Column(
        modifier = modifier, // Modifier untuk menyesuaikan style (opsional)
        verticalArrangement = Arrangement.Center, // Untuk menempatkan elemen secara vertikal di tengah
        horizontalAlignment = Alignment.CenterHorizontally // Untuk menempatkan elemen secara horizontal di tengah
    ) {
        // Menampilkan gambar error (misalnya icon koneksi gagal)
        Image(
            painter = painterResource(id = R.drawable.wifieror),
            contentDescription = "" // Menambahkannya untuk aksesibilitas
        )

        // Menampilkan pesan error ("Loading failed" atau lainnya)
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))

        // Tombol untuk mencoba ulang
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry)) // Menampilkan text tombol "Retry"
        }
    }
}


@Composable
fun MhsLayout(
    mahasiswa: List<Mahasiswa>, // List data mahasiswa
    modifier: Modifier = Modifier,
    onDetailClick: (Mahasiswa) -> Unit, // Fungsi untuk tampilkan detail mahasiswa
    onDeleteClick: (Mahasiswa) -> Unit = {} // Fungsi untuk hapus data mahasiswa
) {
    // LazyColumn untuk menampilkan list mahasiswa
    LazyColumn(
        modifier = modifier, // Modifier untuk styling (opsional)
        contentPadding = PaddingValues(16.dp), // Padding untuk seluruh list
        verticalArrangement = Arrangement.spacedBy(16.dp) // Jarak antar item dalam list
    ) {
        items(mahasiswa) { mahasiswa -> // Untuk setiap mahasiswa di dalam list
            MhsCard(
                mahasiswa = mahasiswa, // Kirim data mahasiswa ke MhsCard
                modifier = Modifier
                    .fillMaxWidth() // Membuat kartu memakan seluruh lebar layar
                    .clickable { onDetailClick(mahasiswa) }, // Fungsi detail ketika item diklik
                onDeleteClick = { // Fungsi delete ketika tombol delete di kartu diklik
                    onDeleteClick(mahasiswa)
                }
            )
        }
    }
}

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
