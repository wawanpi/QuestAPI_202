package com.example.pertemuan12.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pertemuan12.MahasiswaApplications

object PenyediaViewModel {
    // Factory untuk inisialisasi ViewModel yang diperlukan
    val Factory = viewModelFactory {
        // Inisialisasi ViewModel untuk halaman Home
        initializer {
            HomeViewModel(aplikasiMahasiswa().container.mahasiswaRepository)
            // Pake repository dari aplikasi untuk HomeViewModel
        }

        // Inisialisasi ViewModel untuk halaman Insert (Tambah Data)
        initializer {
            InsertViewModel(aplikasiMahasiswa().container.mahasiswaRepository)
            // Pake repository dari aplikasi untuk InsertViewModel
        }

        // Inisialisasi ViewModel untuk halaman Detail (Lihat Detail Data)
        initializer {
            DetailViewModel(aplikasiMahasiswa().container.mahasiswaRepository)
            // ViewModel untuk menampilkan detail mahasiswa
        }

        // Inisialisasi ViewModel untuk halaman Update (Update Data)
        initializer {
            UpdateViewModel(aplikasiMahasiswa().container.mahasiswaRepository)
            // ViewModel untuk update data mahasiswa
        }
    }

    // Extension function untuk akses instance `MahasiswaApplications` lewat `CreationExtras`
    fun CreationExtras.aplikasiMahasiswa(): MahasiswaApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)
    // Ambil aplikasi utama (cast dari Application) sebagai MahasiswaApplications
}
