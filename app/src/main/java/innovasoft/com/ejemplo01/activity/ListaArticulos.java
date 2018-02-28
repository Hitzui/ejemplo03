package innovasoft.com.ejemplo01.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.R;
import innovasoft.com.ejemplo01.adapter.ArticuloAdapter;
import innovasoft.com.ejemplo01.converter.MyGsonHttpMessageConverter;
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cargarArticulos();
            }
        });
        listViewArticulos = findViewById(R.id.listViewArticulos);
        ArticuloAdapter articuloAdapter = new ArticuloAdapter(getApplicationContext(), listaArticulos);
        listViewArticulos.setAdapter(articuloAdapter);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarArticulos() {
        try {
            if(android.os.Build.VERSION.SDK_INT>9)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            final String url = "http://abrasa.com.ni/api/articulo";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MyGsonHttpMessageConverter());
            listaArticulos = restTemplate.getForObject(url, Articulos[].class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }


}
