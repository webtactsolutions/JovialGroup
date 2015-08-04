package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Calendar;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.util.Constants;

public class LoanCalcResult extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView tvLoanAmount, tvRate, tvDate, tvMonths, tvMonthlyPayment, tvTotalPay, totalIntPaid,tvPayOff;
    private Button btnMonthly, btnYearly;
    private String day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calc_result);

        LinearLayout jantriLayout = (LinearLayout) findViewById(R.id.result_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(jantriLayout);


        toolbar = (Toolbar) findViewById(R.id.toolbar_loan_result);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                finish();
                onBackPressed();

            }
        });


        double loanAmount = getIntent().getDoubleExtra(Constants.MAIN_AMOUNT, 0);
        double rateOfInterest = getIntent().getDoubleExtra(Constants.PERCENTAGE,0);
        int numberOfMonths = getIntent().getIntExtra(Constants.MONTHS,0);

        double temp = 1200;           //100*numberofmonths(12))
        double interestPerMonth = rateOfInterest/temp;
        //System.out.println(interestPerMonth);

        double onePlusInterestPerMonth = 1 + interestPerMonth;
        //System.out.println(onePlusInterestPerMonth);

        double powerOfOnePlusInterestPerMonth = Math.pow(onePlusInterestPerMonth,numberOfMonths);
        //System.out.println(powerOfOnePlusInterestPerMonth);

        double powerofOnePlusInterestPerMonthMinusOne = powerOfOnePlusInterestPerMonth-1;
        //System.out.println(powerofOnePlusInterestPerMonthMinusOne);

        double divides = powerOfOnePlusInterestPerMonth/powerofOnePlusInterestPerMonthMinusOne;

        double principleMultiplyInterestPerMonth = loanAmount * interestPerMonth;
        //System.out.println(principleMultiplyInterestPerMonth);

        final double totalEmi =  principleMultiplyInterestPerMonth*divides;
       // System.out.println("EMI per month (Exact) : " + totalEmi);

        double finalValue = Math.round( totalEmi * 100.0 ) / 100.0;

        getDate(getIntent().getStringExtra(Constants.LSTART_DATE));

//        Double totalPay = ((getIntent().getDoubleExtra(Constants.PERCENTAGE, 0)*getIntent().getDoubleExtra(Constants.MAIN_AMOUNT, 0))/100)+ getIntent().getDoubleExtra(Constants.MAIN_AMOUNT, 0);
//        Double monthlyPay = totalPay/getIntent().getIntExtra(Constants.MONTHS,0);
//        Double totalInterestPaid = (getIntent().getDoubleExtra(Constants.PERCENTAGE, 0)*getIntent().getDoubleExtra(Constants.MAIN_AMOUNT, 0))/100;

        final double totalPayment = finalValue*numberOfMonths;
        final double totalIntrestpayment = totalPayment-loanAmount;


        tvDate = (TextView) findViewById(R.id.tvResDate);
        tvLoanAmount = (TextView) findViewById(R.id.tvLoanAmount);
        tvMonths = (TextView) findViewById(R.id.tvMonths);
        tvRate = (TextView) findViewById(R.id.tvRate);
        tvMonthlyPayment = (TextView) findViewById(R.id.tvMonthPayment);
        tvTotalPay = (TextView) findViewById(R.id.tvToatalPayment);
        totalIntPaid = (TextView) findViewById(R.id.tvTotalInterestPaid);
        tvPayOff = (TextView) findViewById(R.id.tvPayoffDate);

        btnMonthly = (Button) findViewById(R.id.btnMonthlyAt);
        btnYearly = (Button) findViewById(R.id.btnYearlyAt);
        btnYearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        tvPayOff.setText(setPayoffDate(getIntent().getStringExtra(Constants.LSTART_DATE)));
        tvTotalPay.setText(""+String.format("%.3f",totalPayment));
        totalIntPaid.setText(""+String.format("%.3f",totalIntrestpayment));

        tvMonths.setText(""+getIntent().getIntExtra(Constants.MONTHS,0)+" Months");
        tvRate.setText(""+String.format("%.3f",getIntent().getDoubleExtra(Constants.PERCENTAGE, 0)));
        tvLoanAmount.setText(""+String.format("%.3f",getIntent().getDoubleExtra(Constants.MAIN_AMOUNT, 0)));
        tvDate.setText(getIntent().getStringExtra(Constants.LSTART_DATE));
        tvMonthlyPayment.setText("" + String.format("%.3f", finalValue));

//        Log.i("total pay",""+(monthlyPay+getIntent().getLongExtra(Constants.MAIN_AMOUNT,0))/12);
        //long totalPayment = (getIntent().getIntExtra(Constants.PERCENTAGE,0)*100);

    }

    public String getDate(String d){

        day = d.substring(0,2);
        month = d.substring(3,5);
        year = d.substring(6,10);

        return day+"-"+month+"-"+""+(Integer.parseInt(year)+1);
    }

    public String setPayoffDate(String d){

        Calendar calendar =Calendar.getInstance();
        calendar.add(Calendar.DATE,Integer.parseInt(day));
        calendar.add(Calendar.MONTH, getIntent().getIntExtra(Constants.MONTHS,0));

        return ""+calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR);
    }

}