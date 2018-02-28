package innovasoft.com.ejemplo01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import innovasoft.com.ejemplo01.R;
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
            view = inflater.inflate(R.layout.articulos_rows,null);
            TextView lblDescripcion = view.findViewById(R.id.lblDescripcionArticulo);
            ImageView imageView = view.findViewById(R.id.imgArticulo);
            return view;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
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