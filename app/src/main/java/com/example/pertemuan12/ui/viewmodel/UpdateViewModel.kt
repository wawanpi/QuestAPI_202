package com.example.pertemuan12.ui.viewmodel


import com.example.pertemuan12.model.Mahasiswa



// Data State untuk menyimpan state UI
data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent() // Default kosong
)

// Event user: input NIM, Nama, Alamat, dll. untuk update
data class UpdateUiEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

// Konversi dari UpdateUiEvent ke Mahasiswa (format backend)
fun UpdateUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)
