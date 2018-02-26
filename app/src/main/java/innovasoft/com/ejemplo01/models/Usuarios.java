package innovasoft.com.ejemplo01.models;

import java.util.Date;

/**
 * Created by server on 26/02/2018.
 */

public class Usuarios {
    private String usuario;
    private String password;
    private String fecha;
    private String indicio;
    private String nombre;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIndicio() {
        return indicio;
    }

    public void setIndicio(String indicio) {
        this.indicio = indicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
