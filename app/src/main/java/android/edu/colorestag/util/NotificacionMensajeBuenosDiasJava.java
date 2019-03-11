package android.edu.colorestag.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.edu.colorestag.DetalleRecord;
import android.edu.colorestag.R;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificacionMensajeBuenosDiasJava {
    //El id
    public static final String NOTIFICATION_CHANNEL_ID = "channel_id";

    //El nombre visible para el usuario en Ajustes
    public static final String CHANNEL_NAME = "Notification Channel";

    private static NotificationChannel crearCanalNotificacion() {
        NotificationChannel notificationChannel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);

            //La duración del patrón de vibración {silencio,vibración,silencio,vibración,..}
            notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500, 500});

            //Indicamos si la notificación será visible estando la pantalla bloqueada o no
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        return notificationChannel;
    }

    public static void lanzarNotificiacion(Context context) {

        Log.d("MENSAJE", "Este es el que Lanzan la notificacion");
        NotificationCompat.Builder nb = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //Con esto crea un apartado en ajustes para configurarlo
            NotificationChannel nc = crearCanalNotificacion();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(nc);
            nb = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        } else {

            nb = new NotificationCompat.Builder(context, null);

        }
        nb.setSmallIcon(R.mipmap.ic_launcher_round);

        Drawable drawable= ContextCompat.getDrawable(context,R.drawable.rivas_1);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        nb.setLargeIcon(bitmap);

        nb.setContentTitle("BUENOS DÍAS");
        nb.setContentText("Despiertate de una vez");
        nb.setAutoCancel(true);
        nb.setColor(Color.GREEN);
        nb.setColorized(true);
        nb.setSubText("................................");
        nb.setDefaults(Notification.DEFAULT_ALL);

//Esto es donde vamos air cuando toquemos la notificacion
        Intent resultIntent = new Intent(context, DetalleRecord.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), resultIntent, PendingIntent.FLAG_ONE_SHOT);
        nb.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(537, nb.build());//537
    }
}
