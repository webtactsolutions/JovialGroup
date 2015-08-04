package in.co.scorp.jovialgroup.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DecimalFormat;
import java.util.ArrayList;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.adapters.MainUnitConverterAdapter;
import in.co.scorp.jovialgroup.adapters.UnitNamesAdapter;
import in.co.scorp.jovialgroup.database.DatabaseHelper;
import in.co.scorp.jovialgroup.database.UnitsDB;
import in.co.scorp.jovialgroup.models.UnitConverter;
import in.co.scorp.jovialgroup.util.Constants;

public class UnitConverterActivity extends ActionBarActivity {

    ListView lvUnitConverter;
    ArrayList<UnitConverter> list;
    MainUnitConverterAdapter adapter;
    DatabaseHelper helper;

    TextView etUnitName;
    TextView etValue;
    Toolbar toolbar;
    TextView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnC, btnDel, btnMul, btnDivide, btnEql, btnDot, btnPlus, btnMinus, btnRight, btnLeft;
    TextView tvUnitValueDisplay;
    MainUnitConverterAdapter mainAdapter;
    double baseM;

    long a = 0;
    long b = 0;

    double mulPlier;
    double val1;
    double val2;
    double ans;
    int signBit;
    String sign;
    String MainValue;
    int FirstBit;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);

        LinearLayout projectsContainer = (LinearLayout) findViewById(R.id.unitconverter_layout);
        YoYo.with(Techniques.RollIn).duration(800).playOn(projectsContainer);

        pref = getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);
        pref.edit().putString(Constants.BASE_MULTIPLIER,"1").commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar_unit_converter);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });



        lvUnitConverter = (ListView) findViewById(R.id.lv_unitconverter);

        etValue  = (TextView) findViewById(R.id.et_unit_value);
        etValue.setText("1");


        etUnitName = (TextView) findViewById(R.id.et_unit_name);
        etUnitName.setText("Sq. Meter");

        etUnitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }

    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(UnitConverterActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void reset()
    {
        val2=val1=ans=0;
        signBit=0;
        FirstBit=0;
        tvUnitValueDisplay.setText("0");
        MainValue="0";
        sign="";
    }

}
