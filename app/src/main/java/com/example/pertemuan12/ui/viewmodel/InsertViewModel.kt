package com.example.pertemuan12.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.launch

// ViewModel untuk proses Insert data Mahasiswa
class InsertViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    // State buat nampung inputan user
    var uiState by mutableStateOf(InsertUiState())
        private set // Biar enggak diubah langsung dari luar

    // Update state input kalo ada event baru
    fun updateInsertMhsState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    // Fungsi buat insert data mahasiswa
    suspend fun insertMhs() {
        viewModelScope.launch { // Lari di background (asycn), biar UI gak ngelag
            try {
                mhs.insertMahasiswa(uiState.insertUiEvent.toMhs()) // Panggil fungsi insert dari repository
            } catch (e: Exception) {
                e.printStackTrace() // Error? Cetak aja, biar gampang debug
            }
        }
    }
}

// Data State buat nampung UI-nya
data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent() // Default kosong
)

// Event user: input NIM, Nama, Alamat, dll.
data class InsertUiEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

// Konversi dari InsertUiEvent ke Mahasiswa (format backend)
fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)

// Konversi dari Mahasiswa ke InsertUiState (format UI)
fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = tolnsertUiEvent()
)

// Konversi dari Mahasiswa ke InsertUiEvent (event input UI)
fun Mahasiswa.tolnsertUiEvent(): InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)
