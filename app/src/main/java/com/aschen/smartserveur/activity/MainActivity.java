package com.aschen.smartserveur.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.model.Session;
import com.aschen.smartserveur.service.SessionService;
import com.aschen.smartserveur.service.SmartServeurService;
import com.aschen.smartserveur.model.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity
{
    private TextView  _name, _id, _imageUrl;
    private ImageView _image;
    private SmartServeurService _serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _id = (TextView) findViewById(R.id.category_id);
        _name = (TextView) findViewById(R.id.category_name);
        _image = (ImageView) findViewById(R.id.category_image);
        _imageUrl = (TextView) findViewById(R.id.category_image_url);

        RestAdapter     adapter = new RestAdapter.Builder()
                                .setEndpoint(SessionService.URL_API)
                                .build();

        _serviceApi = adapter.create(SmartServeurService.class);
    }


    public void GetCategories(View v)
    {
        Callback callback = new Callback()
        {
            @Override
            public void success(Object o, Response response)
            {
                for (ApiClient.Category c : (List<ApiClient.Category>) o)
                {
                    _id.setText(c.id + "");
                    _name.setText(c.name);
                    _imageUrl.setText(c.image);
                    Picasso.with(getApplicationContext()).load(c.image).into(_image);
                }

            }

            @Override
            public void failure(RetrofitError retrofitError)
            {
                _id.setText(retrofitError.getUrl());
                _name.setText(retrofitError.getMessage());
            }
        };

        _serviceApi.getCategories(callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void ShowCategories(View v)
    {
        Intent showCategories = new Intent(MainActivity.this, ShowCategoriesActivity.class);

        startActivity(showCategories);
    }

    public void GetSession(View v)
    {
        _serviceApi.getSession(21, new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                _id.setText(session.id() + "");
                _name.setText(session.table_id() + "");
                _imageUrl.setText(session.expired() == true ? "Expiré" : "Valide");
            }

            @Override
            public void failure(RetrofitError error)
            {

            }
        });
    }

    public void UpdateSession(View v)
    {
        Session session = new Session(true, 2);
        session.id(21);

        _serviceApi.updateSession(session.id(), session, new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                _id.setText("Success");
            }

            @Override
            public void failure(RetrofitError error)
            {
                _id.setText(error.getMessage());
            }
        });
    }

    public void deleteSession(View v)
    {
        _serviceApi.deleteSession(21, new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                _id.setText("Success");
            }

            @Override
            public void failure(RetrofitError error)
            {
                _id.setText(error.getMessage());
            }
        });
    }

    public void CreateSession(View v)
    {
        Session session = new Session(false, 60);

        _serviceApi.createSession(session, new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                _id.setText(session.id() + "");
                _name.setText(session.table_id() + "");
                _imageUrl.setText(session.expired() == true ? "Expiré" : "Valide");
            }

            @Override
            public void failure(RetrofitError error)
            {
                _id.setText(error.getMessage());
            }
        });
    }
}
