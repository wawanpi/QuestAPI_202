package com.example.pertemuan12.service

import com.example.pertemuan12.model.Mahasiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

// Interface Retrofit untuk mengakses layanan Mahasiswa
interface MahasiswaService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    // Mengambil semua data mahasiswa
    @GET("bacamahasiswa.php")
    suspend fun getAllMahasiswa(): List<Mahasiswa>

    // Mengambil data mahasiswa berdasarkan NIM
    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswabyNim(@Query("nim") nim: String): Mahasiswa

    // Menambahkan mahasiswa baru ke database
    @POST("insertmahasiswa.php")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    // Memperbarui data mahasiswa berdasarkan NIM
    @PUT("editmahasiswa.php/{nim}")
    suspend fun updateMahasiswa(@Query("nim") nim: String, @Body mahasiswa: Mahasiswa)

    // Menghapus mahasiswa berdasarkan NIM
    @DELETE("deletemahasiswa.php/{nim}")
    suspend fun deleteMahasiswa(@Query("nim") nim: String): Response<Void>
}
