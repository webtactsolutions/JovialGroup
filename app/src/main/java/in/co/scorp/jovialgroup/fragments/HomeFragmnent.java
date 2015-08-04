package in.co.scorp.jovialgroup.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.activities.ContactDetailsActivity;
import in.co.scorp.jovialgroup.activities.GovCircular;
import in.co.scorp.jovialgroup.activities.LoanCalculator;
import in.co.scorp.jovialgroup.activities.LoginActivity;
import in.co.scorp.jovialgroup.activities.MyAccount;
import in.co.scorp.jovialgroup.activities.RevenueRecordActivity;
import in.co.scorp.jovialgroup.activities.ServicesActivity;
import in.co.scorp.jovialgroup.activities.UnitConverterActivity;
import in.co.scorp.jovialgroup.adapters.SliderAdapter;
import in.co.scorp.jovialgroup.services.SliderImgService;
import in.co.scorp.jovialgroup.util.ConnectionDetector;
import in.co.scorp.jovialgroup.util.Constants;
import in.co.scorp.jovialgroup.util.ImageSliderParser;

import static android.view.MotionEvent.*;

/**
 * Created by SCORP on 09/04/15.
 */
public class HomeFragmnent extends Fragment implements View.OnTouchListener {


    private static final String APP_VERSION = "appVersion";
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    String regId = "";

    Typeface typefaceTitle;
    Typeface typefaceNormal;
    boolean clicked = false;
    View v;
    ImageView ivMenuLogo;
    RelativeLayout rl2 = null;
    ProgressBar progressBar;
    VideoView videoView;
    ViewPager imageSlider;
    Rect rect2;
    Button btnRevenueRecord;
    Button btnGovCircular;
    Button btnLoanCalc;
    Button btnTracker;
    Button btnContactUs;
    Button btnJantri;
    Button btnMap;
    Button btnUnitConverter;
    Button btnMyAcount;
    RelativeLayout rlContainer;

    int btnMenuX, btnMenuY, btnJantriX, btnJantriY, btnGovX, btnGovY, btnRevenueX, btnRevenueY, btnTrackerX, btnTrackerY, btnUnitConverterX, btnUnitConverterY, btnLoanX, btnLoanY, btnContactX, btnContctY;

    LinearLayout llRevenue, llCircular, llLoan, llUnitCon, llJantri, llMap, llMyacc, llTracker, llContact;

    SharedPreferences preferences;


    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);

        IntentFilter filter = new IntentFilter(ImageSliderBroadcast.ACTION_MOSLIDER_LOAD);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new ImageSliderBroadcast(), filter);

        preferences = getActivity().getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        Intent intent = new Intent(getActivity(), SliderImgService.class);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction(ImageSliderBroadcast.ACTION_MOSLIDER_LOAD);
        intent.putExtra(Constants.ACTION, Constants.MOSLIDER_ACTION);
        getActivity().startService(intent);

        typefaceTitle = Typeface.createFromAsset(getActivity().getAssets(), "oswald_regular.ttf");
        typefaceNormal = Typeface.createFromAsset(getActivity().getAssets(), "normal.ttf");
        final SharedPreferences appPreferences = getActivity().getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        imageSlider = (ViewPager) v.findViewById(R.id.vp_home_fragment);


        ivMenuLogo = (ImageView) v.findViewById(R.id.menu_logo);

        rl2 = (RelativeLayout) v.findViewById(R.id.rootLayuot);

        rect2 = new Rect(rl2.getLeft(), rl2.getTop(), rl2.getRight(), rl2.getBottom());


        btnRevenueRecord = (Button) v.findViewById(R.id.btn_revenue);

        btnGovCircular = (Button) v.findViewById(R.id.btn_gov_circular);
        btnGovCircular.setOnTouchListener(this);

        btnLoanCalc = (Button) v.findViewById(R.id.btn_loan_calculator);
        btnLoanCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnTracker = (Button) v.findViewById(R.id.btn_tracker);
        btnTracker.setClickable(true);
        btnTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Tracker", Toast.LENGTH_SHORT).show();
            }
        });

        btnContactUs = (Button) v.findViewById(R.id.btn_contact_us);
        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnJantri = (Button) v.findViewById(R.id.btn_jantri);
        btnJantri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMap = (Button) v.findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Map", Toast.LENGTH_SHORT).show();
            }
        });

        btnUnitConverter = (Button) v.findViewById(R.id.btn_unit_converter);
        btnUnitConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMyAcount = (Button) v.findViewById(R.id.btn_my_account);
        btnMyAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Account", Toast.LENGTH_SHORT).show();
            }
        });


        ((Button) v.findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                showMenu();
            }
        });


        return v;
    }

    private void setRegistrationId(String regId) {

        this.regId = regId;
    }

    private void storeRegistrationId(Context context, String regId) {

        int appVersion = getAppVersion(context);

        Log.i("save gcm", "Saving regId on app version " + appVersion);

        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GCM_REG_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
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

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), 9000).show();
            } else {

                Log.i("PLAY SERVICE STATUS", "This device is not supported.");
                getActivity().finish();

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
                        gcm = GoogleCloudMessaging.getInstance(getActivity());
                    }
                    String gcmId = gcm.register(Constants.PROJECT_ID);

                    setRegistrationId(gcmId);

                    regId = gcmId;

                    Log.i("n ew reg key", regId);
                    storeRegistrationId(getActivity(), gcmId);
                    return Boolean.TRUE;

                } catch (IOException ex) {
                    Log.e("gcm attemp", "Failed to register on attempt " + ex);

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

        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        String registrationId = preferences.getString(Constants.GCM_REG_ID, "");
        Log.i("regId:", registrationId);
        if (registrationId.isEmpty()) {
            Log.i("reg_not_fonund", "Registration not found.");
            return "";
        }

        int registeredVersion = preferences.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i("app version changed", "App version changed.");
            return "";
        }
        return registrationId;
    }

    Button btnGovCerti, btnRevenue, btnGCirle, btnLoanC, btnTrackerMenu, btnContc, btnMapMenu, btnJantriMenu, btnUnit, btnAccount;

    public void showMenu() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(15, 15, 0, 0);

        int scrWidth = rl2.getWidth();

        Log.i("Width", "" + scrWidth);

        int distance = (scrWidth / 4) + 10;

        int xDest = rect2.centerX() + distance;
        int yDest = rect2.centerY();
        moveViewToScreenCenter(btnRevenueRecord, xDest, yDest);
//        Drawable topGov = getResources().getDrawable(R.drawable.villagemap_48);
        btnRevenue = new Button(getActivity());
        btnRevenue.setLayoutParams(params);
//        btnRevenue.setTop(rect2.top + distance);
//        btnRevenue.setLeft(rect2.left);
        btnRevenue.setX(xDest);
        btnRevenue.setY(yDest);
        btnRevenue.setBackgroundColor(Color.TRANSPARENT);
        btnRevenue.setTextSize(12);
        btnRevenue.setTypeface(Typeface.DEFAULT_BOLD);
        btnRevenue.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.revenuerecord_48, 0, 0);
        btnRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RevenueRecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnRevenue.setVisibility(View.GONE);
        btnRevenueRecord.setVisibility(View.INVISIBLE);
        rl2.addView(btnRevenue);


        xDest = rect2.centerX() - distance;
        yDest = rect2.centerY();
        moveViewToScreenCenter(btnGovCircular, xDest, yDest);
        btnGCirle = new Button(getActivity());
        btnGCirle.setX(xDest);
        btnGCirle.setLayoutParams(params);
        btnGCirle.setY(yDest);
        btnGCirle.setBackgroundColor(Color.TRANSPARENT);
        btnGCirle.setTextSize(12);
        btnGCirle.setTypeface(Typeface.DEFAULT_BOLD);
        btnGCirle.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.help_48, 0, 0);
        btnGCirle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GovCircular.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnGCirle.setVisibility(View.GONE);
        btnGovCircular.setVisibility(View.INVISIBLE);
        rl2.addView(btnGCirle);

        xDest = rect2.centerX();
        yDest = rect2.centerY() + distance;
        moveViewToScreenCenter(btnLoanCalc, xDest, yDest);
        btnLoanC = new Button(getActivity());
        btnLoanC.setX(xDest);
        btnLoanC.setLayoutParams(params);
        btnLoanC.setY(yDest);
        btnLoanC.setTextSize(12);
        btnLoanC.setBackgroundColor(Color.TRANSPARENT);
        btnLoanC.setTypeface(Typeface.DEFAULT_BOLD);
        btnLoanC.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.loancalculator_48, 0, 0);
        btnLoanC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoanCalculator.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnLoanC.setVisibility(View.GONE);
        btnLoanCalc.setVisibility(View.GONE);
        rl2.addView(btnLoanC);

        xDest = rect2.centerX();
        yDest = rect2.centerY() - distance;
        moveViewToScreenCenter(btnTracker, xDest, yDest);
        btnTrackerMenu = new Button(getActivity());
        btnTrackerMenu.setX(xDest);
        btnTrackerMenu.setY(yDest);
        btnTrackerMenu.setTextSize(12);
        btnTrackerMenu.setLayoutParams(params);
        btnTrackerMenu.setBackgroundColor(Color.TRANSPARENT);
        btnTrackerMenu.setTypeface(Typeface.DEFAULT_BOLD);
        btnTrackerMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tracker_48, 0, 0);
        btnTrackerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferences.getBoolean(Constants.USER_LOGIN_STATUS, false) == false) {


                    if (preferences.getString(Constants.GCM_REG_ID, "").equals("") || preferences.getString(Constants.GCM_REG_ID, "") == null) {


                        registerInBackground();
                    }

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra(Constants.CALLED_FROM, "tracker");
                    intent.putExtra(Constants.GCM_REG_ID, preferences.getString(Constants.GCM_REG_ID, ""));
                    startActivity(intent);
                } else {

                    if (ConnectionDetector.isNetworkAvailable(getActivity()) == false) {

                        Toast.makeText(getActivity(), "Network not Available", Toast.LENGTH_SHORT).show();
                    } else {


                    }
                }
            }
        });
        btnTrackerMenu.setVisibility(View.GONE);
        btnTracker.setVisibility(View.INVISIBLE);
        rl2.addView(btnTrackerMenu);

        xDest = rect2.centerX() + distance;
        yDest = rect2.centerY() + distance;
        moveViewToScreenCenter(btnContactUs, xDest, yDest);
        btnContc = new Button(getActivity());
        btnContc.setX(xDest);
        btnContc.setY(yDest);
        btnContc.setTextSize(12);
        btnContc.setBackgroundColor(Color.TRANSPARENT);
        btnContc.setLayoutParams(params);
        btnContc.setTypeface(Typeface.DEFAULT_BOLD);
        btnContc.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contact_48, 0, 0);
        btnContc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnContc.setVisibility(View.GONE);
        btnContactUs.setVisibility(View.INVISIBLE);
        rl2.addView(btnContc);

        xDest = rect2.centerX() + distance;
        yDest = rect2.centerY() - distance;
        moveViewToScreenCenter(btnMap, xDest, yDest);
        Drawable map = getActivity().getResources().getDrawable(R.drawable.villagemap_48);
        map.setBounds(0, 0, map.getIntrinsicWidth(), map.getIntrinsicHeight());
        btnMapMenu = new Button(getActivity());
        btnMapMenu.setX(xDest);
        btnMapMenu.setY(yDest);
        btnMapMenu.setLayoutParams(params);
        btnMapMenu.setTextSize(12);
        btnMapMenu.setBackgroundColor(Color.TRANSPARENT);
        btnMapMenu.setTypeface(Typeface.DEFAULT_BOLD);
        btnMapMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.services_48, 0, 0);
        btnMapMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ServicesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnMapMenu.setVisibility(View.GONE);
        btnMap.setVisibility(View.INVISIBLE);
        rl2.addView(btnMapMenu);


        xDest = rect2.centerX() - distance;
        yDest = rect2.centerY() - distance;
        moveViewToScreenCenter(btnJantri, xDest, yDest);
        btnJantriX = xDest;
        btnJantriY = yDest;
        btnJantriMenu = new Button(getActivity());
        btnJantriMenu.setX(xDest);
        btnJantriMenu.setY(yDest);
        btnJantriMenu.setTextSize(12);
        btnJantriMenu.setBackgroundColor(Color.TRANSPARENT);
        btnJantriMenu.setLayoutParams(params);
        btnJantriMenu.setTypeface(Typeface.DEFAULT_BOLD);
        btnJantriMenu.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.villagemap_48, 0, 0);
        btnJantriMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        btnJantriMenu.setVisibility(View.GONE);
        btnJantri.setVisibility(View.INVISIBLE);
        rl2.addView(btnJantriMenu);


        xDest = rect2.centerX() - distance;
        yDest = rect2.centerY() + distance;
        moveViewToScreenCenter(btnUnitConverter, xDest, yDest);
        btnUnit = new Button(getActivity());
        btnUnit.setX(xDest);
        btnUnit.setY(yDest);
        btnUnit.setTextSize(12);
        btnUnit.setLayoutParams(params);
        btnUnit.setBackgroundColor(Color.TRANSPARENT);
        btnUnit.setTypeface(Typeface.DEFAULT_BOLD);
        btnUnit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.unitconverter_48, 0, 0);
        btnUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnUnit.setVisibility(View.GONE);
        btnUnitConverter.setVisibility(View.INVISIBLE);
        rl2.addView(btnUnit);

        xDest = rect2.centerX();
        yDest = rect2.centerY();
        moveViewToScreenCenter(btnMyAcount, xDest, yDest);
        btnAccount = new Button(getActivity());
        btnAccount.setX(xDest);
        btnAccount.setY(yDest);
        btnAccount.setTextSize(12);
        btnAccount.setLayoutParams(params);
        btnAccount.setBackgroundColor(Color.TRANSPARENT);
        btnAccount.setTypeface(Typeface.DEFAULT_BOLD);
        btnAccount.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_account_48, 0, 0);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MyAccount.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnAccount.setVisibility(View.GONE);
        btnMyAcount.setVisibility(View.INVISIBLE);
        rl2.addView(btnAccount);


        if (clicked == true)
            clicked = false;
        else
            clicked = true;


    }


    private void moveViewToScreenCenter(final View view, int xDest, int yDest) {

        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);


        Rect rect = new Rect(rl2.getLeft(), rl2.getTop(), rl2.getRight(), rl2.getBottom());
        int x = rect.centerX();
        int y = rect.centerY();

        if (clicked) {


            TranslateAnimation anim = new TranslateAnimation(xDest, 0, yDest, 0);
            anim.setDuration(300);
            anim.setFillAfter(true);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    view.setAlpha((float) 1.0);


                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    view.setAlpha((float) 0.0);


                    AlphaAnimation logoAnim = new AlphaAnimation(0.0f, 1.0f);
                    logoAnim.setDuration(300);
                    logoAnim.setFillAfter(true);

                    logoAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {


                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ivMenuLogo.startAnimation(logoAnim);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);


            view.startAnimation(alphaAnimation);
            view.startAnimation(anim);
            btnJantriMenu.setVisibility(View.GONE);
            btnMapMenu.setVisibility(View.GONE);
            btnTrackerMenu.setVisibility(View.GONE);
            btnRevenue.setVisibility(View.GONE);
            btnAccount.setVisibility(View.GONE);
            btnGCirle.setVisibility(View.GONE);
            btnContc.setVisibility(View.GONE);
            btnUnit.setVisibility(View.GONE);
            btnLoanC.setVisibility(View.GONE);

//            btnJantriMenu.setVisibility(View.INVISIBLE);
//            btnMapMenu.setVisibility(View.INVISIBLE);
//            btnTrackerMenu.setVisibility(View.INVISIBLE);
//            btnGovCerti.setVisibility(View.INVISIBLE);
//            btnAccount.setVisibility(View.INVISIBLE);
//            btnGCirle.setVisibility(View.INVISIBLE);
//            btnContc.setVisibility(View.INVISIBLE);
//            btnUnit.setVisibility(View.INVISIBLE);
//            btnLoanC.setVisibility(View.INVISIBLE);
            view.setAlpha((float) 0.1);


        } else {

            //TranslateAnimation anim = new TranslateAnimation(0, xDest - originalPos[0], 0, yDest - originalPos[1]);
            view.setAlpha((float) 1);

            TranslateAnimation anim = new TranslateAnimation(0, xDest, 0, yDest);
            anim.setDuration(300);
            anim.setFillAfter(true);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {


                }

                @Override
                public void onAnimationEnd(Animation animation) {


                    btnJantriMenu.setVisibility(View.VISIBLE);
                    btnMapMenu.setVisibility(View.VISIBLE);
                    btnTrackerMenu.setVisibility(View.VISIBLE);
                    btnRevenue.setVisibility(View.VISIBLE);
                    btnAccount.setVisibility(View.VISIBLE);
                    btnGCirle.setVisibility(View.VISIBLE);
                    btnContc.setVisibility(View.VISIBLE);
                    btnUnit.setVisibility(View.VISIBLE);
                    btnLoanC.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            view.startAnimation(anim);


            AlphaAnimation logoAnim = new AlphaAnimation(0.1f, 0.0f);
            logoAnim.setDuration(300);
            logoAnim.setFillAfter(true);
            ivMenuLogo.startAnimation(logoAnim);
            logoAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {


                }

                @Override
                public void onAnimationEnd(Animation animation) {

//
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    String gcmId = "";

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {

            case ACTION_DOWN:

                if (view.getId() == btnGovCircular.getId()) {

                    Toast.makeText(getActivity(), "circular", Toast.LENGTH_LONG).show();

                }

                break;
        }
        return false;
    }

    public class ImageSliderBroadcast extends BroadcastReceiver {

        public static final String ACTION_MOSLIDER_LOAD = "in.co.scorp.jovial.moslider";
        ArrayList<String> f = new ArrayList<String>();
        File[] listFile;
        SliderAdapter sliderAdapter;


        @Override
        public void onReceive(Context context, Intent intent) {

            String response = intent.getStringExtra(Constants.RESPONSE);
            Log.i("Response retrive", "" + response);


            ImageLoader imageLoader = ImageLoader.getInstance();

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
            imageLoader.init(config);
            imageLoader.loadImage("http://jovialgroup.net/control/images/moslider/banner1.jpg", new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    createFile(loadedImage, "banner1.jpg");


                }
            });

            ImageLoader imageLoader2 = ImageLoader.getInstance();

            ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(context).build();
            imageLoader2.init(config2);
            imageLoader2.loadImage("http://jovialgroup.net/control/images/moslider/banner2.jpg", new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    createFile(loadedImage, "banner2.jpg");


                }
            });

            ImageLoader imageLoader3 = ImageLoader.getInstance();

            ImageLoaderConfiguration config3 = new ImageLoaderConfiguration.Builder(context).build();
            imageLoader3.init(config3);
            imageLoader3.loadImage("http://jovialgroup.net/control/images/moslider/banner3.jpg", new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    createFile(loadedImage, "banner3.jpg");


                }
            });

            ImageLoader imageLoader4 = ImageLoader.getInstance();

            ImageLoaderConfiguration config4 = new ImageLoaderConfiguration.Builder(context).build();
            imageLoader4.init(config4);
            imageLoader4.loadImage("http://jovialgroup.net/control/images/moslider/banner4.jpg", new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    createFile(loadedImage, "banner4.jpg");


                }
            });


            ImageSliderParser imageSliderParser = new ImageSliderParser();


            sliderAdapter = new SliderAdapter(context, getFromSdcard());

            Log.i("slider count", "" + sliderAdapter.getCount());

            imageSlider.setAdapter(sliderAdapter);


            int i = 0;
            final Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < sliderAdapter.getCount(); i++) {
                        final int value = i;
                        if (i == sliderAdapter.getCount() - 1) {
                            i = 0;
                        }
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                imageSlider.setCurrentItem(value, true);
                            }
                        });


                    }
                }
            };
            new Thread(runnable).start();


        }

        public ArrayList<String> getFromSdcard() {
            File file = new File(Environment.getExternalStorageDirectory(), "/.JovialGroup/moslider");

            if (file.isDirectory()) {
                listFile = file.listFiles();


                for (int i = 0; i < listFile.length; i++) {

                    f.add(listFile[i].getAbsolutePath());

                }
            }

            return f;
        }

        public void createFile(Bitmap bitmap, String name) {

            String mainDirPath = Environment.getExternalStorageDirectory().toString() + "/.JovialGroup";

            File mainDir = new File(mainDirPath);
            if (!mainDir.exists()) {

                mainDir.mkdir();
            } else {

                File sliderDir = new File(mainDirPath + "/moslider");

                if (!sliderDir.exists()) {

                    sliderDir.mkdir();
                } else {

                    File banner = new File(mainDirPath + "/moslider/" + name);
                    if (!banner.exists()) {

                        banner.delete();

                        try {

                            FileOutputStream out = new FileOutputStream(banner);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();
                        } catch (Exception e) {

                            System.out.print("" + e.toString());
                        }
                    }
                }
            }
        }
    }

}
