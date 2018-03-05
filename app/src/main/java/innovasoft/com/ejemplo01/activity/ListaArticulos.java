package innovasoft.com.ejemplo01.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import innovasoft.com.ejemplo01.dialog.DialogArticulos;
import innovasoft.com.ejemplo01.models.Articulos;

public class ListaArticulos extends AppCompatActivity {

    private Articulos[] listaArticulos = null;
    ListView listViewArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new RequestAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.nav_clientes) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class RequestAsyncTask extends AsyncTask<Void, Void,Articulos[]>{

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
        protected void onPostExecute(Articulos[] articulos){
            listaArticulos = articulos;
            listViewArticulos = findViewById(R.id.listViewArticulos);
            ArticuloAdapter articuloAdapter = new ArticuloAdapter(getApplicationContext(), listaArticulos);
            listViewArticulos.setAdapter(articuloAdapter);
            listViewArticulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final Dialog dialog = new Dialog(ListaArticulos.this); // Context, this, etc.
                    Articulos articulo = listaArticulos[i];
                    String url = "http://abrasa.com.ni"+articulo.getRutaimg();
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
