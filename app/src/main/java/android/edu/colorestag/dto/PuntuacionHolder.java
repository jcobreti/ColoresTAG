package android.edu.colorestag.dto;

import android.edu.colorestag.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class PuntuacionHolder extends RecyclerView.ViewHolder {

    private static PuntuacionHolder a,b;
    public TextView caja_tiempo_jugador;
    public TextView caja_nombre_jugador;

    public PuntuacionHolder(View itemView) {

        super(itemView);
        caja_nombre_jugador = itemView.findViewById(R.id.nombre_jugador);
        caja_tiempo_jugador = itemView.findViewById(R.id.tiempo_jugador);
    }

    public static void ordenar(String t) {
        comparar(a,b,t);
    }

    public void cargarPuntuacion(Puntacion p) {
        caja_tiempo_jugador.setText(p.getTiempo() + "");
        caja_nombre_jugador.setText(p.getNombre());
    }


    public static int comparar(PuntuacionHolder a, PuntuacionHolder b, String opt)
    {  //opr==t compara por tiempo
       //opt==n compara por nombre
        //cualquier otro valor no compara nada
        String t1 = a.caja_tiempo_jugador.getText().toString();
        String t2 = a.caja_tiempo_jugador.getText().toString();
        String n1 = a.caja_nombre_jugador.getText().toString();
        String n2 = a.caja_nombre_jugador.getText().toString();
        if (opt.equals("t"))
            return t1.compareTo(t2);
        else if (opt.equals("n"))
            return n1.compareTo(n2);
        else return 0;
    }

}