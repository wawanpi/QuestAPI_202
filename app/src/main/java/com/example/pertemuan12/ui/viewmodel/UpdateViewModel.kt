package com.example.pertemuan12.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class UpdateViewModel(private val mhsRepository: MahasiswaRepository) : ViewModel() {

    var uiState by mutableStateOf(UpdateUiState())
        private set

    // Fetch existing Mahasiswa data based on ID and set the state
    fun loadMahasiswaData(mahasiswaId: String) {
        viewModelScope.launch {
            try {
                val mahasiswa = mhsRepository.getMahasiswabyNim(mahasiswaId)
                uiState = UpdateUiState(insertUiEvent = mahasiswa.tolnsertUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Update Mahasiswa data
    fun updateMhs() {
        viewModelScope.launch {
            try {
                val nim = uiState.insertUiEvent.nim
                val mahasiswa = uiState.insertUiEvent.toMhs()
                mhsRepository.updateMahasiswa(nim, mahasiswa)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent) {
        uiState = UpdateUiState(insertUiEvent = insertUiEvent)
    }
}

data class UpdateUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)