package com.example.pertemuan12.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pertemuan12.MahasiswaApplications

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Inisialisasi ViewModel untuk Home
        initializer {
            HomeViewModel(aplikasiMahasiswa().container.mahasiswaRepository)
        }

        // Inisialisasi ViewModel untuk Insert
        initializer {
            InsertViewModel(aplikasiMahasiswa().container.mahasiswaRepository)
        }
    }
}


    fun CreationExtras.aplikasiMahasiswa(): MahasiswaApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)
