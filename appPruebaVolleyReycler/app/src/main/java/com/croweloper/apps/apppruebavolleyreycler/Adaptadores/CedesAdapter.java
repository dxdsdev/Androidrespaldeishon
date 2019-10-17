package com.croweloper.apps.apppruebavolleyreycler.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croweloper.apps.apppruebavolleyreycler.Modelo.Cedes;
import com.croweloper.apps.apppruebavolleyreycler.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CedesAdapter extends RecyclerView.Adapter<CedesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cedes> lista;

    public CedesAdapter(Context context) {
        this.context = context;
        lista=new ArrayList<>();
    }

    public void agregar(ArrayList<Cedes> cedes){
        lista.clear();
        lista.addAll(cedes);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Cedes item=lista.get(i);
        viewHolder.tvId.setText("Id : "+item.getId());
        Picasso.with(context).load(item.getUrl()).fit().centerInside().into(viewHolder.ivImagenURL);
        viewHolder.tvAlbumId.setText("Album ID : "+item.getAlbumId());
        viewHolder.tvTitulo.setText("Titulo : "+item.getTitle());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        ImageView ivImagenURL;
        TextView tvAlbumId;
        TextView tvTitulo;
        CardView cContenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId=itemView.findViewById(R.id.tvId);
            ivImagenURL=itemView.findViewById(R.id.ivImagenURL);
            tvAlbumId=itemView.findViewById(R.id.tvAlbumId);
            tvTitulo=itemView.findViewById(R.id.tvTitulo);
            cContenedor=itemView.findViewById(R.id.cvContenedor);

        }
    }
}
