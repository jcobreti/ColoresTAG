package android.edu.colorestag;

import android.edu.colorestag.dto.AdapterPuntuaciones;
import android.edu.colorestag.dto.Puntacion;
import android.edu.colorestag.util.Preferencias;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MostrarRecords extends AppCompatActivity {
    private RecyclerView recView;
    private List<Puntacion> datos;
    private AdapterPuntuaciones adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_records);
//Animacion de ventanas
        overridePendingTransition(R.anim.slide_up, R.anim.slide_off);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        datos = Preferencias.cargarPuntuaciones(this);

        if (datos.size()==0)
            {
                Toast.makeText(this,"NO HAY REGISTROS",Toast.LENGTH_SHORT).show();
            }
            else
            {
                adaptador = new AdapterPuntuaciones(datos);
                recView = (RecyclerView) findViewById(R.id.myrecycview);

                //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar
                recView.setAdapter(adaptador);//mostrando la lista
                recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                //recView.setLayoutManager(new GridLayoutManager(this,3));
                //StaggeredGridLayoutManager para celdas de tamaño variable
                //recView.setLayoutManager(new StaggeredGridLayoutManager());
                 //ITEM DECORATOR --> OPCIONAL

                recView.addItemDecoration(
                        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

                 //recView.setItemAnimator(new DefaultItemAnimator());
                //registerForContextMenu(recView);
                 // recView.setContextClickable(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO permitir cambiar de nombre al usuario
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                super.onBackPressed();
                break;
        }
        return true;
    }
    public void ordenarPuntuacion(View view){
        //Toast.makeText(this,"PUNTUACION",Toast.LENGTH_SHORT).show();
        Collections.sort(datos);
        recView.setAdapter(adaptador);
    }

    public void ordenarNombre(View view)
    {   //Ordenado por nombre de menor a mayor
       // Toast.makeText(this,"Por Nombre",Toast.LENGTH_SHORT).show();

        Comparator<Puntacion> comparator=new Comparator<Puntacion>() {
            @Override
            public int compare(@NonNull  Puntacion o1, @NonNull Puntacion o2) {
                String t1 = o1.getNombre();
                String t2 = o2.getNombre();

                if (t1.compareTo(t2) >= 0) return 1;
                else if (t1.compareTo(t2) < 0) return -1;
                else return 0;
            }
        };
        Collections.sort(datos, comparator);
        recView.setAdapter(adaptador);

       // recView.setAdapter(adaptador);
    }

/*
 * *************************************************************************************
    public void ordenarPuntuacionJNG()
    {   //Ordenado por puntuacion de menor a mayor
        Toast.makeText(this,"Puntuacion",Toast.LENGTH_SHORT).show();
        Collections.sort(datos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Puntacion p1=(Puntacion)o1;
                Puntacion p2=(Puntacion)o2;
                long t1=p1.getTiempo();
                long t2=p2.getTiempo();

                if (t1>=t2) return 1;
                else if (t1<t2) return -1;
                else return 0;
            }
        });
    }
 */
}

