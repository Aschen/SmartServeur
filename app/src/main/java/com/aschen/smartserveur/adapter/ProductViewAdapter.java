package com.aschen.smartserveur.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.activity.ShowProductActivity;
import com.aschen.smartserveur.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aschen on 03/07/2015.
 */
public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder>
{
    private List<Product>       _products;

    public ProductViewAdapter(List<Product> products)
    {
        _products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        ViewHolder  vh = new ViewHolder(parent.getContext(), v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        Product     current = _products.get(i);

        viewHolder.mProductName.setText(current.name());
        viewHolder.mProductId.setText(current.id().toString());
        viewHolder.mProductPrice.setText(current.price() + " €");
        Picasso.with(viewHolder.mContext).load(current.image()).resize(100, 100).into(viewHolder.mProductImage);
    }

    @Override
    public int getItemCount() { return _products.size(); }


    /*
     ** VIEW HOLDER **
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public Context      mContext;
        public TextView     mProductName;
        public ImageView    mProductImage;
        public TextView     mProductId;
        public TextView     mProductDescription;
        public TextView     mProductPrice;

        private LinearLayout _container;

        public ViewHolder(final Context context, View view)
        {
            super(view);

            mContext = context;
            mProductName = (TextView) view.findViewById(R.id.product_item_name);
            mProductImage = (ImageView) view.findViewById(R.id.product_item_image);
            mProductId = (TextView) view.findViewById(R.id.product_item_id);
            mProductPrice = (TextView) view.findViewById(R.id.product_item_price);
            _container = (LinearLayout) view.findViewById(R.id.product_container);

            _container.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent showProduct = new Intent(context, ShowProductActivity.class);

                    showProduct.putExtra("product_id", Integer.parseInt(mProductId.getText().toString()));
                    context.startActivity(showProduct);
                }
            });
        }
    }
}
