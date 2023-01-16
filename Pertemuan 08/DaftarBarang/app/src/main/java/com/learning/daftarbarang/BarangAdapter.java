package com.learning.daftarbarang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList kode_brg, nama_brg, hrg_beli, hrg_jual, stok_brg;

    BarangAdapter(Activity activity, Context context,
                  ArrayList kode_brg, ArrayList nama_brg,
                  ArrayList hrg_beli, ArrayList hrg_jual,
                  ArrayList stok_brg) {
        this.activity = activity;
        this.context = context;
        this.kode_brg = kode_brg;
        this.nama_brg = nama_brg;
        this.hrg_beli = hrg_beli;
        this.hrg_jual = hrg_jual;
        this.stok_brg = stok_brg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_barang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.kodeBrg.append(String.valueOf(kode_brg.get(position)));
        holder.namaBrg.append(String.valueOf(nama_brg.get(position)));
        holder.hrgBeli.append(String.valueOf(hrg_beli.get(position)));
        holder.hrgJual.append(String.valueOf(hrg_jual.get(position)));
        holder.stokBrg.append(String.valueOf(stok_brg.get(position)));
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context, holder.btnMore,
                        Gravity.END, 0, R.style.MenuStyle);

                popupMenu.getMenuInflater().inflate(R.menu.menu_more, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.update:
                                Intent intentUpd = new Intent(context, UpdBarangActivity.class);
                                intentUpd.putExtra("kodeBrg",
                                        String.valueOf(kode_brg.get(position)));
                                intentUpd.putExtra("namaBrg",
                                        String.valueOf(nama_brg.get(position)));
                                intentUpd.putExtra("hrgBeli",
                                        String.valueOf(hrg_beli.get(position)));
                                intentUpd.putExtra("hrgJual",
                                        String.valueOf(hrg_jual.get(position)));
                                intentUpd.putExtra("stokBrg",
                                        String.valueOf(stok_brg.get(position)));

                                activity.startActivityForResult(intentUpd, 1);
                                return true;
                            case R.id.delete:
                                BarangDatabaseHelper dbHelper = new BarangDatabaseHelper(context);
                                dbHelper.delBarang(String.valueOf(kode_brg.get(position)));

                                Intent intentDel = new Intent(context, context.getClass());
                                activity.startActivityForResult(intentDel, 1);
                                return true;
                            default:
                                return onMenuItemClick(menuItem);
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kode_brg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView kodeBrg, namaBrg, hrgBeli, hrgJual, stokBrg;
        ImageView btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            kodeBrg = itemView.findViewById(R.id.kodeBrg);
            namaBrg = itemView.findViewById(R.id.namaBrg);
            hrgBeli = itemView.findViewById(R.id.hrgBeli);
            hrgJual = itemView.findViewById(R.id.hrgJual);
            stokBrg = itemView.findViewById(R.id.stokBrg);
            btnMore = itemView.findViewById(R.id.btnMore);
        }
    }
}
