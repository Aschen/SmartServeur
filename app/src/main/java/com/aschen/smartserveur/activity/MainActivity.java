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


public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Flasher(View v)
    {
        Intent  flasher = new Intent(MainActivity.this, FlashCodeActivity.class);

        startActivity(flasher);
    }


//    public void ShowCategories(View v)
//    {
//        Intent showCategories = new Intent(MainActivity.this, ShowCategoriesActivity.class);
//
//        startActivity(showCategories);
//    }
//
//    public void GetSession(View v)
//    {
//        _serviceApi.getSession(21, new Callback<Session>()
//        {
//            @Override
//            public void success(Session session, Response response)
//            {
//                _id.setText(session.id() + "");
//                _name.setText(session.table_id() + "");
//                _imageUrl.setText(session.expired() == true ? "Expiré" : "Valide");
//            }
//
//            @Override
//            public void failure(RetrofitError error)
//            {
//
//            }
//        });
//    }
//
//    public void UpdateSession(View v)
//    {
//        Session session = new Session(true, 2);
//        session.id(21);
//
//        _serviceApi.updateSession(session.id(), session, new Callback<Session>()
//        {
//            @Override
//            public void success(Session session, Response response)
//            {
//                _id.setText("Success");
//            }
//
//            @Override
//            public void failure(RetrofitError error)
//            {
//                _id.setText(error.getMessage());
//            }
//        });
//    }
//
//    public void deleteSession(View v)
//    {
//        _serviceApi.deleteSession(21, new Callback<Session>()
//        {
//            @Override
//            public void success(Session session, Response response)
//            {
//                _id.setText("Success");
//            }
//
//            @Override
//            public void failure(RetrofitError error)
//            {
//                _id.setText(error.getMessage());
//            }
//        });
//    }
//
//    public void CreateSession(View v)
//    {
//        Session session = new Session(false, 60);
//
//        _serviceApi.createSession(session, new Callback<Session>()
//        {
//            @Override
//            public void success(Session session, Response response)
//            {
//                _id.setText(session.id() + "");
//                _name.setText(session.table_id() + "");
//                _imageUrl.setText(session.expired() == true ? "Expiré" : "Valide");
//            }
//
//            @Override
//            public void failure(RetrofitError error)
//            {
//                _id.setText(error.getMessage());
//            }
//        });
//    }

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
}
