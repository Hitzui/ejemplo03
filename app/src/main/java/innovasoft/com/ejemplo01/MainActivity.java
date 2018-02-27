package innovasoft.com.ejemplo01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.activity.ListaArticulos;
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
            public void onClick(final View view) {
                final boolean result = login(view);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (result) {
                                    Intent intent = new Intent(getApplicationContext(), ListaArticulos.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Usuario o contraseña incorrectas, intente nuevamente",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                };
                thread.start();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private boolean login(final View view) {
        final boolean[] flags = {false};
        new AsyncTask<Void, Void, Void>() {
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
                            Log.i("Usuario encontrado: ", datos[i].getNombre());
                            flags[0] = true;
                        }
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }
                return null;
            }
        };
        return flags[0];
    }

}
