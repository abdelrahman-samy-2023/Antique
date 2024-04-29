package com.example.gp.Models;

import com.example.gp.AuctionModel.Authentication1;
import com.example.gp.GetProd.AuctionProduct;
import com.example.gp.GetProd.Authentication3;
import com.example.gp.SingleAuctionModel.Authentication2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @Headers({"Accept: application/json"})
    @POST("auth/register")
    @FormUrlEncoded
    Call<Authentication> reg(@Field("name") String name, @Field("email") String email,@Field("password") String password,@Field("password_confirmation") String password_confirmation);


    @Headers({"Accept: application/json"})
    @POST("auth/login")
    @FormUrlEncoded
    Call<Authentication> login(@Field("email") String email,@Field("password") String password);




    @Headers({"Accept: application/json"})
    @POST("checkCode")
    @FormUrlEncoded
    Call <Authentication> checkcode(
            @Header("Authorization") String  token);


    @Headers({"Accept: application/json"})
    @GET("auctions")
    Call<Authentication1> getAuction(@Header("Authorization") String  token);

    @Headers({"Accept: application/json"})
    @GET("auctions/{id}")
    Call<Authentication2> getOneAuction(@Header("Authorization") String  token, @Path("id")int id);

    @Headers({"Accept: application/json"})
    @GET("auctions/products/{id}")
    Call<Authentication3> getOneProduct(@Header("Authorization") String  token, @Path("id")int id);

    @Headers({"Accept: application/json"})
    @POST("auth/logout")
    Call<Authentication> logut(@Header("Authorization") String  token);




}
