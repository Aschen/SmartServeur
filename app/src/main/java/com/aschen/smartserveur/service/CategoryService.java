package com.aschen.smartserveur.service;

import com.aschen.smartserveur.model.Category;
import com.aschen.smartserveur.model.Product;
import com.aschen.smartserveur.model.Session;

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
public interface CategoryService
{
    public static final String URL_API = "http://rails.aschen.ovh/";

    @GET("/categories.json")
    public void getCategories(Callback<List<Category>> response);
}
