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

import com.google.android.gms.gcm.GoogleCloudMessaging;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 22/04/15.
 */
public class GCMStautsIntentService extends IntentService {

    SharedPreferences preferences;


    public GCMStautsIntentService() {
        super("GCMStautsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extraas = intent.getExtras();
        extraas.putString("SEND_ERROR","SendError");
        extraas.putString("DELETED MSG","Message Delete");

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if(!extraas.isEmpty()) {

            if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                //sendNotification(extraas);
            }
            else if(GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
                //sendNotification(extraas);
            }
            else if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                if(getApplicationContext().getSharedPreferences(Constants.APP_PREFERENCE_NAME,Context.MODE_PRIVATE).getBoolean(Constants.USER_LOGIN_STATUS,false)!=false){




                }

            }
        }

        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    private int NOTIFICATION_ID = 2;



}
