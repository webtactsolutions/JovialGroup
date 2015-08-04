package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.Msgs;

/**
 * Created by SCORP on 12/04/15.
 */
public class MsgBoardAdapter extends BaseAdapter {

    Typeface typefaceTitle;
    Typeface typefaceNormal;
    private Context context;
    private ArrayList<Msgs> msgList;

    public MsgBoardAdapter(Context context,ArrayList<Msgs> msgList) {

        this.context =context;
        this.msgList = msgList;


    }


    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int i) {
        return msgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {

        public TextView tvMsg,tvDate;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view==null){

            holder = new ViewHolder();
            view = LayoutInflater.from(this.context).inflate(R.layout.msg_board_single_item,null);
            holder.tvMsg = (TextView) view.findViewById(R.id.tv_msg_board_msg);
            holder.tvDate = (TextView) view.findViewById(R.id.tv_msg_board_date);

            view.setTag(holder);

        }

        holder = (ViewHolder) view.getTag();

        holder.tvMsg.setText(msgList.get(i).getMsg().toString());
        holder.tvDate.setText(getDate(msgList.get(i).getDate().toString().substring(0, 10)) + " " + msgList.get(i).getDate().toString().substring(11, 19));

        return view;
    }

    public String getDate(String d){

        String year = d.substring(0,4);
        String month = d.substring(5,7);
        String day = d.substring(8,10);

        return day+"-"+month+"-"+year;
    }

}
