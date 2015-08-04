package in.co.scorp.jovialgroup.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.nio.DoubleBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.util.Constants;

public class LoanCalculator extends ActionBarActivity {

    private Toolbar toolbar;
    private SharedPreferences pref;
    private EditText etLoanAmount, etIntRate, etLoadDuration;
    private ToggleButton btnPeriod;
    private TextView tvStartDate;
    private int year,month,day;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);

        LinearLayout jantriLayout = (LinearLayout) findViewById(R.id.loan_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(jantriLayout);


        toolbar = (Toolbar) findViewById(R.id.toolbar_loan);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });

        pref = getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);

        initControll();

    }

    public void initControll(){

        etLoanAmount = (EditText) findViewById(R.id.et_loan_amount);
        etIntRate = (EditText) findViewById(R.id.et_loan_interest_rate);
        etLoadDuration = (EditText) findViewById(R.id.et_loan_duration);
        btnPeriod = (ToggleButton) findViewById(R.id.btnLoanPeriod);
        btnPeriod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {

                    btnPeriod.setTextOn("YEARS");
                } else {

                    btnPeriod.setTextOff("MONTHS");
                }
            }
        });

        tvStartDate = (TextView) findViewById(R.id.tv_loan_start_date);
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);

                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                showDialog(999);
            }
        });

        btnCalculate = (Button) findViewById(R.id.btn_loan_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isValid()){

                    int months;

                    if(!btnPeriod.isChecked()){

                        months = Integer.parseInt(etLoadDuration.getText().toString());
                    }else {

                        months = Integer.parseInt(etLoadDuration.getText().toString())*12;
                    }


                    Intent intent = new Intent(LoanCalculator.this, LoanCalcResult.class);
                    intent.putExtra(Constants.MAIN_AMOUNT, Double.parseDouble(etLoanAmount.getText().toString().trim()));
                    intent.putExtra(Constants.PERCENTAGE, Double.parseDouble(etIntRate.getText().toString().trim()));
                    intent.putExtra(Constants.MONTHS, months);
                    intent.putExtra(Constants.LSTART_DATE,tvStartDate.getText().toString());
                    startActivity(intent);

                }
            }
        });
    }

    public boolean isValid(){

        if(etIntRate.length()<1){

            return false;

        }else if(etLoadDuration.length()<1){

            return false;

        }else if (etIntRate.length()<1){

            return false;

        }else if (tvStartDate.length()<1){

            return false;

        }else {

            return true;
        }

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

        try {

//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//            Date d = format.parse(day + "-" + month + "-" + year);

            String m = "";
            String d = "";

            if(String.valueOf(month).length()==1){

                m = "0"+month;

            }
            else{

                m = String.valueOf(month);
            }

            if(String.valueOf(day).length()==1){

                d = "0"+day;

            }else{

                d = String.valueOf(day);
            }
//            if(String.valueOf(day).length()==1 && String.valueOf(month).length()==1) {
//
//                m = "0" + month;
//                d = "0" + day;
//            }

            tvStartDate.setText(d+"-"+m+"-"+year);



        }catch (Exception e){


        }
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day

            showDate(arg1, arg2 + 1, arg3);
        }
    };




    @Override
    public void onBackPressed() {

        Intent intent = new Intent(LoanCalculator.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
