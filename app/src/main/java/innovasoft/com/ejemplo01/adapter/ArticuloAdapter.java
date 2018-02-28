package innovasoft.com.ejemplo01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.converter.RequestDataArticulos;
import innovasoft.com.ejemplo01.models.Articulos;

/**
 * Created by josemiguel on 26/02/2018.
 */

public class ArticuloAdapter extends BaseAdapter {

    private Context context;
    private Articulos[] datos;
    LayoutInflater inflater;

    public ArticuloAdapter(Context context, Articulos[] datos) {
        this.context = context;
        this.datos = datos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            RequestDataArticulos requestDataArticulos = new RequestDataArticulos();
            view = inflater.inflate(R.layout.articulos_rows,null);
            TextView lblDescripcion = view.findViewById(R.id.lblDescripcionArticulo);
            ImageView imageView = view.findViewById(R.id.imgArticulo);
            lblDescripcion.setText(datos[i].getDescripcion());
            String url = "http://abrasa.com.ni"+datos[i].getRutaimg();
            Glide.with(view).load(url).into(imageView);
            //imageView.setImageBitmap(requestDataArticulos.loadImageFromURL(url));
            return view;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}