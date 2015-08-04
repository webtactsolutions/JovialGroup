package in.co.scorp.jovialgroup.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import in.co.scorp.jovialgroup.activities.MessagesActivity;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 14/04/15.
 */
public class GetMessagesService extends IntentService {

    String data="";
    String str="";
    String params="";
    String action="";

    private URL url;
    private HttpURLConnection connection;
    private DataOutputStream outputStream;
    private BufferedReader reader;

    public GetMessagesService() {
        super("GetMessagesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        action = intent.getStringExtra(Constants.ACTION);
        params = "action=msgboard&usrid="+intent.getStringExtra(Constants.USERID);

        try{

            url = new URL(Constants.BASE_URL);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(params);
            outputStream.flush();
            outputStream.close();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((str=reader.readLine())!=null){
                data = str;
            }

            Log.i("messesa",data);

            Intent msgResponse = new Intent(getApplicationContext(), MessagesActivity.MsgsRecievedBroadCast.class);
            msgResponse.addCategory(Intent.CATEGORY_DEFAULT);
            msgResponse.putExtra(Constants.RESPONSE,data);
            msgResponse.setAction(MessagesActivity.MsgsRecievedBroadCast.ACTION_MESSAGE_RECEIVED);

            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(msgResponse);



        }catch (Exception e){

        }
    }
}
