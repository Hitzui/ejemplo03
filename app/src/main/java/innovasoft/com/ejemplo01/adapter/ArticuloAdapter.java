package innovasoft.com.ejemplo01.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.converter.ImageConverterFromUrl;
import innovasoft.com.ejemplo01.models.Articulos;

/**
 * Created by josemiguel on 26/02/2018.
 */

public class ArticuloAdapter extends BaseAdapter{

    private Context context;
    private Articulos[] listaArticulos;
    private LayoutInflater layoutInflater;
    private ImageConverterFromUrl imageConverterFromUrl;
    private String url;

    public ArticuloAdapter(Context context, Articulos[] listaArticulos) {
        this.context = context;
        this.listaArticulos = listaArticulos;
        this.layoutInflater = LayoutInflater.from(context);
        imageConverterFromUrl = new ImageConverterFromUrl();
    }
    @Override
    public int getCount() {
        return listaArticulos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Articulos articulo = listaArticulos[i];
        convertView = layoutInflater.inflate(R.layout.articulos_rows, null);
        TextView lblDescripcion = convertView.findViewById(R.id.lblDescripcionArticulo);
        ImageView imgArticulo = convertView.findViewById(R.id.imgArticulo);
        lblDescripcion.setText(articulo.getDescripcion());
        Log.i("Ruta de la imagen: ",articulo.getRutaimg());
        String url = "http://abrasa.com.ni"+articulo.getRutaimg();
        imageConverterFromUrl.loadImageFromURL(url,imgArticulo);
        return convertView;
    }
}