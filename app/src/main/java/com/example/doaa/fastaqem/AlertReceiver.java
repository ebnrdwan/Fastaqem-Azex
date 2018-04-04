package com.example.doaa.fastaqem;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertReceiver extends BroadcastReceiver {
    Context context ;
  //   ZakrnyDBHelper ae = new ZakrnyDBHelper(context) ;
//    Main5_1Activity mm = new Main5_1Activity();
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        PendingIntent notification = PendingIntent.getActivity(context, 0, new Intent(context, Main5Activity.class), 0);
       // Bitmap btm = BitmapFactory.decodeResource(this.getResources(),R.drawable.b)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.e)//image
                //.setLargeIcon(btm)
               .setContentTitle("ذكرني")
             .setContentText("معلش لو سمحت ممكن تروح تشوف انت بنفسك اخر حاجه عايزنا نذكرك بيها! اصل احنا عندنا مشكله ف الكود معلش");
           //dont forget large item

        builder.setContentIntent(notification);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        NotificationManager mm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mm.cancel(1);
        mm.notify(1, builder.build());
    }
}
