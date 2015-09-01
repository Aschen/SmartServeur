package com.aschen.smartserveur.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.adapter.CategoryViewAdapter;
import com.aschen.smartserveur.adapter.ProductViewAdapter;
import com.aschen.smartserveur.model.Category;
import com.aschen.smartserveur.model.Product;
import com.aschen.smartserveur.service.ProductService;
import com.aschen.smartserveur.service.SmartServeurService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShowProductsActivity extends ActionBarActivity
{
    private static int          _categoryId;
    private RecyclerView        _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        _recyclerView = (RecyclerView) findViewById(R.id.product_recycler_view);

        Bundle  extras = getIntent().getExtras();
        if (extras != null)
        {
            _categoryId = extras.getInt("category_id");
        }

        ProductService productService = new RestAdapter.Builder()
                                        .setEndpoint(ProductService.URL_API)
                                        .build().create(ProductService.class);


        productService.getProductsFromCategory(_categoryId, new Callback<List<Product>>()
        {
            @Override
            public void success(List<Product> products, Response response)
            {
                for (Product product : products)
                {
                    product.image(ProductService.URL_API + product.image());
                }

                _recyclerView.setAdapter(new ProductViewAdapter(products));
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(), "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        _recyclerView.setAdapter(new ProductViewAdapter(new ArrayList<Product>()));
        _recyclerView.setLayoutManager(new GridLayoutManager(ShowProductsActivity.this, 2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_products, menu);
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
