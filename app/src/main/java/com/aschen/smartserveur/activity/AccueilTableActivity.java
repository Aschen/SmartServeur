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

public class AccueilTableActivity extends ActionBarActivity
{

    private SessionService      _sessionService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_table);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(SessionService.URL_API)
                .build();
        _sessionService = adapter.create(SessionService.class);
    }


    public void ShowCategories(View v)
    {
        Intent showCategories = new Intent(AccueilTableActivity.this, ShowCategoriesActivity.class);

        startActivity(showCategories);
    }

    public void ShowOrders(View v)
    {
        Intent showOrders = new Intent(AccueilTableActivity.this, ShowOrdersActivity.class);

        startActivity(showOrders);
    }

    public void Exit(View v)
    {
        _sessionService.getSession(DataHolder.getInstance().sessionId(), new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                session.expired(true);

                _sessionService.updateSession(session.id(),session, new Callback<Session>()
                {

                    @Override
                    public void success(Session session, Response response)
                    {
                        DataHolder.getInstance().sessionId(-1);
                        Toast.makeText(AccueilTableActivity.this, "Au revoir et à bientôt !", Toast.LENGTH_SHORT).show();
                        Intent  main = new Intent(AccueilTableActivity.this, MainActivity.class);

                        startActivity(main);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Toast.makeText(AccueilTableActivity.this, "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(AccueilTableActivity.this, "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil_table, menu);
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
