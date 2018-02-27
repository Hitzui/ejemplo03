package innovasoft.com.ejemplo01.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.converter.ImageConverterFromUrl;
import innovasoft.com.ejemplo01.models.Articulos;

/**
 * Created by josemiguel on 26/02/2018.
 */

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.MyViewHolder> {

    public Articulos[] datos;
    ImageConverterFromUrl imageConverterFromUrl = new ImageConverterFromUrl();

    public ArticuloAdapter(Articulos[] lista) {
        this.datos = lista;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblDescripcion;
        public ImageView imgArticulo;

        public MyViewHolder(View view) {
            super(view);
            lblDescripcion = view.findViewById(R.id.title);
            imgArticulo = view.findViewById(R.id.imgArticulo);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.articulos_rows, viewGroup, false);
        MyViewHolder pvh = new MyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        imageConverterFromUrl.setImageView(holder.imgArticulo);
        imageConverterFromUrl.imageConverter(datos[position].getRutaimg());
        holder.lblDescripcion.setText(datos[position].getDescripcion());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return datos.length;
    }
}
