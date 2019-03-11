package android.edu.colorestag;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Record extends AppCompatActivity {
    private AnimationDrawable ad;
    private ImageView img;
    private TextView textoPuntuacion2;
    long puntuacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
//ponemos la barra de estado
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//Cargamos el texto con el record
        puntuacion=getIntent().getExtras().getLong("Puntuacion");
        textoPuntuacion2=findViewById(R.id.textoPuntuacion);
        textoPuntuacion2.setText(""+puntuacion);
//Empezamos con la animación
    //1 obtengo la imagen
        img= findViewById(R.id.imagen_animacion);
    //2 cargo la animacion
        img.setBackgroundResource(R.drawable.introduccion);//Esto es un XML que tiene las imagenes y la duracion
        ad = (AnimationDrawable) img.getBackground();
        img.setVisibility(View.VISIBLE);
        ad.start();

    }
    public void parar(View view) {
        //Se llama cuando hacemos click en la imagen de la animación.
        //img.setVisibility(View.INVISIBLE);
        ad.stop();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO permitir cambiar de nombre al usuario
        switch (item.getItemId())
        { case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                super.onBackPressed();
                break;
        }
        return true;
    }
}
