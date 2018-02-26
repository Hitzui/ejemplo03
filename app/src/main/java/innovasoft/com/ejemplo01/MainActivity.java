package innovasoft.com.ejemplo01;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import innovasoft.com.ejemplo01.converter.MyGsonHttpMessageConverter;
import innovasoft.com.ejemplo01.models.Usuarios;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void login(final View view) {

        new AsyncTask<Void, Void, Void>() {
            boolean flags = false;
            ProgressDialog progressDialog;
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Handler handler = new Handler(getApplicationContext().getMainLooper());
                    txtEmail = findViewById(R.id.txtEmail);
                    txtPassword = findViewById(R.id.txtPassword);
                    final String url = "http://abrasa.com.ni/api/usuarios";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MyGsonHttpMessageConverter());
                    Usuarios[] datos = restTemplate.getForObject(url, Usuarios[].class);
                    for (int i = 0; i < datos.length; i++) {
                        if (txtEmail.getText().toString().equals(datos[i].getUsuario()) && txtPassword.getText().toString().equals(datos[i].getPassword())) {
                            flags = true;
                           // progressDialog.set
                        }
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }
                return null;
            }
        };
    }

}
