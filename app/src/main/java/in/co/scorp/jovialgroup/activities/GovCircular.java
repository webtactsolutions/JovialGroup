package in.co.scorp.jovialgroup.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.adapters.GovCircularAdapter;
import in.co.scorp.jovialgroup.database.GovCircularDB;
import in.co.scorp.jovialgroup.models.Circular;
import in.co.scorp.jovialgroup.util.CircularParser;
import in.co.scorp.jovialgroup.util.ConnectionDetector;
import in.co.scorp.jovialgroup.util.Constants;

public class GovCircular extends ActionBarActivity {

    ListView lvGovCircular;
    TextView tvNoCircular;
    Circular circular;

    ArrayList<Circular> AllList;

    GovCircularAdapter adapter;
    String allCircLoadResponse="";
    ProgressDialog listDialog;
    Toolbar toolbar;
    public ProgressDialog circularDownloadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_circular);

        LinearLayout jantriLayout = (LinearLayout) findViewById(R.id.circular_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(jantriLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar_circular);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });


        lvGovCircular = (ListView) findViewById(R.id.lv_gov_circular);
        tvNoCircular = (TextView) findViewById(R.id.tv_no_circular);

        AllList = null;

        if (ConnectionDetector.isNetworkAvailable(GovCircular.this)) {



        } else {




        }

    }

//    public void setCircularAdapter(ArrayList<Circular> list){
//
//        adapter = new GovCircularAdapter(GovCircular.this, list);
//
//        lvGovCircular.setAdapter(adapter);
//
//        lvGovCircular.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                circular = (Circular) adapterView.getItemAtPosition(i);
//
//                if (circular.isDownloaded() == 0) {
//
//                    if (view.getId() == R.id.btn_download_circ) {
//
//
//                        if (ConnectionDetector.isNetworkAvailable(GovCircular.this)) {
//
//                            DownloadCircular downloadCircular = new DownloadCircular();
//                            downloadCircular.execute("http://www.jovialgroup.net/control/images/help/" + circular.getcDocument(), circular.getcDocument(), String.valueOf(circular.getId()));
//
//                        } else {
//
//                            Toast.makeText(GovCircular.this, "Internet Not Available", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                } else {
//
//                    showPdf(circular.getLocalPath());
//                }
//            }
//        });
//    }

    @Override
    public void onBackPressed() {

        finish();

        Intent intent = new Intent(GovCircular.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


}
