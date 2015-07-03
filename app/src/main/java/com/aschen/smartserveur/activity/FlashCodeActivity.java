package com.aschen.smartserveur.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.model.Session;
import com.aschen.smartserveur.service.SessionService;
import com.aschen.smartserveur.tools.DataHolder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FlashCodeActivity extends ActionBarActivity
{
    private SessionService _sessionService;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_code);


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(SessionService.URL_API)
                .build();

        _sessionService = adapter.create(SessionService.class);
    }

    public void Suite(View v)
    {
        _sessionService.createSession(new Session(false, 1), new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                Intent  flasher = new Intent(FlashCodeActivity.this, AccueilTableActivity.class);

                DataHolder.getInstance().sessionId(session.id());

                startActivity(flasher);
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(), "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
