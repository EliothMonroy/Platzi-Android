package com.example.notificaciones2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIF_ALERTA_ID=1;
    NotificationChannel notificationChannel;
    NotificationManager notificationManager;
    private final String CHANNEL_ID="some_channel_id";
    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getText(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //

        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        crearCanal();
        prepararNotificacion();

    }

    public void prepararNotificacion(){

        Intent intent=new Intent(this,NotificationActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ID",NOTIF_ALERTA_ID);
        intent.putExtras(bundle);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        builder=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setLargeIcon(getBitmap(R.mipmap.ic_launcher))
                .setTicker("Nueva entrada en ESCOM")
                .setContentTitle("Notificaciones").setContentText("Curso de Android")
                .setContentIntent(pendingIntent);

    }

    public void notificar(View view){
        notificationManager.notify(NOTIF_ALERTA_ID,builder.build());
    }

    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void crearCanal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Some Channel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            notificationChannel = new NotificationChannel(CHANNEL_ID, channelName, importance);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }


    }

}
