package in.co.scorp.jovialgroup.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.fragments.ProfileFragment;
import in.co.scorp.jovialgroup.util.Constants;

public class MyAccount extends ActionBarActivity {

    Toolbar toolbar;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        toolbar = (Toolbar) findViewById(R.id.toolbar_myacount);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        pref = getSharedPreferences(Constants.APP_PREFERENCE_NAME, MODE_PRIVATE);

        if(pref.getInt(Constants.UROLE,0)==3||pref.getInt(Constants.UROLE,0)==1||pref.getInt(Constants.UROLE,0)==2){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.myaccount_container, new ProfileFragment()).commit();

        }
        else{

            AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
            builder.setMessage("You are not Paid Member of Jovial Group. Please Register with Jovial Group");
            builder.setPositiveButton("Register Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                    Intent intent = new Intent(MyAccount.this, RegistrationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                    onBackPressed();

                }
            });
            builder.show();
        }

    }


    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(MyAccount.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
