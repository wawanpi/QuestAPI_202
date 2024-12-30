package com.example.pertemuan12.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Definisi state buat UI, ada tiga: Loading, Sukses, dan Error
sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState() // Kalau sukses dapet data
    object Error : DetailUiState() // Kalau error pas ambil data
    object Loading : DetailUiState() // State lagi loading
}

class DetailViewModel(private val mhsRepository: MahasiswaRepository) : ViewModel() {

    // Mutable state buat internal ViewModel (bisa diubah)
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)

    // StateFlow biar observer cuma bisa lihat, nggak bisa ubah
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    // Fungsi buat ambil data mahasiswa berdasarkan NIM
    fun getDetailMahasiswa(nim: String) {
        viewModelScope.launch {
            // Update state ke loading pas mulai ambil data
            _detailUiState.value = DetailUiState.Loading
            try {
                // Ambil data dari repository
                val mahasiswa = mhsRepository.getMahasiswabyNim(nim)
                // Update state ke sukses pas data berhasil diambil
                _detailUiState.value = DetailUiState.Success(mahasiswa)
            } catch (e: Exception) {
                // Update state ke error kalau ada exception
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}
