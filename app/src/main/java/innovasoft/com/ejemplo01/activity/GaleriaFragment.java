package innovasoft.com.ejemplo01.activity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.adapter.ArticuloAdapter;
import innovasoft.com.ejemplo01.converter.MyGsonHttpMessageConverter;
import innovasoft.com.ejemplo01.models.Articulos;


public class GaleriaFragment extends Fragment {

    private Articulos[] listaArticulos = null;
    ListView listViewArticulos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galeria, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RequestAsyncTask().execute();
    }

    public class RequestAsyncTask extends AsyncTask<Void, Void, Articulos[]> {

        @Override
        protected Articulos[] doInBackground(Void... voids) {
            try {
                final String url = "http://abrasa.com.ni/api/articulo";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                restTemplate.getMessageConverters().add(new MyGsonHttpMessageConverter());
                return restTemplate.getForObject(url, Articulos[].class);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Articulos[] articulos) {
            listaArticulos = articulos;
            listViewArticulos = getActivity().findViewById(R.id.listViewArticulos);
            ArticuloAdapter articuloAdapter = new ArticuloAdapter(getActivity().getApplicationContext(), listaArticulos);
            listViewArticulos.setAdapter(articuloAdapter);
            listViewArticulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final Dialog dialog = new Dialog(getView().getContext()); // Context, this, etc.
                    Articulos articulo = listaArticulos[i];
                    String url = "http://abrasa.com.ni" + articulo.getRutaimg();
                    dialog.setContentView(R.layout.dialog_articulo);
                    //dialog.setTitle(R.string.aceptar_dialog);
                    ImageView dialogImage = dialog.findViewById(R.id.imgDialogArticulo);
                    TextView lblDescripcion = dialog.findViewById(R.id.lblDialogDescripcion);
                    TextView lblIdArticulo = dialog.findViewById(R.id.lblDialogIdArticulo);
                    Glide.with(view).load(url).into(dialogImage);
                    lblDescripcion.setText(articulo.getDescripcion());
                    lblIdArticulo.setText(articulo.getIdarticulo());
                    dialog.show();
                }
            });
        }
    }
}
