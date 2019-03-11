package android.edu.colorestag.dto;

import java.io.Serializable;
import java.util.Comparator;

public class Puntacion implements Comparable, Serializable{
//JNG1 implements Comparable
//JNG2 le pongo serializable para poder colarlo de forma serializable (lo guarda en disco y luego lo recupera de un intent a otro.

    private String nombre;
    private long tiempo;
    private String tipoOrdenacion;

    public Puntacion(String nombre, long tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public Puntacion() { }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Puntacion{" +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }

    //JNG2 Metodo de comparacion
    @Override
    public int compareTo(Object o) {
        //tenemos 2 objetos tipo puntucion: this y "o"
        //Tenemos que comparar estos dos objetos
        //this>"o" devuelve numero positivo
        //this<"o" devuelve numero negativo
        //Si son iguales devuelve un 0

        Puntacion p1 = (Puntacion) o;
        if (this.getTiempo() > p1.getTiempo())
            return 1;
        else if (this.getTiempo() < p1.getTiempo())
            return -1;
        return 0;
    }

//JNG2 fin metodo de comparacion
}

