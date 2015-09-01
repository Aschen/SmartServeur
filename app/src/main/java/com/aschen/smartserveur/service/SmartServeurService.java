package com.aschen.smartserveur.service;

import com.aschen.smartserveur.model.Category;
import com.aschen.smartserveur.model.Product;
import com.aschen.smartserveur.model.Session;

import java.util.List;

import retrofit.Callback;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Aschen on 15/05/2015.
 */
public interface SmartServeurService
{
    public static final String URL_API = "http://rails.aschen.ovh/";

    @GET("/products.json")
    public void getProducts(Callback<List<Product>> response);


    @GET("/categories.json")
    public void getCategories(Callback<List<Category>> response);


    @GET("/sessions/{id}.json")
    public void getSession(@Path("id") Number id, Callback<Session> response);

    @POST("/sessions.json")
    public void createSession(@Body Session session, Callback<Session> response);

    @PUT("/sessions/{id}.json")
    public void updateSession(@Path("id") Number id, @Body Session session, Callback<Session> response);

    @DELETE("/sessions/{id}.json")
    public void deleteSession(@Path("id") Number id, Callback<Session> response);
}
