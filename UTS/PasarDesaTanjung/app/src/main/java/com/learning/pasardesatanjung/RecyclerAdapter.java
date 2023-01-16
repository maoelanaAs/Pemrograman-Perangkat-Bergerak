package com.learning.pasardesatanjung;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<MenuModel> dataMenu;

    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
            (new Locale("in", "ID"));

    RecyclerAdapter(ArrayList<MenuModel> data) {
        this.dataMenu = data;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView menuName, menuPrice;
        ImageView menuImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menuName = itemView.findViewById(R.id.menuName);
            menuImg = itemView.findViewById(R.id.menuImg);
            menuPrice = itemView.findViewById(R.id.menuPrice);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            menuImg.setOnClickListener(this);
            menuName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        TextView menu_Name = holder.menuName;
        TextView menu_Price = holder.menuPrice;
        ImageView menu_img = holder.menuImg;

        MenuModel menu = dataMenu.get(position);
        menu_Name.setText(menu.getMenuName());
        menu_Price.setText(formatRupiah.format(menu.getMenuPrice()));
        menu_img.setImageResource(menu.getMenuImg());
    }

    @Override
    public int getItemCount() {
        return dataMenu.size();
    }
}
