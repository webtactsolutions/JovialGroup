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

import in.co.scorp.jovialgroup.activities.LoginActivity;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 12/04/15.
 */
public class LoginIntentService extends IntentService {

    String data="";
    String str="";
    String params="";
    String action="";

    private URL url;
    private HttpURLConnection connection;
    private DataOutputStream outputStream;
    private BufferedReader reader;

    public LoginIntentService() {
        super("LoginIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        action = intent.getStringExtra(Constants.REGUSER);
        params = "action="+action+"&username="+intent.getStringExtra(Constants.USERNAME)+"&password="+intent.getStringExtra(Constants.PASSWORD)+"&gcm_reg_id="+intent.getStringExtra(Constants.GCM_REG_ID);


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


            Intent response = new Intent(this, LoginActivity.LoginDone.class);
            response.setAction(LoginActivity.LoginDone.ACTION_LOGGED_IN);
            response.addCategory(Intent.CATEGORY_DEFAULT);
            response.putExtra(Constants.RESPONSE,data);

            Log.i("Login response",data);

            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(response);


        }catch (Exception e){
            Log.e("Error on Login Service","Error:"+e.toString());
        }
    }
}
