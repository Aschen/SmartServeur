package com.aschen.smartserveur.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.model.Order;
import com.aschen.smartserveur.model.Product;
import com.aschen.smartserveur.service.CategoryService;
import com.aschen.smartserveur.service.OrderService;
import com.aschen.smartserveur.service.ProductService;
import com.aschen.smartserveur.tools.DataHolder;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShowProductActivity extends ActionBarActivity
{
    private int             _productId;
    private int             _productQuantity = 0;

    private OrderService    _orderService;

    private TextView        _name;
    private TextView        _description;
    private TextView        _price;
    private EditText        _quantity;
    private ImageView       _image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        _name = (TextView) findViewById(R.id.show_product_name);
        _description = (TextView) findViewById(R.id.show_product_description);
        _price = (TextView) findViewById(R.id.show_product_price);
        _image = (ImageView) findViewById(R.id.show_product_image);
        _quantity = (EditText) findViewById(R.id.show_product_quantity);

        _quantity.setFocusable(false);

        Bundle  extras = getIntent().getExtras();
        if (extras != null)
        {
            _productId = extras.getInt("product_id");
        }

        /* Get order service */
        _orderService = new RestAdapter.Builder()
                        .setEndpoint(OrderService.URL_API)
                        .build().create(OrderService.class);

        ProductService productService = new RestAdapter.Builder()
                                        .setEndpoint(ProductService.URL_API)
                                        .build().create(ProductService.class);

        productService.getProduct(_productId, new Callback<Product>()
        {
            @Override
            public void success(Product product, Response response)
            {
                _name.setText(product.name());
                _description.setText(product.description());
                _price.setText(product.price() + " €");

                Picasso.with(ShowProductActivity.this).load(ProductService.URL_API + product.image()).resize(400, 400).into(_image);
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(), "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Ajouter(View v)
    {
        _productQuantity += 1;
        _quantity.setText(_productQuantity + "");
    }

    public void Enlever(View v)
    {
        _productQuantity = (_productQuantity - 1) < 0 ? 0 : _productQuantity - 1;
        _quantity.setText(_productQuantity + "");
    }

    public void Commander(View v)
    {
        _orderService.createOrder(new Order(_productQuantity, DataHolder.getInstance().sessionId(), _productId, false), new Callback<Order>()
        {
            @Override
            public void success(Order order, Response response)
            {
                Toast.makeText(getApplicationContext(), "Commande de " + _productQuantity + " " + _name.getText(), Toast.LENGTH_SHORT).show();
                _productQuantity = 0;
                _quantity.setText("0");
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(), "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_product, menu);
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
