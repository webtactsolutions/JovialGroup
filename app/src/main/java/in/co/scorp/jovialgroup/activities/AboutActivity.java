package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.util.TextViewEx;

public class AboutActivity extends ActionBarActivity {


    TextView tvAboutTitle;
    TextView tvAbuotText;
//    Typeface typefaceTitle;
//    Typeface typefaceNormal;
//    Typeface typefaceStatement;

    ImageView ivAboutBanner;
    TextView tvAboutFirstPersonName;
    TextView tvAboutFirstPersonDegree;

    TextView tvAboutSecondPersonName;
    TextView tvAboutSecondPersonDegree;

    TextView tvAboutThirdPersonName;
    TextView tvAboutThirdPersonDegree;

    TextView tvAboutFourthPersonName;
    TextView tvAboutFourthPersonDegree;

    TextView tvAboutFifthPersonName;
    TextView tvAboutFifthPersonDegree;

    TextView tvAboutSixthPersonName;
    TextView tvAboutSixthPersonDegree;

    TextView tvAboutSeventhPersonName;
    TextView tvAboutSeventhPersonDegree;

    TextView tvAboutEighthPersonName;
    TextView tvAboutEighthPersonDegree;



    TextView tvAboutVisionTitle;
    TextView tvAboutVisionText;

    TextView tvOurTeamTitle;

    TextView tvAboutMissionTitle;

    TextView tvOne,tvTwo,tvThree;

    CardView firstCard, secondCard;

    DocumentView documentView;
    private Toolbar toolbar;

    TextViewEx tvAboutText;
    TextViewEx tvAboutVissionText,tvAboutMissionText,tvValuesOne,tvValuesTwo,tvValuesThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.about_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(linearLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });

        firstCard = (CardView) findViewById(R.id.card_view);
        secondCard = (CardView) findViewById(R.id.card_view2);

        tvAboutText = (TextViewEx) findViewById(R.id.tv_about_text);
        tvAboutText.setText(getResources().getString(R.string.about_content),true);

        tvAboutVissionText = (TextViewEx) findViewById(R.id.tvAboutVisionText);
        tvAboutVissionText.setText(getResources().getString(R.string.vision_text),true);

        tvAboutMissionText = (TextViewEx) findViewById(R.id.tvAboutMissionText);
        tvAboutMissionText.setText(getResources().getString(R.string.mission_text),true);

        tvValuesOne = (TextViewEx) findViewById(R.id.tvAboutValuesTextOne);
        tvValuesTwo = (TextViewEx) findViewById(R.id.tvAboutValuesTextTwo);
        tvValuesThree = (TextViewEx) findViewById(R.id.tvAboutValuesTextThree);

        tvValuesOne.setText(getResources().getString(R.string.values_one),true);
        tvValuesTwo.setText(getResources().getString(R.string.values_two),true);
        tvValuesThree.setText(getResources().getString(R.string.values_three),true);


//        YoYo.with(Techniques.StandUp).delay(300).duration(900).playOn(documentView);

//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutFirstPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutFirstPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutSecondPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutSecondPersonDegree);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutThirdPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutThirdPersonDegree);
//
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutFourthPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutFourthPersonDegree);
//
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutFifthPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutFifthPersonDegree);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutSixthPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutSixthPersonDegree);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutSeventhPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutSeventhPersonDegree);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutEighthPersonName);
//
//        YoYo.with(Techniques.Bounce).delay(500).duration(900).playOn(tvAboutEighthPersonDegree);
//
//
//        YoYo.with(Techniques.BounceInDown).delay(300).duration(900).playOn(tvAboutTitle);
    }

    @Override
    public void onBackPressed() {

//        this.finish();
        Intent intent = new Intent(AboutActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }


}
