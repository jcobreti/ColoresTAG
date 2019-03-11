package android.edu.colorestag.dto;

import android.content.Context;
import android.content.Intent;
import android.edu.colorestag.DetalleRecord;
import android.edu.colorestag.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class AdapterPuntuaciones extends RecyclerView.Adapter<PuntuacionHolder> implements View.OnClickListener{
//Hemos tenido que incluir laq interfaz: OnClickListener y como es abstracto tengo que sobreescribir los metodos, en este caso "onClick"
    private List<Puntacion> datos;

    @Override
    public PuntuacionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PuntuacionHolder puntuacionHolder = null;

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.fila_record, parent, false);
            itemView.setOnClickListener(this);//asociaría el listener para usar esto hay que poner "implements View.OnClickListener" this es la fila pulsada.
            puntuacionHolder = new PuntuacionHolder(itemView);

        return puntuacionHolder;
    }

    //Al holder, le meto la info de item que toca
    @Override
    public void onBindViewHolder(PuntuacionHolder holder, int position) {

        Puntacion puntacion = datos.get(position);
        holder.cargarPuntuacion(puntacion);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public AdapterPuntuaciones (List<Puntacion> lista_puntuaciones)
    {
        this.datos = lista_puntuaciones;
    }

    @Override
    public void onClick(View v) {
        //Aqui v es la fila que toca
        //Si voy a fila_record veo que tiene dos textViews
        Log.d ("JNG", "pulsado fila");
        //Creamos un Intent explicito, es decir, de aqui me voy a alla
        //Saco la Puntacion
        //Y construyo el intent para pasarselo y mostrar en esa activity el detalle.
        TextView nombre = v.findViewById(R.id.nombre_jugador);
        TextView tiempo = v.findViewById(R.id.tiempo_jugador);

       String nombreTxt=nombre.getText().toString();
       String tiempoTxt=tiempo.getText().toString();
       long tiempoFinal=Long.parseLong(tiempoTxt);

//Este es serializable
       //Puntacion pt=new Puntacion(nombreTxt,tiempoFinal);
//Este es el Parcelable
       PuntuacionParcelable pt=new PuntuacionParcelable(nombreTxt,tiempoFinal);

       Context v2=v.getContext();
       Intent intentdetalle = new Intent(v2, DetalleRecord.class);
       //Serializable -->del objeto a bytes
       // si consigo que putacion sea serializable lo puedo meter por ahi
       intentdetalle.putExtra("Puntacion",pt);//Como pt ya es Serializable no da guerra))
       v2.startActivity(intentdetalle);

    }
}
