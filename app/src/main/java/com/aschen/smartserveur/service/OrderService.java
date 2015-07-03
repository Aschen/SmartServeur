package com.aschen.smartserveur.service;

import com.aschen.smartserveur.model.Order;
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
public interface OrderService
{
    public static final String URL_API = "http://rails.aschen.ovh/";

    @GET("/orders/from_table/{id}")
    public void getOrderFromTable(@Path("id") Number id, Callback<List<Order>> response);

    @POST("/orders.json")
    public void createOrder(@Body Order order, Callback<Order> response);

    @PUT("/orders/{id}.json")
    public void updateOrder(@Path("id") Number id, @Body Order orders, Callback<Order> response);

    @DELETE("/orders/{id}.json")
    public void deleteOrder(@Path("id") Number id, Callback<Order> response);
}
