package com.aschen.smartserveur.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.activity.ShowCategoriesActivity;
import com.aschen.smartserveur.activity.ShowProductsActivity;
import com.aschen.smartserveur.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aschen on 02/07/2015.
 */
public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder>
{
    private List<Category> _categories;

    public CategoryViewAdapter(List<Category> categories)
    {
        _categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);

        ViewHolder  vh = new ViewHolder(parent.getContext(), v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        Category        current = _categories.get(i);

        viewHolder.mCategoryName.setText(current.name());
        viewHolder.mCategoryId.setText(current.id().toString());
        Picasso.with(viewHolder.mContext).load(current.image()).resize(100, 100).into(viewHolder.mCategoryImage);
    }

    @Override
    public int getItemCount() { return _categories.size(); }


    /*
     ** VIEW HOLDER **
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public Context         mContext;
        public TextView        mCategoryName;
        public TextView        mCategoryId;
        public ImageView       mCategoryImage;

        private LinearLayout    _container;

        public ViewHolder(final Context context, View view)
        {
            super(view);

            mContext = context;
            mCategoryName = (TextView) view.findViewById(R.id.category_item_name);
            mCategoryImage = (ImageView) view.findViewById(R.id.category_item_image);
            mCategoryId = (TextView) view.findViewById(R.id.category_item_id);
            _container = (LinearLayout) view.findViewById(R.id.category_container);

            _container.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent showProducts = new Intent(context, ShowProductsActivity.class);

                    showProducts.putExtra("category_id", Integer.parseInt(mCategoryId.getText().toString()));
                    context.startActivity(showProducts);
                }
            });
        }
    }


}
