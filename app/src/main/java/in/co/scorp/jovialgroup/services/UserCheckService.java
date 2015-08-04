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

import in.co.scorp.jovialgroup.activities.RegistrationActivity;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 10/07/15.
 */
public class UserCheckService extends IntentService {

    String data="";
    String str="";
    String action="";

    private URL url;
    private HttpURLConnection connection;
    private DataOutputStream outputStream;
    private BufferedReader reader;


    public UserCheckService() {
        super("UserCheckService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try{

            url = new URL(Constants.BASE_URL);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);


            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(intent.getStringExtra(Constants.PARAMS));
            outputStream.flush();
            outputStream.close();


            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while((str=reader.readLine())!=null){
                data = str;
            }


            Intent response = new Intent(this, RegistrationActivity.UserChecked.class);
            response.setAction(RegistrationActivity.UserChecked.ACTION_USER_CHECKED);
            response.addCategory(Intent.CATEGORY_DEFAULT);
            response.putExtra(Constants.RESPONSE,data);

            Log.i("usercheck Response:", data);

            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(response);


        }catch (Exception e){
            Log.e("Error on usercheck Serv","Error:"+e.toString());
        }
    }
}
