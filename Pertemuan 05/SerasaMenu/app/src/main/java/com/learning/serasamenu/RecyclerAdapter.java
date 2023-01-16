package com.learning.serasamenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<MenuModel> dataMenu;

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
            if(clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }

    RecyclerAdapter(ArrayList<MenuModel> data) {
        this.dataMenu = data;
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

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
                (new Locale("in", "ID"));

        menu_Name.setText(dataMenu.get(position).getMenuName());
        menu_Price.setText(formatRupiah.format(dataMenu.get(position).getMenuPrice()));
        menu_img.setImageResource(dataMenu.get(position).getMenuImg());
    }

    @Override
    public int getItemCount() {
        return dataMenu.size();
    }
}
