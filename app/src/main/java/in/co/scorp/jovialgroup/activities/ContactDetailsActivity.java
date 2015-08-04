package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;

public class ContactDetailsActivity extends ActionBarActivity {

    Typeface typefaceTitle;
    Typeface typefaceNormal;

    TextView tvContactTitle,tvPhoneNumberOne,tvPhoneNumberTypeOne,tvPhoneNumberTwo,tvPhoneNumberTypeTwo;

    TextView tvEmailIdText, tvWebAddOne, tvWebAddTwo, tvQuickContactTitle, tvAddressTitleTwo;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        LinearLayout contactContainer = (LinearLayout) findViewById(R.id.contact_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(contactContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_contact);
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

        tvContactTitle = (TextView)findViewById(R.id.tvContactPageTitle);

        tvPhoneNumberOne = (TextView)findViewById(R.id.tv_phonenumber_1);

        tvPhoneNumberTypeOne = (TextView)findViewById(R.id.tv_phonenumber_type_1);

        tvPhoneNumberTwo = (TextView)findViewById(R.id.tv_phonenumber_2);

        tvPhoneNumberTypeTwo = (TextView)findViewById(R.id.tv_phonenumber_type_2);

        tvEmailIdText = (TextView)findViewById(R.id.tv_email_address);

        tvQuickContactTitle = (TextView)findViewById(R.id.tv_contact_quick_contact_title);

        tvWebAddOne = (TextView)findViewById(R.id.tv_web_address_1);

        tvWebAddTwo = (TextView)findViewById(R.id.tv_web_address_2);

        tvAddressTitleTwo = (TextView)findViewById(R.id.tvContact2Title);
    }

    public void callOne(View v) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:+" + "02612725000"));
        startActivity(intent);
    }

    public void callTwo(View v) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:+" + "02613004999"));
        startActivity(intent);
    }

    public void doMail(View v) {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"customercare@jovialgroup.net"});
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));

    }

    public void showWebOne(View v) {

        Intent webOneIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jovialgroup.net"));
        startActivity(webOneIntent);

    }

    public void showWebTwo(View v) {

        Intent webOneIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jovialarchitects.net"));
        startActivity(webOneIntent);
    }


    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(ContactDetailsActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
