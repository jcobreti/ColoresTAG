package android.edu.colorestag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.edu.colorestag.util.NotificacionMensajeBuenosDiasJava;
import android.util.Log;

public class InicioMovilReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d("JNG", "Se acaba de encender el dispositivo");
        NotificacionMensajeBuenosDiasJava.lanzarNotificiacion(context);

        Intent intent1=new Intent(context, NombreActivity.class);
        context.startActivity(intent1);
    }
}
