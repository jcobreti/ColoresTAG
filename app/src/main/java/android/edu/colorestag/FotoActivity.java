package android.edu.colorestag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.edu.colorestag.util.NotificacionMensajeBuenosDiasJava;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class FotoActivity extends AppCompatActivity {

    private static final String SRC_CONTROL="SRC_IMAGEN";
    private static final String SRC_DEFAULT="@drawable/descarga";
    private boolean permanente=false;
    ImageView foto_circular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

//así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//Lanzo las notificaciones
        NotificacionMensajeBuenosDiasJava.lanzarNotificiacion(getApplicationContext());

//Menu contextual
        foto_circular=findViewById(R.id.imageView);

//Quiero un menu contextual en foto_circular
        registerForContextMenu(foto_circular);



//FOTO PERMANENTE
/*
       String uriTxt=getSrc();
       Uri uri=Uri.parse(uriTxt);

       if (uriTxt.compareTo("")!=0) {
           try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
               foto_circular.setImageBitmap(bitmap);
           }
           catch (IOException e) {
               e.printStackTrace();

           }
       }
*/
//FIN FOTO PERMANENTE
    }

    private void guardarSrc(String ruta){
        SharedPreferences sp = getApplicationContext().getSharedPreferences(SRC_CONTROL, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        ed.putString ("SRC",ruta);

        ed.commit();
    }
    private String getSrc ()
        {String uri;
         SharedPreferences sp = getApplicationContext().getSharedPreferences(SRC_CONTROL, Context.MODE_PRIVATE);
         uri=sp.getString("SRC","");
         return uri;
     }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//Aqui inflo el menu contextual
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle("Seleccione una opción");
        inflater.inflate(R.menu.menu_contextual, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//Este es para cuando pincha en una opcion

        switch (item.getItemId()) {
            case R.id.eliminarFoto:
                Log.d("JNG", "Tocó En eliminar Foto: "+item.getTitle()+" -->Orden: "+item.getOrder());
                permanente=true;
                //Intent i = new Intent(this, NombreActivity.class);
                //i.putExtra("DEVUELTA", true);
                //startActivityForResult(i, 305);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void tomarFoto (View v)
    {
        Log.d("MIAPP", "QUIERO TOMAR UNA FOTO");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 500);
        }
    }

    public void seleccionarFoto (View v)
    {
        Log.d("MIAPP", "QUIERO TOMAR UNA FOTO");
        Intent intentpidefoto = new Intent ();
        intentpidefoto.setAction(Intent.ACTION_PICK);
        intentpidefoto.setType("image/*");//TIPO MIME

        startActivityForResult(intentpidefoto, 30);


    }

    private void setearImagenCam (int resultCode, Intent data)
    {
        switch (resultCode)
        {
            case RESULT_OK:Log.d("MIAPP", "Tiró la foto bien");
                try {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    ImageView im = (ImageView) findViewById(R.id.imageView);
                    im.setImageBitmap(thumbnail);
                }catch (Throwable t)
                {
                    Log.e("MIAPP", "ERROR AL SETEAR LA FOTO", t);
                }
                break;

            case RESULT_CANCELED:Log.d("MIAPP", "Canceló la foto");
                break;

        }
    }

    private void setearImagenDeArchivo (int resultCode, Intent data)
    {
        switch (resultCode)
        {
            case RESULT_OK:Log.d("MIAPP", "Seleccionó foto ok");
                Uri uri = data.getData();
                Log.d("MIAPP", "URI = " +data.getData().toString());
                try {

                    Bitmap  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ImageView imageView = (ImageView)findViewById(R.id.imageView);
                    if (!permanente)
                        imageView.setImageBitmap(bitmap);
                    else
                        Toast.makeText(this,"IMAGEN MARCADA COMO PERMANENTE",Toast.LENGTH_SHORT).show();

                    guardarSrc(data.getData().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case RESULT_CANCELED:Log.d("MIAPP", "Canceló la foto");
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==500)//viene de mi petición de tirar mi foto
        {
           setearImagenCam(resultCode, data);
        } else if (requestCode==30)
        {
            setearImagenDeArchivo(resultCode, data);
        }
    }
    /**
     * Recibimos el evento sobre una opcion del menú superior
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO permitir cambiar de nombre al usuario
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog alerta;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("ALERTA...");
        builder.setCancelable(false);
        builder.setMessage("¿Seguro que deseas salir?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FotoActivity.super.onBackPressed();
                }
            });
        builder.setNegativeButton("NO", null);
        alerta=builder.create();
        alerta.show();

    }


}
