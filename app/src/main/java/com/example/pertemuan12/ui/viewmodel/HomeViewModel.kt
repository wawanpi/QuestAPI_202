package com.example.pertemuan12.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Status UI buat nampilin kondisi data: sukses, error, atau loading
sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState() // Kalau data berhasil diambil
    object Error : HomeUiState() // Kalau ada error
    object Loading : HomeUiState() // Kalau lagi loading
}

class HomeViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    // State untuk nyimpen kondisi UI
    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    // Langsung ambil data pas ViewModel dibuat
    init {
        getMhs()
    }

    // Ambil data mahasiswa dari repository
    fun getMhs() {
        viewModelScope.launch { // Coroutine biar proses ambil data di background
            mhsUIState = HomeUiState.Loading // Kasih tau UI lagi loading

            mhsUIState = try {
                HomeUiState.Success(mhs.getMahasiswa()) // Kalau berhasil, set ke sukses
            } catch (e: IOException) {
                HomeUiState.Error // Kalau ada error karena koneksi
            } catch (e: HttpException) {
                HomeUiState.Error // Kalau error dari server
            }
        }
    }

    // Hapus mahasiswa berdasarkan NIM
    fun deleteMhs(nim: String) {
        viewModelScope.launch { // Lagi-lagi coroutine biar lancar
            try {
                mhs.deleteMahasiswa(nim) // Hapus data di repo
            } catch (e: IOException) {
                HomeUiState.Error // Kalau koneksi bermasalah
            } catch (e: HttpException) {
                HomeUiState.Error // Kalau server error
            }
        }
    }
}
