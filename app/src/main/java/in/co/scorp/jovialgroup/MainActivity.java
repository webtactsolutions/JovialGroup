package in.co.scorp.jovialgroup;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import in.co.scorp.jovialgroup.activities.AboutActivity;
import in.co.scorp.jovialgroup.activities.ClientActivity;
import in.co.scorp.jovialgroup.activities.ContactDetailsActivity;
import in.co.scorp.jovialgroup.activities.LoginActivity;
import in.co.scorp.jovialgroup.activities.MessagesActivity;
import in.co.scorp.jovialgroup.activities.ProjectsActivity;
import in.co.scorp.jovialgroup.activities.RegistrationActivity;
import in.co.scorp.jovialgroup.activities.ServicesActivity;
import in.co.scorp.jovialgroup.activities.WhoWeAreActivity;
import in.co.scorp.jovialgroup.fragments.HomeFragmnent;
import in.co.scorp.jovialgroup.services.LogoutService;
import in.co.scorp.jovialgroup.util.ConnectionDetector;
import in.co.scorp.jovialgroup.util.Constants;


public class MainActivity extends ActionBarActivity {

    private String loginLogout = "Login";
    private Toolbar toolbar;
    private RelativeLayout mainContainer;
    private AccountHeader.Result accountHeader;
    Drawer.Result result;

    TextView tvToolbarTitle;

    Typeface typefaceTitle;
    Typeface typefaceNormal;


    private static final String APP_VERSION = "appVersion";
    private static final String TAG = "gcm registration";

    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();

    SharedPreferences logoutPreference;

    String regId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated

        Log.i("display", "height:" + height + " width:" + width);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        typefaceTitle = Typeface.createFromAsset(this.getAssets(), "oswald_regular.ttf");
        typefaceNormal = Typeface.createFromAsset(this.getAssets(), "normal.ttf");

        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);


        mainContainer = (RelativeLayout) findViewById(R.id.main_container);

        setSupportActionBar(toolbar);

        IntentFilter intentFilter = new IntentFilter(LogoutReciever.ACTION_LOGOUT);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(new LogoutReciever(), intentFilter);

        YoYo.with(Techniques.RollIn).duration(500).playOn(mainContainer);

        Fragment fragment = new HomeFragmnent();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();


        if (checkPlayServices()) {

            gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            regId = getRegistrationId(this);

            if (regId.isEmpty()) {
                Log.i("Registration", "registration not found");
                registerInBackground();
            }

        } else {
            Log.i(TAG, "App is not supported");
        }

        final SharedPreferences appPreferences = this.getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);

        if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == true) {

            loginLogout = "Logout";
        }

        if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == true) {

            AccountHeader.Result headerResult = new AccountHeader()
                    .withActivity(this)
                    .withNameTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
                    .withCompactStyle(true)
                    .withHeightDp(150)
                    .withHeaderBackground(R.drawable.header_bg_logged_in)
                    .withTextColor(Color.parseColor("#444444"))
                    .withProfileImagesVisible(false)
                    .withTranslucentStatusBar(true)
                    .withSelectionListEnabledForSingleProfile(false)
                    .addProfiles(
                            new ProfileDrawerItem().withName(appPreferences.getString(Constants.USER_FULL_NAME, "")).withEmail(appPreferences.getString(Constants.USERNAME, "")).withTextColor(Color.parseColor("#444444")).withTypeface(typefaceTitle)
                    )
                    .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                        @Override
                        public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                            return false;
                        }
                    })
                    .build();

            result = new Drawer()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .withSelectedItem(0)
                    .withHeader(R.layout.header)
                    .withAccountHeader(headerResult)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.meny_home)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("About Jovial Group").withIcon(getResources().getDrawable(R.drawable.menu_about_us)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Who We Are?").withIcon(getResources().getDrawable(R.drawable.who_we_are_48)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Services").withIcon(getResources().getDrawable(R.drawable.menu_services)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Projects").withIcon(getResources().getDrawable(R.drawable.menu_projects)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Clients").withIcon(getResources().getDrawable(R.drawable.menu_clients)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Terms Of Use").withIcon(getResources().getDrawable(R.drawable.termsofuse_48)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Contact Details").withIcon(getResources().getDrawable(R.drawable.menu_contact_details)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName("Message Board").withIcon(getResources().getDrawable(R.drawable.menu_msgboard)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Tracker").withIcon(getResources().getDrawable(R.drawable.menu_tracker)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName(loginLogout).withIcon(getResources().getDrawable(R.drawable.login_logout)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)))
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                                    //Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();

                                    if (i == 0) {

                                        YoYo.with(Techniques.RollIn).duration(500).playOn(mainContainer);

                                        Fragment fragment = new HomeFragmnent();
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();

                                    }

                                    if (i == 1) {

                                        Intent aboutActivity = new Intent(getApplicationContext(), AboutActivity.class);
                                        aboutActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        aboutActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        aboutActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        MainActivity.this.startActivity(aboutActivity);
                                    }
                                    if (i == 2) {

                                        Intent whoWeAreActivity = new Intent(getApplicationContext(), WhoWeAreActivity.class);
                                        whoWeAreActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        whoWeAreActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        whoWeAreActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        MainActivity.this.startActivity(whoWeAreActivity);
                                    }
                                    if (i == 3) {

                                        Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    } else if (i == 4) {

                                        Intent intent = new Intent(MainActivity.this, ProjectsActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    } else if (i == 5) {

                                        Intent intent = new Intent(MainActivity.this, ClientActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    } else if (i == 7) {

                                        Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);


                                    } else if (i == 9) {

                                        if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == false) {

                                            if (appPreferences.getString(Constants.GCM_REG_ID, "").equals("") || appPreferences.getString(Constants.GCM_REG_ID, "") == null) {


                                                registerInBackground();
                                            }

                                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                            intent.putExtra(Constants.CALLED_FROM, "msg");
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.putExtra(Constants.GCM_REG_ID, appPreferences.getString(Constants.GCM_REG_ID, ""));
                                            startActivity(intent);


                                        } else {

                                            if (ConnectionDetector.isNetworkAvailable(MainActivity.this) == false) {

                                                Toast.makeText(MainActivity.this, "Network not Available", Toast.LENGTH_SHORT).show();
                                            } else {

//                                        YoYo.with(Techniques.RollIn).duration(500).playOn(mainContainer);
                                                Intent intent = new Intent(MainActivity.this, MessagesActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
//                                        Fragment contactFragment = new MessageBoardFragment();
//                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, contactFragment).addToBackStack("").commit();
                                            }
                                        }


                                    } else if (i == 10) {

//                            Toast.makeText(getApplicationContext(), "8 tracker", Toast.LENGTH_SHORT).show();

                                        if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == false) {


                                            if (appPreferences.getString(Constants.GCM_REG_ID, "").equals("") || appPreferences.getString(Constants.GCM_REG_ID, "") == null) {


                                                registerInBackground();
                                            }

                                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.putExtra(Constants.CALLED_FROM, "tracker");
                                            intent.putExtra(Constants.GCM_REG_ID, appPreferences.getString(Constants.GCM_REG_ID, ""));
                                            startActivity(intent);
                                        } else {

                                            if (ConnectionDetector.isNetworkAvailable(MainActivity.this) == false) {

                                                Toast.makeText(MainActivity.this, "Network not Available", Toast.LENGTH_SHORT).show();
                                            } else {


                                            }
                                        }

                                    } else if (i == 12) {

                                        if (ConnectionDetector.isNetworkAvailable(MainActivity.this) == false) {

                                            Toast.makeText(MainActivity.this, "Network not Available", Toast.LENGTH_SHORT).show();

                                        } else {

                                            if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == true) {


                                                Intent intent = new Intent(MainActivity.this, LogoutService.class);
                                                intent.setAction(LogoutReciever.ACTION_LOGOUT);
                                                intent.putExtra(Constants.USERNAME, appPreferences.getString(Constants.USERNAME, ""));
                                                intent.putExtra(Constants.ACTION, "logout");
                                                MainActivity.this.startService(intent);

                                            } else {

                                                if (appPreferences.getString(Constants.GCM_REG_ID, "").equals("") || appPreferences.getString(Constants.GCM_REG_ID, "") == null) {


                                                    registerInBackground();
                                                }

                                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                intent.putExtra(Constants.GCM_REG_ID, appPreferences.getString(Constants.GCM_REG_ID, ""));
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }
                            })
                            .build();
        } else {


            result = new Drawer()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .withSelectedItem(0)
                    .withActionBarDrawerToggleAnimated(true)
                    .withHeader(R.layout.header)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.meny_home)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("About Jovial Group").withIcon(getResources().getDrawable(R.drawable.menu_about_us)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Who We Are?").withIcon(getResources().getDrawable(R.drawable.who_we_are_48)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Services").withIcon(getResources().getDrawable(R.drawable.menu_services)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Projects").withIcon(getResources().getDrawable(R.drawable.menu_projects)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Clients").withIcon(getResources().getDrawable(R.drawable.menu_clients)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Terms Of Use").withIcon(getResources().getDrawable(R.drawable.termsofuse_48)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Contact Details").withIcon(getResources().getDrawable(R.drawable.menu_contact_details)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName("Message Board").withIcon(getResources().getDrawable(R.drawable.menu_msgboard)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Tracker").withIcon(getResources().getDrawable(R.drawable.menu_tracker)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName(loginLogout).withIcon(getResources().getDrawable(R.drawable.login_logout)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL)),
                            new PrimaryDrawerItem().withName("Registration").withIcon(getResources().getDrawable(R.drawable.menu_tracker)).withTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                            //Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();

                            if (i == 0) {

                                YoYo.with(Techniques.RollIn).duration(500).playOn(mainContainer);

                                Fragment fragment = new HomeFragmnent();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();

                            }

                            if (i == 1) {

                                Intent aboutActivity = new Intent(getApplicationContext(), AboutActivity.class);
                                aboutActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                aboutActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                aboutActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                MainActivity.this.startActivity(aboutActivity);
                            }
                            if (i == 2) {

                                Intent whoWeAreActivity = new Intent(getApplicationContext(), WhoWeAreActivity.class);
                                whoWeAreActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                whoWeAreActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                whoWeAreActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                MainActivity.this.startActivity(whoWeAreActivity);
                            }
                            if (i == 3) {

                                Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else if (i == 4) {

                                Intent intent = new Intent(MainActivity.this, ProjectsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else if (i == 5) {

                                Intent intent = new Intent(MainActivity.this, ClientActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else if (i == 7) {

                                Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else if (i == 9) {

                                if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == false) {


                                    if (appPreferences.getString(Constants.GCM_REG_ID, "").equals("") || appPreferences.getString(Constants.GCM_REG_ID, "") == null) {


                                        registerInBackground();
                                    }

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    intent.putExtra(Constants.CALLED_FROM, "msg");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra(Constants.GCM_REG_ID, appPreferences.getString(Constants.GCM_REG_ID, ""));
                                    startActivity(intent);

                                } else {

                                    Intent intent = new Intent(MainActivity.this, MessagesActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }


                            } else if (i == 10) {

//                            Toast.makeText(getApplicationContext(), "8 tracker", Toast.LENGTH_SHORT).show();

                                if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == false) {


                                    if (appPreferences.getString(Constants.GCM_REG_ID, "").equals("") || appPreferences.getString(Constants.GCM_REG_ID, "") == null) {


                                        registerInBackground();
                                    }

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    intent.putExtra(Constants.CALLED_FROM, "tracker");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra(Constants.GCM_REG_ID, appPreferences.getString(Constants.GCM_REG_ID, ""));
                                    startActivity(intent);

                                } else {


                                }

                            } else if (i == 12) {

                                if (ConnectionDetector.isNetworkAvailable(MainActivity.this) == false) {

                                    Toast.makeText(MainActivity.this, "Network is not Available", Toast.LENGTH_LONG).show();
                                } else {

                                    if (appPreferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == true) {


                                        Intent intent = new Intent(MainActivity.this, LogoutService.class);
                                        intent.setAction(LogoutReciever.ACTION_LOGOUT);
                                        intent.putExtra(Constants.USERNAME, appPreferences.getString(Constants.USERNAME, ""));

                                        intent.putExtra(Constants.ACTION, "logout");
                                        MainActivity.this.startService(intent);


                                    } else {

                                        if (appPreferences.getString(Constants.GCM_REG_ID, "").equals("") || appPreferences.getString(Constants.GCM_REG_ID, "") == null) {


                                            registerInBackground();
                                        }

                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        intent.putExtra(Constants.CALLED_FROM, "login");
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra(Constants.GCM_REG_ID, appPreferences.getString(Constants.GCM_REG_ID, ""));
                                        startActivity(intent);

                                    }
                                }
                            }else if (i==13){

                                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }


                        }
                    }).build();

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


    }

//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        finish();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }

    public void showRevenueRecords(View v) {

        Toast.makeText(this, "Revenue Record", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.this.finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private boolean checkPlayServices() {

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 9000).show();
            } else {

                Log.i("PLAY SERVICE STATUS", "This device is not supported.");
                finish();

            }

            return false;
        }
        return true;
    }

    private void registerInBackground() {

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {

                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    String gcmId = gcm.register(Constants.PROJECT_ID);

                    setRegistrationId(gcmId);

                    regId = gcmId;

                    Log.i("n ew reg key", regId);
                    storeRegistrationId(getApplicationContext(), gcmId);
                    return Boolean.TRUE;

                } catch (IOException ex) {
                    Log.e(TAG, "Failed to register on attempt " + ex);

                }

                return Boolean.FALSE;
            }

            @Override
            protected void onPostExecute(Boolean status) {
                //broadcastStatus(status);
            }
        }.execute(null, null, null);
    }

    private String getRegistrationId(Context context) {

        Log.i("GCM App Log", "get Registration id");

        SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);

        String registrationId = preferences.getString(Constants.GCM_REG_ID, "");
        Log.i("regId:", registrationId);
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }

        int registeredVersion = preferences.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private void setRegistrationId(String regId) {

        this.regId = regId;
    }

    private void storeRegistrationId(Context context, String regId) {

        int appVersion = getAppVersion(context);

        Log.i(TAG, "Saving regId on app version " + appVersion);

        SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GCM_REG_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }

    public void showUnitConverter(View v) {

        Toast.makeText(this, "UnitConverter", Toast.LENGTH_SHORT).show();
    }


    public class LogoutReciever extends BroadcastReceiver {

        public static final String ACTION_LOGOUT = "in.co.scorp.jovialgroup.logout";

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("Logout Reciever:", "Logouted");

            logoutPreference = MainActivity.this.getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);

            logoutPreference.edit().putBoolean(Constants.USER_LOGIN_STATUS, false).commit();
            logoutPreference.edit().putString(Constants.SERVICES_LIST, "").commit();
            logoutPreference.edit().putString(Constants.USER_FULL_NAME, "").commit();
            logoutPreference.edit().putString(Constants.USERNAME, "").commit();
            logoutPreference.edit().putString(Constants.USERID,"").commit();
            logoutPreference.edit().putString(Constants.BLOCK_NO, "").commit();
            logoutPreference.edit().putString(Constants.MOJE, "").commit();
            logoutPreference.edit().putInt(Constants.UROLE, 0).commit();
            logoutPreference.edit().putString(Constants.NAME, "").commit();
            logoutPreference.edit().putString(Constants.PHONE, "").commit();
            logoutPreference.edit().putString(Constants.DOB, "").commit();
            logoutPreference.edit().putString(Constants.EMAIL, "").commit();
            logoutPreference.edit().putLong(Constants.ORDER_AMNT, 0).commit();


            MainActivity.this.finish();

            Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
        }
    }

}
