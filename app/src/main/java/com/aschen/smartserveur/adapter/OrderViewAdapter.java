package com.aschen.smartserveur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.model.Order;

import java.util.List;

/**
 * Created by Aschen on 03/07/2015.
 */
public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.ViewHolder>
{
    private List<Order> _orders;

    public OrderViewAdapter(List<Order> orders) { _orders = orders; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);

        ViewHolder  vh = new ViewHolder(parent.getContext(), v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        Order   current = _orders.get(i);

        viewHolder.mName.setText(current.productId() + "");
        viewHolder.mQuantity.setText(current.quantity() + "");
        viewHolder.mServed.setText(current.served() ? "Servie" : "En attente");
        viewHolder.mId.setText(current.id() + "");
    }

    @Override
    public int getItemCount() { return _orders.size(); }


    /*
     ** VIEW HOLDER **
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public Context      mContext;
        public ImageView    mImage;
        public TextView     mName;
        public TextView     mQuantity;
        public TextView     mServed;
        public TextView     mId;
        public Button       mCancel;

        public ViewHolder(final Context context, View view)
        {
            super(view);

            mContext = context;
            mImage = (ImageView) view.findViewById(R.id.order_item_image);
            mName = (TextView) view.findViewById(R.id.order_item_name);
            mQuantity = (TextView) view.findViewById(R.id.order_item_quantity);
            mServed = (TextView) view.findViewById(R.id.order_item_served);
            mId = (TextView) view.findViewById(R.id.order_item_id);
            mCancel = (Button) view.findViewById(R.id.order_item_button);

            mCancel.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(context, "ID : " + mId.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
