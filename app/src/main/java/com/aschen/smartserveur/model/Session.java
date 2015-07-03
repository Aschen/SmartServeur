package com.aschen.smartserveur.model;

import retrofit.RestAdapter;

/**
 * Created by Aschen on 02/07/2015.
 */
public class Session
{
    private Number  id;
    private boolean expired;
    private Number  table_id;

    public Session(boolean newExpired, Number newTableId)
    {
        expired = newExpired;
        table_id = newTableId;
        id = -1;
    }

    public Number id() { return id; }
    public void id(Number newId) { id = newId; }

    public boolean expired() { return expired; }
    public void expired(boolean newExpired) { expired = newExpired;}

    public Number table_id() { return table_id; }
    public void table_id(Number newTableId) { table_id = newTableId; }
}
