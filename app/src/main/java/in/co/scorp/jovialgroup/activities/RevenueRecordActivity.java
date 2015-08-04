package in.co.scorp.jovialgroup.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;

public class RevenueRecordActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private WebView wvRevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_record);

        LinearLayout projectsContainer = (LinearLayout) findViewById(R.id.revenue_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(projectsContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_revenue);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });
        wvRevenue  = new WebView(this);

        wvRevenue.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        wvRevenue.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        wvRevenue .loadUrl("http://anyror.gujarat.gov.in");
        setContentView(wvRevenue);

    }


    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(RevenueRecordActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
