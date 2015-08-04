package in.co.scorp.jovialgroup.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.services.UserCheckService;
import in.co.scorp.jovialgroup.util.Constants;
import in.co.scorp.jovialgroup.util.UserCheckParser;

/**
 * Created by SCORP on 19/06/15.
 */
public class RegistrationActivity extends ActionBarActivity {

    Toolbar toolbar;
    Typeface typefaceTitle,typefaceNormal;

    EditText etFullName, etUname, etPassword, etEmail, etPhone;
            TextView etDob;
    Button btnSubmit;
    private int year;
    private int month;
    private int day;

    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        IntentFilter intentFilter = new IntentFilter(UserChecked.ACTION_USER_CHECKED);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new UserChecked(), intentFilter);

        IntentFilter priceIntent = new IntentFilter(UserChecked.ACTION_USER_CHECKED);
        priceIntent.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new UserChecked(), priceIntent);


        RelativeLayout aboutContainer = (RelativeLayout) findViewById(R.id.reg_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(aboutContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_reg);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });

        typefaceTitle = Typeface.createFromAsset(this.getAssets(), "oswald_regular.ttf");
        typefaceNormal = Typeface.createFromAsset(this.getAssets(),"normal.ttf");

        initControlls();
    }

    @Override
    protected void onResume() {
        super.onResume();

        etUname.setTextColor(Color.parseColor("#333333"));
    }

    public void initControlls(){

        etFullName = (EditText) findViewById(R.id.et_reg_name);
        etUname = (EditText) findViewById(R.id.et_reg_uname);
        etDob = (TextView) findViewById(R.id.et_reg_dob);
        etPhone = (EditText) findViewById(R.id.et_reg_mob);
        etEmail = (EditText) findViewById(R.id.et_loan_amount);
        etPassword = (EditText) findViewById(R.id.et_reg_password);

        btnSubmit = (Button) findViewById(R.id.btn_reg_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isValid()) {

                    if (etEmail.length() > 1) {

                        if(isValidEmail(etEmail.getText().toString())){


                        }else {

                            Toast.makeText(RegistrationActivity.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
                        }


//                        if (etEmail.getText().toString().matches(email_regex)) {
//
//
//
//                        }
//                        else {
//
//                            Toast.makeText(RegistrationActivity.this, "Invalid Email Address", Toast.LENGTH_LONG).show();
//                        }
                    } else {


//                    MaskedWalletRequest maskedWalletRequest =
//                            MaskedWalletRequest.newBuilder()
//                                    .setMerchantName(Constants.MERCHANT_NAME)
//                                    .setPhoneNumberRequired(true)
//                                    .setShippingAddressRequired(true)
//                                    .setCurrencyCode("INR")
//                                    .setShouldRetrieveWalletObjects(true)
//                                    .setCart(Cart.newBuilder()
//                                            .setCurrencyCode(Constants.CURRENCY_CODE_INR)
//                                            .setTotalPrice("")
//                                            .addLineItem(LineItem.newBuilder()
//                                                    .setCurrencyCode(Constants.CURRENCY_CODE_INR)
//                                                    .setDescription("")
//                                                    .setQuantity("1")
//                                                    .setUnitPrice((context, itemInfo.priceMicros))
//                                            .setTotalPrice("130.00")
//                                            .build())
//                                            .build())
//                                    .setEstimatedTotalPrice("")
//                                    .build();
//
//                    WalletFragmentInitParams.Builder startParamsBuilder = WalletFragmentInitParams.newBuilder()
//                            .setMaskedWalletRequest(maskedWalletRequest)
//                            .setMaskedWalletRequestCode(REQUEST_CODE_MASKED_WALLET);

                    }

                }
                else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                    builder.setMessage("Please Enter All Details");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }

            }
        });

        etUname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b == false) {


                    if (etUname.length() > 0) {

                        progressDialog = new ProgressDialog(RegistrationActivity.this);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        btnSubmit.setEnabled(false);


                        String params = "action=" + Constants.CHECKUSER + "&uname=" + etUname.getText().toString().trim();
                        Intent intent = new Intent(RegistrationActivity.this, UserCheckService.class);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setAction(UserChecked.ACTION_USER_CHECKED);
                        intent.putExtra(Constants.PARAMS, params);
                        startService(intent);

                    }

                }
            }
        });

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);

                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                showDialog(999);

            }
        });

    }

    public boolean isValid(){

        if(etFullName.length()<1){

            return false;
        }else if (etUname.length()<1){

            return false;
        }
        else if (etPhone.length()<1){

            return false;
        }
        else if (etEmail.length()<1){

            return false;
        }

        else if (etPassword.length()<1){

            return false;
        }
        else if (etDob.length()<1){

            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

//        this.finish();
        Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public boolean isValidEmail(String email){

        Pattern pattern;
        Matcher matcher;

         String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);

        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        etDob.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    public class UserChecked extends BroadcastReceiver{

        public static final String ACTION_USER_CHECKED = "in.co.scorp.usercheck";

        @Override
        public void onReceive(Context context, Intent intent) {

            progressDialog.dismiss();

            UserCheckParser parser = new UserCheckParser();

            if(parser.getResponseCode(intent.getStringExtra(Constants.RESPONSE))==1){

                btnSubmit.setEnabled(true);
                etUname.setTextColor(Color.parseColor("#30810D"));

            }else if(parser.getResponseCode(intent.getStringExtra(Constants.RESPONSE))==0){

                btnSubmit.setEnabled(false);
                etUname.setTextColor(Color.parseColor("#EE5B2F"));
                Toast.makeText(RegistrationActivity.this,"Username is not available", Toast.LENGTH_LONG).show();

            }else {

                etUname.setTextColor(Color.parseColor("#333"));
                btnSubmit.setEnabled(false);

            }


        }
    }
}
