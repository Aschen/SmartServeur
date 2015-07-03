package com.aschen.smartserveur.model;

/**
 * Created by Aschen on 03/07/2015.
 */
public class Order
{
    private Number  id;
    private Number  quantity;
    private Number  session_id;
    private Number  product_id;
    private boolean served;

    public Order(Number nQuantity, Number nSessionId, Number nProductId, boolean nServed)
    {
        quantity = nQuantity;
        session_id = nSessionId;
        product_id = nProductId;
        served = nServed;
    }

    public Number id() { return id; }
    public void id(Number nId) { id = nId; }

    public Number quantity() { return quantity; }
    public void quantity(Number nQuantity) { quantity = nQuantity; }

    public Number sessionId() { return session_id; }
    public void sessionId(Number nSessionId) { session_id = nSessionId; }

    public Number productId() { return product_id; }
    public void productId(Number nProductId) { product_id = nProductId; }

    public boolean served() { return served; }
    public void served(boolean nServed) { served = nServed; }
}
