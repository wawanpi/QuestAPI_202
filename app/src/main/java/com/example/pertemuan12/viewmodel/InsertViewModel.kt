package com.example.pertemuan12.viewmodel

import com.example.pertemuan12.model.Mahasiswa


data class InsertUiState(

    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)
data class InsertUiEvent(
        val nim: String = "",
        val nama: String = "",
        val alamat: String = "",
        val jenisKelamin: String = "",
        val kelas: String = "",
        val angkatan: String = ""
)

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(

    insertUiEvent = tolnsertUiEvent()

)

fun Mahasiswa.tolnsertUiEvent(): InsertUiEvent = InsertUiEvent(

    nim = nim,

    nama = nama,

    alamat = alamat,

    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)