package com.android.vik.inventoryapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyProductRecyclerViewAdapter extends RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyProductRecyclerViewAdapter(List<Product> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = getValues().get(position);
        holder.textViewPrice.setText(holder.mItem.getPriceForDisplay());
        holder.textViewName.setText(holder.mItem.getName());
        holder.textViewQuantity.setText(String.valueOf(holder.mItem.getQuantity()));
        holder.saleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mItem.getQuantity() > 0) {
                    new ProductDetailsActivity().decrease(holder.mItem);
                    holder.textViewQuantity.setText(String.valueOf(holder.mItem.getQuantity()));
                }else {
                    Toast.makeText(v.getContext(), "Value can't be less than zero", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClickProduct(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getValues().size();
    }

    public List<Product> getValues() {
        return mValues;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewName;
        public final TextView textViewPrice;
        public final TextView textViewQuantity;
        public final ImageView saleImage;
        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewName = (TextView) view.findViewById(R.id.textViewNameItem);
            textViewPrice = (TextView) view.findViewById(R.id.textViewPriceItem);
            textViewQuantity = (TextView) view.findViewById(R.id.textViewQuantityItem);
            saleImage = (ImageView) view.findViewById(R.id.img);
        }
    }

    public interface OnListFragmentInteractionListener {
        void onClickProduct(Product item);
    }
}