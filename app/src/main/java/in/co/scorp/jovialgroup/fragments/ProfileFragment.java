package in.co.scorp.jovialgroup.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.services.LoginIntentService;
import in.co.scorp.jovialgroup.services.UpGradeServices;
import in.co.scorp.jovialgroup.util.ConnectionDetector;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 07/07/15.
 */
public class ProfileFragment extends Fragment {

    Typeface tfNormal;
    TextView tvName, tvEmail, tvDob, tvPhone, tvUname, tvEndDate, tvOrderId, tvAmnt, tvMemberType;
    LinearLayout upgradeLayout;
    Button btnUpgrade;
    SharedPreferences preferences;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        preferences = getActivity().getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        upgradeLayout = (LinearLayout) v.findViewById(R.id.upgaradeLayout);


        if (preferences.getInt(Constants.UROLE, 0)==1){

            upgradeLayout.setVisibility(View.VISIBLE);
            btnUpgrade = (Button) v.findViewById(R.id.btn_upgrade);
            btnUpgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(ConnectionDetector.isNetworkAvailable(getActivity())==false){

                        Toast.makeText(getActivity(), "Network not Available", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setMessage("Fees Amount is Rs.1500");
                        dialog.setCancelable(false);

                        dialog.setPositiveButton("CONFORM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        dialog.show();


//                        dialog = new ProgressDialog(getActivity());
//                        dialog.setMessage("Loading..");
//                        dialog.setCancelable(false);
//                        dialog.show();

//                        Intent intent = new Intent(getActivity(), UpGradeServices.class);
//                        intent.putExtra(Constants.ACTION,"registration");
//                        intent.putExtra(Constants.REGUSER, preferences.getString(Constants.USERNAME,""));
//                        getActivity().startService(intent);

                    }
                }
            });

        }else{

            upgradeLayout.setVisibility(View.GONE);
        }

        tfNormal = Typeface.createFromAsset(getActivity().getAssets(), "normal.ttf");


        tvUname = (TextView) v.findViewById(R.id.tv_profile_uname);
        tvUname.setText(""+preferences.getString(Constants.USERNAME,"").toString());
        tvUname.setTypeface(tfNormal);

        tvDob = (TextView) v.findViewById(R.id.tv_profile_dob);
        tvDob.setText("" + preferences.getString(Constants.DOB, ""));
        tvDob.setTypeface(tfNormal);

        tvEmail = (TextView) v.findViewById(R.id.tv_profile_email);
        tvEmail.setText("" + preferences.getString(Constants.EMAIL, ""));
        tvEmail.setTypeface(tfNormal);

        tvName = (TextView) v.findViewById(R.id.tv_profile_name);
        tvName.setText("" + preferences.getString(Constants.NAME, ""));
        tvName.setTypeface(tfNormal);

        tvPhone = (TextView) v.findViewById(R.id.tv_profile_phone);
        tvPhone.setText("" + preferences.getString(Constants.PHONE, ""));
        tvPhone.setTypeface(tfNormal);

        tvMemberType = (TextView) v.findViewById(R.id.tv_profile_member_type);
        tvMemberType.setText("" + getRoleType(preferences.getInt(Constants.UROLE, 0)));
        tvMemberType.setTypeface(tfNormal);

        return v;
    }

    public String getDate(String date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
            return d.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getRoleType(int i){

        if(i==3){

            return "Platinum";
        }else if(i==2){

            return "Silver";
        }
        else if(i==1){

            return "Gold";
        }

        return "";
    }

    public class Upgraded extends BroadcastReceiver{

        public static final String ACTION_USER_UPGRADED = "in.co.scorp.upgraded";

        @Override
        public void onReceive(Context context, Intent intent) {

            dialog.dismiss();

        }
    }
}
