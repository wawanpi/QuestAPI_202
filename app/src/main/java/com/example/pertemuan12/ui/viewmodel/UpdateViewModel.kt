package com.example.pertemuan12.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.launch

// ViewModel untuk proses Update data Mahasiswa
class UpdateViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    // State untuk menyimpan inputan user
    var uiState by mutableStateOf(UpdateUiState())
        private set // Agar tidak dapat diubah langsung dari luar

    // Update state input jika ada perubahan data
    fun updateUpdateMhsState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    // Fungsi untuk update data mahasiswa
    fun updateMhs() {
        viewModelScope.launch { // Jalankan di background (async)
            try {
                mhs.updateMahasiswa(
                    nim = uiState.updateUiEvent.nim, // Parameter NIM
                    mahasiswa = uiState.updateUiEvent.toMhs() // Parameter Mahasiswa
                )
            } catch (e: Exception) {
                e.printStackTrace() // Cetak error untuk debugging
            }
        }
    }
}

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
