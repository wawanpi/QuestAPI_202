package com.example.pertemuan12.dependeciesinjection

import com.example.pertemuan12.repository.MahasiswaRepository
import com.example.pertemuan12.repository.NetworkMahasiswaRepository
import com.example.pertemuan12.service.MahasiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// Interface yang mendefinisikan container aplikasi untuk menyimpan semua dependency
interface AppContainer {
    val kontakRepository: MahasiswaRepository
}

// Implementasi AppContainer khusus untuk Mahasiswa
class MahasiswaContainer : AppContainer {

    // URL dasar untuk mengakses API backend
    private val baseUrl = "http://10.0.2.2:8000/umyTI/"
    // Note: 10.0.2.2 adalah localhost untuk emulator. Ganti dengan IP perangkat jika diakses dari perangkat lain.

    // Konfigurasi Json untuk menangani data yang tidak dikenal (ignoreUnknownKeys)
    private val json = Json { ignoreUnknownKeys = true }

    // Membuat instance Retrofit dengan konfigurasi JSON dan baseUrl
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // Converter JSON ke objek Kotlin
        .baseUrl(baseUrl)
        .build()

    // Lazy initialization (dibuat hanya ketika dipanggil) untuk MahasiswaService
    private val mahasiswaService: MahasiswaService by lazy {
        retrofit.create(MahasiswaService::class.java) // Membuat instance dari interface API
    }

    // Lazy initialization untuk repository Mahasiswa, menggunakan NetworkMahasiswaRepository
    override val kontakRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(mahasiswaService) // Menggunakan MahasiswaService sebagai dependensinya
    }
}
