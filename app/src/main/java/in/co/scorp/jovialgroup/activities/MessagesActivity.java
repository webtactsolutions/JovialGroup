package in.co.scorp.jovialgroup.activities;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.adapters.MsgBoardAdapter;
import in.co.scorp.jovialgroup.models.Msgs;
import in.co.scorp.jovialgroup.services.GetMessagesService;
import in.co.scorp.jovialgroup.util.ConnectionDetector;
import in.co.scorp.jovialgroup.util.Constants;
import in.co.scorp.jovialgroup.util.MsgsParser;

public class MessagesActivity extends ActionBarActivity {

    SharedPreferences preferences;
    public ListView lvMsgBoard;
    public MsgBoardAdapter adapter;
    public ArrayList<Msgs> msgList;
    public MsgsParser parser;
    public  ProgressDialog dialogMsg;
    TextView tvNoMsg;

    Typeface typefaceTitle;
    Toolbar toolbar;
    Typeface typefaceNormal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        IntentFilter filter = new IntentFilter(MsgsRecievedBroadCast.ACTION_MESSAGE_RECEIVED);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(new MsgsRecievedBroadCast(), filter);

        IntentFilter msgFilter = new IntentFilter(MessageRecievedBroadcastReceiver.ACTION_MESSAGE_RECEIVE);
        msgFilter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(new MessageRecievedBroadcastReceiver(), msgFilter);


        YoYo.with(Techniques.RollIn).duration(500).playOn((LinearLayout)findViewById(R.id.msg_container));

        toolbar = (Toolbar) findViewById(R.id.toolbar_tracker);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();
//                Intent intent = new Intent(MessagesActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

        preferences = this.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        lvMsgBoard = (ListView) findViewById(R.id.lv_msg_board);
        tvNoMsg = (TextView) findViewById(R.id.tv_no_message);


        if(preferences.getBoolean(Constants.USER_LOGIN_STATUS,false)==true){

            if(ConnectionDetector.isNetworkAvailable(this)==true){


                Log.i("user id", preferences.getString(Constants.USERID, ""));

                Intent intent = new Intent(this, GetMessagesService.class);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(Constants.ACTION,"messageboard");
                intent.putExtra(Constants.USERID,preferences.getString(Constants.USERID,""));
                this.startService(intent);



                //Toast.makeText(getActivity(),"In Board",Toast.LENGTH_SHORT).show();

                dialogMsg = new ProgressDialog(this);
                dialogMsg.setMessage("Loading...");
                dialogMsg.setCancelable(false);
                dialogMsg.show();

            }
            else {

                Toast.makeText(this, "Network is not Available", Toast.LENGTH_SHORT).show();

            }

        }
        else {

            Toast.makeText(this,"Please Login To see Messageboard",Toast.LENGTH_LONG).show();
            onBackPressed();
        }



    }

    public String getDate(String d){

        String year = d.substring(0,4);
        String month = d.substring(5,7);
        String day = d.substring(8,10);

        return day+"-"+month+"-"+year;
    }

    public void setOnclickListner(){

        lvMsgBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Msgs msg = (Msgs) adapterView.getItemAtPosition(i);

                final AlertDialog.Builder dialog = new AlertDialog.Builder(MessagesActivity.this);

                View v = LayoutInflater.from(MessagesActivity.this).inflate(R.layout.message_detail, null);
                dialog.setView(v);
                dialog.setCancelable(false);
                TextView text = (TextView) v.findViewById(R.id.tv_detailed_msg);
                text.setText(msg.getMsg());
                typefaceTitle = Typeface.createFromAsset(MessagesActivity.this.getAssets(), "oswald_regular.ttf");
                typefaceNormal = Typeface.createFromAsset(MessagesActivity.this.getAssets(), "normal.ttf");

                TextView tvDate = (TextView) v.findViewById(R.id.tv_detailed_msg_date);

                tvDate.setText(getDate(msg.getDate().toString().substring(0, 10)) + " " + msg.getDate().toString().substring(11, 19));
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                dialog.create().show();


            }
        });
    }


    public class MsgsRecievedBroadCast extends BroadcastReceiver {

        public static final String ACTION_MESSAGE_RECEIVED = "in.co.scorp.jovial.message_recieved";


        @Override
        public void onReceive(final Context context, Intent intent) {

            dialogMsg.dismiss();

            parser = new MsgsParser();
            msgList = parser.parseJson(intent.getStringExtra(Constants.RESPONSE));

            if(msgList.size()==0){

                tvNoMsg.setVisibility(View.VISIBLE);
                tvNoMsg.setText("No Messages");
                lvMsgBoard.setVisibility(View.GONE);
            }
            else {

                tvNoMsg.setVisibility(View.GONE);
                tvNoMsg.setText("");
                lvMsgBoard.setVisibility(View.VISIBLE);

                adapter = new MsgBoardAdapter(MessagesActivity.this, msgList);

                lvMsgBoard.setAdapter(adapter);

                setOnclickListner();
            }
        }


    }


    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(MessagesActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public class MessageRecievedBroadcastReceiver extends BroadcastReceiver {

        public static final String ACTION_MESSAGE_RECEIVE = "in.co.scorp.jovialgroup.message_recieved";

        public static final int NOTIFICATION_ID = 1;
        private NotificationManager notificationManager;

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("msg in reciever",""+intent.getExtras().getString("message"));

            sendNotification(intent.getExtras());

            Bundle bundle = intent.getExtras();
            Log.i("msg in reciever",""+bundle.getString("message"));

            Msgs msgs = new Msgs();
            msgs.setMsg(bundle.getString("message"));

            msgList.add(msgs);

            adapter.notifyDataSetChanged();

            setOnclickListner();



        }

        public void sendNotification(Bundle bundle) {

            notificationManager = (NotificationManager) MessagesActivity.this
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    MessagesActivity.this, 0, new Intent(MessagesActivity.this,
                            MessagesActivity.class), 0);

            Uri alarmSound = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    MessagesActivity.this);

            mBuilder.setAutoCancel(true);
            mBuilder.setSmallIcon(R.drawable.noti);
            mBuilder.setSound(alarmSound);
            mBuilder.setContentTitle("Jovial Group");
            mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(bundle.getString("message")));

            mBuilder.setContentText(bundle.getString("message"));

            mBuilder.setContentIntent(pendingIntent);

            notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }

    }
}
