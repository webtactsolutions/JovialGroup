package in.co.scorp.jovialgroup.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.LoginWithUser;
import in.co.scorp.jovialgroup.services.LoginIntentService;
import in.co.scorp.jovialgroup.util.ConnectionDetector;
import in.co.scorp.jovialgroup.util.Constants;
import in.co.scorp.jovialgroup.util.LoginServiceParser;

public class LoginActivity extends ActionBarActivity {

    SharedPreferences preferences;

    Typeface typefaceTitle;
    Typeface typefaceNormal;

    TextView tvLoginTitle;

    EditText etUsername;
    EditText etPassword;

    Button btnLogin;

    Intent bundle;
    public ProgressDialog dialog;

    LoginServiceParser parser;
    ArrayList<LoginWithUser> list = new ArrayList<LoginWithUser>();
    private Toolbar toolbar;

    String from="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        IntentFilter filter = new IntentFilter(LoginDone.ACTION_LOGGED_IN);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(new LoginDone(), filter);

        from = getIntent().getExtras().getString(Constants.CALLED_FROM);

        LinearLayout loginContainer = (LinearLayout) findViewById(R.id.login_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(loginContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });


        preferences = this.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);


        typefaceTitle = Typeface.createFromAsset(this.getAssets(), "oswald_regular.ttf");
        typefaceNormal = Typeface.createFromAsset(this.getAssets(),"normal.ttf");

        tvLoginTitle = (TextView)findViewById(R.id.tvLoginTitle);
        tvLoginTitle.setTypeface(typefaceTitle);

        etUsername = (EditText)findViewById(R.id.et_username);
        etPassword = (EditText)findViewById(R.id.et_password);

        preferences.edit().putInt(Constants.ROLE, 0).commit();

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                if(etUsername.length()<1 || etPassword.length()<1) {

                    Toast.makeText(LoginActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();

                }else {

                    if(ConnectionDetector.isNetworkAvailable(LoginActivity.this)==false){

                        Toast.makeText(LoginActivity.this,"Network not Available",Toast.LENGTH_SHORT).show();
                    }
                    else {


                        Intent intent = new Intent(LoginActivity.this, LoginIntentService.class);
                        intent.putExtra(Constants.REGUSER, "reguser");
                        intent.putExtra(Constants.USERNAME, etUsername.getText().toString());
                        intent.putExtra(Constants.PASSWORD, etPassword.getText().toString());
                        intent.putExtra(Constants.GCM_REG_ID,preferences.getString(Constants.GCM_REG_ID,""));

                        LoginActivity.this.startService(intent);


                    }

                }

            }
        });

    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(new LoginDone());
        finish();
    }

    public class LoginDone extends BroadcastReceiver {

        public static final String ACTION_LOGGED_IN = "in.co.scorp.jovial.logged_in";

        @Override
        public void onReceive(Context context, Intent intent) {

            //dialog.dismiss();


            parser = new LoginServiceParser();

//            dialog.dismiss();

            Log.i("response login", "" + parser.getResponseCode(intent.getStringExtra(Constants.RESPONSE)));

            if(parser.getResponseCode(intent.getStringExtra(Constants.RESPONSE))==0) {

                Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            }
            else {

                list = parser.parseJson(intent.getStringExtra(Constants.RESPONSE));



                String s = list.get(0).getServices().toString();

                String[] blockNo = list.get(0).getServices().toString().split(",");


                preferences.edit().putString(Constants.SERVICES_LIST, s.substring(0, s.length() - 1)).commit();

                Log.i("services in fragmen", preferences.getString(Constants.SERVICES_LIST, ""));
                preferences.edit().putBoolean(Constants.USER_LOGIN_STATUS, true).commit();
                preferences.edit().putString(Constants.USER_FULL_NAME, list.get(0).getName()).commit();
                preferences.edit().putString(Constants.USERNAME, etUsername.getText().toString()).commit();
                preferences.edit().putString(Constants.USERID, String.valueOf(list.get(0).getUserId())).commit();
                preferences.edit().putString(Constants.BLOCK_NO,list.get(0).getBlockNo()).commit();
                preferences.edit().putString(Constants.MOJE,list.get(0).getMoje()).commit();
                preferences.edit().putInt(Constants.UROLE, Integer.parseInt(String.valueOf(list.get(0).getRole()))).commit();
                preferences.edit().putString(Constants.NAME, list.get(0).getName()).commit();
                preferences.edit().putString(Constants.PHONE, list.get(0).getMobile()).commit();
                preferences.edit().putString(Constants.DOB, list.get(0).getDob()).commit();
                preferences.edit().putString(Constants.EMAIL, list.get(0).getEmail()).commit();
                preferences.edit().putLong(Constants.ORDER_AMNT, list.get(0).getOrderAmnt()).commit();


                if (from.equals("tracker")) {


                } else if (from.equals("login")) {

                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);

                } else if (from.equals("msg")) {

                    Intent intentMsg = new Intent(LoginActivity.this, MessagesActivity.class);
                    intentMsg.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentMsg);

                }

            }

        }
    }



    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
