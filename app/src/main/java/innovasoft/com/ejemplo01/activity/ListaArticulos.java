package innovasoft.com.ejemplo01.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.adapter.ArticuloAdapter;
import innovasoft.com.ejemplo01.converter.MyGsonHttpMessageConverter;
import innovasoft.com.ejemplo01.models.Articulos;

public class ListaArticulos extends AppCompatActivity {

    private Articulos[] listaArticulos;
    private ImageView imgArticulo;
    private TextView lblDescripcion;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);
        cargarArticulos();
        ArticuloAdapter articuloAdapter = new ArticuloAdapter(listaArticulos);
        recyclerView = findViewById(R.id.rvArticulos);
        recyclerView.setAdapter(articuloAdapter);
    }

    private void cargarArticulos(){
        try {
            final String url = "http://abrasa.com.ni/api/articulo";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MyGsonHttpMessageConverter());
            String result = restTemplate.getForObject(url, String.class);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            listaArticulos = gson.fromJson(result, Articulos[].class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }
}
