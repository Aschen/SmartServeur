package com.aschen.smartserveur.tools;

/**
 * Created by Aschen on 03/07/2015.
 */
public class DataHolder
{
    private Number      _sessionId;

    public Number sessionId() { return _sessionId; }
    public void sessionId(Number newSessionId) { _sessionId = newSessionId; }


    /* Singleton stuff */
    private static final DataHolder _holder = new DataHolder();
    private DataHolder() {}
    public static DataHolder getInstance() { return _holder; }
}
