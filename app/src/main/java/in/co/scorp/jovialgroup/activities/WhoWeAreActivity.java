package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.util.TextViewEx;

public class WhoWeAreActivity extends ActionBarActivity {

    private Toolbar toolbar;

    TextViewEx infra,associates,architect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_we_are);

        LinearLayout archContainer = (LinearLayout) findViewById(R.id.who_we_are_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(archContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_who_we_are);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });

        infra = (TextViewEx) findViewById(R.id.tv_infra_text);
        associates = (TextViewEx) findViewById(R.id.tv_associates_text);
        architect = (TextViewEx) findViewById(R.id.tv_architext_text);

        infra.setText(getResources().getString(R.string.infrastructure_text),true);
        associates.setText(getResources().getString(R.string.associates_text),true);
        architect.setText(getResources().getString(R.string.architecture_text),true);

    }



    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(WhoWeAreActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
