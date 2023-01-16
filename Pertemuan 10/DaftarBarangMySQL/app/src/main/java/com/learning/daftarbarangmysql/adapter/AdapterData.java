package com.learning.daftarbarangmysql.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.daftarbarangmysql.InsertDataActivity;
import com.learning.daftarbarangmysql.R;
import com.learning.daftarbarangmysql.model.ModelData;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems ;
    private Context context;

    public AdapterData(Context context, List<ModelData> mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
        HolderData holderData = new HolderData(layout);

        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);

        holder.kodeBrgTV.append(md.getKode_brg());
        holder.namaBrgTV.append(md.getNama_brg());
        holder.hrgBeliTV.append(md.getHrg_beli());
        holder.hrgJualTV.append(md.getHrg_jual());
        holder.stokBrgTV.append(md.getStok_brg());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView kodeBrgTV, namaBrgTV, hrgBeliTV, hrgJualTV, stokBrgTV;
        ModelData md;

        public HolderData(View view) {
            super(view);

            kodeBrgTV = itemView.findViewById(R.id.kodeBrgTV);
            namaBrgTV = itemView.findViewById(R.id.namaBrgTV);
            hrgBeliTV = itemView.findViewById(R.id.hrgBeliTV);
            hrgJualTV = itemView.findViewById(R.id.hrgJualTV);
            stokBrgTV = itemView.findViewById(R.id.stokBrgTV);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertDataActivity.class);
                    update.putExtra("update", 1);
                    update.putExtra("kode_brg", md.getKode_brg());
                    update.putExtra("nama_brg", md.getNama_brg());
                    update.putExtra("hrg_beli", md.getHrg_beli());
                    update.putExtra("hrg_jual", md.getHrg_jual());
                    update.putExtra("stok_brg", md.getStok_brg());

                    context.startActivity(update);
                }
            });
        }
    }
}
