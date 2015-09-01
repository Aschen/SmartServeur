package com.aschen.smartserveur.model;

/**
 * Created by Aschen on 02/07/2015.
 */
public class Category
{
    private Number id;
    private String name;
    private String image;

    public Number id() { return id; }
    public void id(Number newId) { id = newId; }

    public String image(){ return image; }
    public void image(String newImage){ image = newImage; }

    public String name(){ return name; }
    public void name(String newName){ name = newName; }
}
