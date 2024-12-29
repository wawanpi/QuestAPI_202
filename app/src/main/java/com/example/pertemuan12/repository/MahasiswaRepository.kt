package com.example.pertemuan12.repository

import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.service.MahasiswaService
import okio.IOException

// Interface untuk Repository Mahasiswa (definisi kontrak untuk operasi data)
interface MahasiswaRepository {
    // Mengambil daftar semua mahasiswa
    suspend fun getMahasiswa(): List<Mahasiswa>

    // Menambahkan data mahasiswa
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    // Memperbarui data mahasiswa berdasarkan NIM
    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    // Menghapus mahasiswa berdasarkan NIM
    suspend fun deleteMahasiswa(nim: String)

    // Mengambil data mahasiswa berdasarkan NIM
    suspend fun getMahasiswabyNim(nim: String): Mahasiswa
}

// Implementasi Repository menggunakan sumber data jaringan (NetworkMahasiswaRepository)
class NetworkMahasiswaRepository(
    private val mahasiswaApiService: MahasiswaService // Menggunakan API Mahasiswa sebagai sumber data
) : MahasiswaRepository {

    // Mengambil semua data mahasiswa menggunakan API service
    override suspend fun getMahasiswa(): List<Mahasiswa> =
        mahasiswaApiService.getAllMahasiswa()

    // Menambahkan data mahasiswa menggunakan API service
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    // Memperbarui data mahasiswa menggunakan API service
    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        mahasiswaApiService.updateMahasiswa(nim, mahasiswa)
    }

    // Menghapus data mahasiswa berdasarkan NIM
    override suspend fun deleteMahasiswa(nim: String) {
        try {
            // Melakukan penghapusan data dengan validasi status respon
            val response = mahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful) { // Jika respon gagal
                throw IOException(
                    "Failed to delete Mahasiswa. HTTP Status Code: ${response.code()}"
                )
            } else {
                println(response.message()) // Cetak pesan jika sukses
            }
        } catch (e: Exception) {
            // Menangani pengecualian
            throw e
        }
    }

    // Mengambil satu data mahasiswa berdasarkan NIM menggunakan API service
    override suspend fun getMahasiswabyNim(nim: String): Mahasiswa {
        return mahasiswaApiService.getMahasiswabyNim(nim)
    }
}
