package com.learning.firebasecrud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {

    private List<ModelBarang> listBrg;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterBarang(List<ModelBarang> listBrg, Activity activity){
        this.listBrg = listBrg;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.item_barang, parent, false);

        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelBarang data = listBrg.get(position);

        holder.kodeBrg.append(String.valueOf(data.getKode_brg()));
        holder.namaBrg.append(String.valueOf(data.getNama_brg()));
        holder.hrgBeli.append(String.valueOf(data.getHrg_beli()));
        holder.hrgJual.append(String.valueOf(data.getHrg_jual()));
        holder.stanBrg.append(String.valueOf(data.getStan_brg()));
        holder.stokBrg.append(String.valueOf(data.getStok_brg()));
        holder.stokMin.append(String.valueOf(data.getStok_min()));

        holder.btnDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child("barang").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity,
                                        "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity,
                                        "Data gagal dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setMessage("Apakah anda yakin untuk menghapus barang " + data.getKode_brg() + "?");
                builder.show();
            }
        });

        holder.cardBrg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intentUpd = new Intent(activity.getBaseContext(), UpdBarangActivity.class);
                intentUpd.putExtra("kodeBrg", String.valueOf(data.getKode_brg()));
                intentUpd.putExtra("namaBrg", String.valueOf(data.getNama_brg()));
                intentUpd.putExtra("hrgBeli", String.valueOf(data.getHrg_beli()));
                intentUpd.putExtra("hrgJual", String.valueOf(data.getHrg_jual()));
                intentUpd.putExtra("stanBrg", String.valueOf(data.getStan_brg()));
                intentUpd.putExtra("stokBrg", String.valueOf(data.getStok_brg()));
                intentUpd.putExtra("stokMin", String.valueOf(data.getStok_min()));
                intentUpd.putExtra("KEY", String.valueOf(data.getKey()));
                activity.startActivityForResult(intentUpd, 1);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBrg.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kodeBrg, namaBrg, hrgBeli, hrgJual, stanBrg, stokBrg, stokMin;
        ImageView btnDele;
        CardView cardBrg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            kodeBrg = itemView.findViewById(R.id.kodeBrg);
            namaBrg = itemView.findViewById(R.id.namaBrg);
            hrgBeli = itemView.findViewById(R.id.hrgBeli);
            hrgJual = itemView.findViewById(R.id.hrgJual);
            stanBrg = itemView.findViewById(R.id.stanBrg);
            stokBrg = itemView.findViewById(R.id.stokBrg);
            stokMin = itemView.findViewById(R.id.stokMin);
            btnDele = itemView.findViewById(R.id.btnDele);
            cardBrg = itemView.findViewById(R.id.cardBrg);
        }
    }
}
