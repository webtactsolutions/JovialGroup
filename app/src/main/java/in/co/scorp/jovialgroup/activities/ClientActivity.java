package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;

public class ClientActivity extends ActionBarActivity {

    private Toolbar toolbar;
    TextView tvClientsTitle;
    Typeface typefaceTitle;
    Typeface typefaceNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        LinearLayout aboutContainer = (LinearLayout) findViewById(R.id.client_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(aboutContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_clients);
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

        tvClientsTitle = (TextView)findViewById(R.id.tvClientTtitle);

        String[] clients = {
                "Sai Drashti Residency - Olpad, Surat - Gujarat - India",
                "Sai Shrushti Residency - Olpad, Surat - Gujarat - India",
                "Aangan Row House - Kamrej, Surat - Gujarat - India",
                "Global Indian International School, Surat - Gujarat - India",
                "Podar International School - Simada, Surat - Gujarat - India",
                "Euro School - Simada, Surat - Gujarat - India",
                "Raj Residency - Laskana, Surat - Gujarat - India",
                "Shubh Villa - Near Singanpore, Surat - Gujarat - India",
                "Prayosha Heights - Pune - Maharashtra - India",
                "Rhythm Mall - Mumbai - Maharashtra - India",
                "Dreamland Mall - Varachha , Surat - Gujarat - India"
        };

        ListView listView = (ListView)findViewById(R.id.clients_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.client_list_item,R.id.tv_clients_list_item_name,clients);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

//        this.finish();
        Intent intent = new Intent(ClientActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
