package innovasoft.com.ejemplo01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.web.client.RestTemplate;

import innovasoft.com.ejemplo01.activity.PrincipalActivity;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void login() {
        new AsyncTask<Void, Void, Usuarios>() {
            @Override
            protected Usuarios doInBackground(Void... voids) {
                try {
                    //Handler handler = new Handler(getApplicationContext().getMainLooper());
                    txtEmail = findViewById(R.id.txtEmail);
                    txtPassword = findViewById(R.id.txtPassword);
                    final String url = "http://abrasa.com.ni/api/usuarios";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MyGsonHttpMessageConverter());
                    Usuarios[] datos = restTemplate.getForObject(url, Usuarios[].class);
                    for (int i = 0; i < datos.length; i++) {
                        if (txtEmail.getText().toString().equals(datos[i].getUsuario())) {
                            return datos[i];
                        }
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(final Usuarios usuario) {
                txtPassword = findViewById(R.id.txtPassword);
                if (usuario.getPassword().equals(txtPassword.getText().toString())) {
                    Log.i("Usuario: ", usuario.getNombre());
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Usuario o contraseña incorrectas, intente nuevamente",
                            Toast.LENGTH_LONG).show();
                }
            }

        }.execute();

    }
}