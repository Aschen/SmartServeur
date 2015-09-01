package com.aschen.smartserveur.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.adapter.CategoryViewAdapter;
import com.aschen.smartserveur.adapter.OrderViewAdapter;
import com.aschen.smartserveur.model.Category;
import com.aschen.smartserveur.model.Order;
import com.aschen.smartserveur.service.CategoryService;
import com.aschen.smartserveur.service.OrderService;
import com.aschen.smartserveur.tools.DataHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShowOrdersActivity extends ActionBarActivity
{
    private RecyclerView    _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);

        _recyclerView = (RecyclerView) findViewById(R.id.order_recycler_view);

        /* Get data from webservice */
        OrderService orderService = new RestAdapter.Builder()
                                        .setEndpoint(OrderService.URL_API)
                                        .build().create(OrderService.class);
        orderService.getOrderFromSession(DataHolder.getInstance().sessionId(), new Callback<List<Order>>()
        {
            @Override
            public void success(List<Order> orders, Response response)
            {
                for (Order order : orders)
                {
                    order.product().image(OrderService.URL_API + order.product().image());
                }
                _recyclerView.setAdapter(new OrderViewAdapter(orders));
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(), "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        _recyclerView.setAdapter(new OrderViewAdapter(new ArrayList<Order>()));
        _recyclerView.setLayoutManager(new LinearLayoutManager(ShowOrdersActivity.this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_orders, menu);
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
