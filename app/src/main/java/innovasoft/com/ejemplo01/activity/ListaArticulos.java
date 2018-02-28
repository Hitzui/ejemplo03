package innovasoft.com.ejemplo01.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.adapter.ArticuloAdapter;
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
    protected  void onStart() {
        super.onStart();
        new HttpRequestAriculos().execute();
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
        if (id == R.id.menuItemRefresh) {
            new HttpRequestAriculos().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class HttpRequestAriculos extends AsyncTask<Void, Void, Articulos[]> {

        @Override
        protected Articulos[] doInBackground(Void... voids) {
            try {
                final String url = "http://abrasa.com.ni/api/articulo";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String result = restTemplate.getForObject(url, String.class);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                listaArticulos = gson.fromJson(result, Articulos[].class);
                return listaArticulos;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                return new Articulos[0];
            }
        }

        @Override
        protected void onPostExecute(Articulos[] datos) {
            listViewArticulos = findViewById(R.id.listViewArticulos);
            ArticuloAdapter articuloAdapter = new ArticuloAdapter(getApplicationContext(), datos);
            listViewArticulos.setAdapter(articuloAdapter);
        }

    }
}
