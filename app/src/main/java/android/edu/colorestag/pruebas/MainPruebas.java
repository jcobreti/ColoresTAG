package android.edu.colorestag.pruebas;

import android.edu.colorestag.dto.Puntacion;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPruebas {

    public static void main (String [] argumentos)
    { //Ordeno la lista de Puntaciones
        Puntacion puntacion1 = new Puntacion("Vale1", 33);
        Puntacion puntacion2 = new Puntacion("Vale2", 23);
        Puntacion puntacion3 = new Puntacion("Vale3", 38);
        Puntacion puntacion4 = new Puntacion("Vale4", 83);

        List<Puntacion> ls=new ArrayList<>();
        ls.add (puntacion1);
        ls.add (puntacion2);
        ls.add (puntacion3);
        ls.add (puntacion4);

        //Los pinta segun estan
        System.out.println(ls);

        //Los ordeno segfun "Puntacion.compareTo" que he implementado en "Puntacion.java" que "implements Comparable".
         Collections.sort(ls);

        //Ahora lo pinta ordenado de mayor a menos
        System.out.println(ls);

    }
    public static void main0 (String [] argumentos)
    {   //Usando GSON
        Puntacion puntacion = new Puntacion("Vale1", 33);
        System.out.println(puntacion.toString());
        //VAMOS A PASAR DE objeto puntacion A JSON --> SERIALIZAR
        Gson gson = new Gson();
        String str_puntacion = gson.toJson(puntacion);
        System.out.println(str_puntacion);
        //VAMOS A PASAR DE JSON a objeto puntación --> DESERIALIZAR
        Puntacion p2 = gson.fromJson(str_puntacion, Puntacion.class);
        System.out.println("p2 = " + p2.toString());
    }
}
