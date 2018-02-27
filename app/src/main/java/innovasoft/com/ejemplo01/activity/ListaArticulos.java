package innovasoft.com.ejemplo01.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.adapter.ArticuloAdapter;
import innovasoft.com.ejemplo01.converter.MyGsonHttpMessageConverter;
import innovasoft.com.ejemplo01.models.Articulos;

public class ListaArticulos extends AppCompatActivity {

    private Articulos[] listaArticulos;
    private RecyclerView recyclerView;
    ArticuloAdapter articuloAdapter = new ArticuloAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);
        cargarArticulos();
        recyclerView = findViewById(R.id.rvArticulos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(articuloAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void cargarArticulos(){
        new AsyncTask<Void, Void, Articulos[]>(){
            @Override
            protected Articulos[] doInBackground(Void... voids) {
                try {
                    final String url = "http://abrasa.com.ni/api/articulo";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                    restTemplate.getMessageConverters().add(new MyGsonHttpMessageConverter());
                    listaArticulos = restTemplate.getForObject(url,Articulos[].class);
                    for (int i =0; i<listaArticulos.length;i++){
                        Log.i("Articulo " + listaArticulos[i].getIdarticulo(),"Descripcion: " + listaArticulos[i].getDescripcion());
                    }
                    return listaArticulos;
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                    return new Articulos[0];
                }
            }
            @Override
            protected void onPostExecute(Articulos[] arrayArticulos){
                articuloAdapter.setDatos(arrayArticulos);
            }
        }.execute();

    }
}
