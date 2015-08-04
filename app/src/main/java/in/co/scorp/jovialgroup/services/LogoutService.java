package in.co.scorp.jovialgroup.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.util.Constants;

public class LogoutService extends IntentService {

    String data="";
    String str="";
    String params="";
    String action="";

    private URL url;
    private HttpURLConnection connection;
    private DataOutputStream outputStream;
    private BufferedReader reader;

    public LogoutService() {
        super("LogoutService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        action = intent.getStringExtra(Constants.ACTION);
        params = "action="+action+"&username="+intent.getStringExtra(Constants.USERNAME);


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


            Intent response = new Intent(this, MainActivity.LogoutReciever.class);
            response.setAction(MainActivity.LogoutReciever.ACTION_LOGOUT);
            response.addCategory(Intent.CATEGORY_DEFAULT);
            response.putExtra(Constants.RESPONSE,data);

            Log.i("Logout Response:", data);

            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(response);


        }catch (Exception e){
            Log.e("Error on Login Service","Error:"+e.toString());
        }
    }
}
