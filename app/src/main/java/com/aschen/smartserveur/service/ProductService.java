package com.aschen.smartserveur.service;

import com.aschen.smartserveur.model.Product;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Aschen on 15/05/2015.
 */
public interface ProductService
{
    public static final String URL_API = "http://rails.aschen.ovh/";

    @GET("/products/{id}.json")
    public void getProduct(@Path("id") Number id, Callback<Product> response);

    @GET("/products/from_category/{id}")
    public void getProductsFromCategory(@Path("id") Number id, Callback<List<Product>> response);

    @POST("/products.json")
    public void createProduct(@Body Product product, Callback<Product> response);

    @PUT("/products/{id}.json")
    public void updateProduct(@Path("id") Number id, @Body Product product, Callback<Product> response);

    @DELETE("/products/{id}.json")
    public void deleteProduct(@Path("id") Number id, Callback<Product> response);
}
