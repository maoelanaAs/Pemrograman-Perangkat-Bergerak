package com.learning.modifutsmysql.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.modifutsmysql.R;
import com.learning.modifutsmysql.model.ProductModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private ArrayList<ProductModel> dataList;
    private String KEY_TOTAL="TOTAL";
    public double total=0;
    NumberFormat rpFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

    public ProductAdapter(ArrayList<ProductModel> dataList) {
        this.dataList = dataList;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item, parent, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        ProductModel data = dataList.get(position);
        Double price = Double.valueOf(data.getProduct_prce());

        holder.productName.setText(data.getProduct_name());
        holder.productPrice.setText(rpFormat.format(price));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView productName, productPrice;
        private ImageView productImg;

        public ProductHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImg = itemView.findViewById(R.id.productImg);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            productName.setOnClickListener(this);
            productImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
