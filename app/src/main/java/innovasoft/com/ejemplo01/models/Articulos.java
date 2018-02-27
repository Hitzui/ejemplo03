package innovasoft.com.ejemplo01.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by josemiguel on 26/02/2018.
 */

public class Articulos {
    /*
        "idarticulo": "00120",
        "descripcion": "FELLOCEL 3 1 DS",
        "caracteristicas": "<p>FELLOCEL 3 1 DS</p>",
        "idsubcategoria": "6",
        "rutaimg": "/images/Veterinario/Mascotas/00120.png",
        "ficha": null
    */
    private String idarticulo;
    private String descripcion;
    private String caracteristicas;
    private int idsubcategoria;
    private String rutaimg;
    private String ficha;


    public String getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(String idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(int idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public String getRutaimg() {
        return rutaimg;
    }

    public void setRutaimg(String rutaimg) {
        this.rutaimg = rutaimg;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

}
