package com.aschen.smartserveur.model;

/**
 * Created by Aschen on 02/07/2015.
 */
public class Product
{
    private Number  id;
    private String  name;
    private String  image;
    private String  description;
    private Number  price;
    private Number  category_id;

    public Product(String newName, String newImage, String newDescription, Number newPrice, Number newCategoryId)
    {
        name = newName;
        image = newImage;
        description = newDescription;
        price = newPrice;
        category_id = newCategoryId;
        id = -1;
    }

    public Number id() { return id; }
    public void id(Number newId) { id = newId; }

    public String name() { return name; }
    public void name(String newName) { name = newName; }

    public String image() { return image; }
    public void image(String newImage) { image = newImage; }

    public String description() { return description; }
    public void description(String newDescription) { description = newDescription; }

    public Number price() { return price; }
    public void price(Number newPrice) { price = newPrice; }

    public Number categoryId() { return category_id; }
    public void categoryId(Number newCategoryId) { category_id = newCategoryId; }
}
