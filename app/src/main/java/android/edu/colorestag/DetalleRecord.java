package android.edu.colorestag;


import android.edu.colorestag.dto.PuntuacionParcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DetalleRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_record);
//Serializable
       // Puntacion p= (Puntacion) getIntent().getExtras().get("Puntacion");
//Parcelable
        PuntuacionParcelable p= (PuntuacionParcelable) getIntent().getExtras().get("Puntacion");

        String nombre=p.getNombre();
        long tiempo=p.getTiempo();
        TextView tv4=findViewById(R.id.tv4);
        TextView tv5=findViewById(R.id.tv5);
        String tiempoTxt=Float.toString(tiempo);
        tv4.setText(nombre);
        tv5.setText(tiempoTxt);

    }
}
