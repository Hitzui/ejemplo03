package innovasoft.com.ejemplo01.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    Context context;
    private ImageConverterFromUrl imageConverterFromUrl = new ImageConverterFromUrl();

    public ArticuloAdapter(Context context, Articulos[] datos) {
        this.context = context;
        this.datos = datos;
    }

    public void setDatos(Articulos[] datos) {
        this.datos = datos;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblDescripcion;
        public ImageView imgArticulo;

        public MyViewHolder(View view) {
            super(view);
            lblDescripcion = view.findViewById(R.id.lblDescripcionArticulo);
            imgArticulo = view.findViewById(R.id.imgArticulo);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.articulos_rows, null);
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
