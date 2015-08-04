package in.co.scorp.jovialgroup.services;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import in.co.scorp.jovialgroup.util.Constants;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

    	Log.i("msg came", "msg came");

        if(intent.getStringExtra(Constants.SENDER_ID).equals("message")) {
            ComponentName name = new ComponentName(context.getPackageName(), GCMIntentService.class.getName());
            startWakefulService(context, (intent.setComponent(name)));
            setResultCode(Activity.RESULT_OK);
        }
        else {

            Log.i("Tracker Status","Update");
            ComponentName name = new ComponentName(context.getPackageName(), GCMStautsIntentService.class.getName());
            startWakefulService(context, (intent.setComponent(name)));
            setResultCode(Activity.RESULT_OK);
        }
    }
}
