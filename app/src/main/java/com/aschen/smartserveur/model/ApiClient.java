package com.aschen.smartserveur.model;
import android.graphics.Bitmap;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Aschen on 15/05/2015.
 */
public class ApiClient
{
    private static final String     _urlAPI = "http://rails.aschen.ovh";

    public static class Category
    {
        public int     id;
        public String  name;
        public String  image;
    }

    public static String urlApi() { return _urlAPI; }
}
