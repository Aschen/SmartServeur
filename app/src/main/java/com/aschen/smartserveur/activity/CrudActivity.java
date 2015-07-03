package com.aschen.smartserveur.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aschen.smartserveur.R;

public class CrudActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crud, menu);
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

    public void ProductsAdmin(View v)
    {
        Intent intent = new Intent(CrudActivity.this, ProductsActivity.class);

        startActivity(intent);
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
}
