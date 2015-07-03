package com.aschen.smartserveur.service;

import com.aschen.smartserveur.model.Category;
import com.aschen.smartserveur.model.Session;
import com.aschen.smartserveur.model.Table;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Aschen on 15/05/2015.
 */
public interface TableService
{
    public static final String URL_API = "http://rails.aschen.ovh/";

    @GET("/tables/from_number/{id}")
    public void getTableFromNumber(@Path("id") Number id, Callback<Table> response);
}
