package com.upt.moviles.veterinaria.util.api;

import com.upt.moviles.veterinaria.model.ResponseDosen;
import com.upt.moviles.veterinaria.model.ResponseDosenDetail;
import com.upt.moviles.veterinaria.model.ResponseMatkul;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface BaseApiService {

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password);

    @GET("semuadosen")
    Call<ResponseDosen> getSemuaDosen();

    @GET("dosen/{namadosen}")
    Call<ResponseDosenDetail> getDetailDosen(@Path("namadosen") String namadosen);

    @GET("matkul")
    Call<ResponseMatkul> getSemuaMatkul();

    @FormUrlEncoded
    @POST("matkul")
    Call<ResponseBody> simpanMatkulRequest(@Field("nama_dosen") String namadosen,
                                           @Field("matkul") String namamatkul);

    @DELETE("matkul/{idmatkul}")
    Call<ResponseBody> deteleMatkul(@Path("idmatkul") String idmatkul);
}
