package in.co.scorp.jovialgroup.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.activities.MessagesActivity;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by root on 22/2/15.
 */
public class GCMIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    SharedPreferences preferences;
    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);


    public GCMIntentService() {
        super("GCMIntentService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extraas = intent.getExtras();
        extraas.putString("SEND_ERROR","SendError");
        extraas.putString("DELETED MSG","Message Delete");

        preferences = getApplicationContext().getSharedPreferences(Constants.APP_PREFERENCE_NAME,MODE_PRIVATE);

        String messageType = gcm.getMessageType(intent);

        if(!extraas.isEmpty()) {

            if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                //sendNotification(extraas);
            }
            else if(GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
                //sendNotification(extraas);
            }
            else if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                if(getApplicationContext().getSharedPreferences(Constants.APP_PREFERENCE_NAME,Context.MODE_PRIVATE).getBoolean(Constants.USER_LOGIN_STATUS,false)!=false) {

                    Log.i("MESSAGE CAME", extraas.getString("message"));

                    Intent msgIntent = new Intent(this, MessagesActivity.MessageRecievedBroadcastReceiver.class);
                    msgIntent.setAction(MessagesActivity.MessageRecievedBroadcastReceiver.ACTION_MESSAGE_RECEIVE);
                    msgIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    msgIntent.putExtra("message", extraas.getString("message").toString());

                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(msgIntent);

                    Log.i("after broadcase", extraas.getString("message"));

                    sendNotification();
                }
            }
        }

        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;


    private void sendNotification() {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MessagesActivity.class).putExtra("",""), 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.noti)
                        .setContentTitle("Jovial Group")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("You have received a new message from Jovial Group"))
                        .setContentText("You have received a new message from Jovial Group")
                .setAutoCancel(true);
                ;


        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
