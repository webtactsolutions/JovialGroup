package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.District;
import in.co.scorp.jovialgroup.models.Taluka;

/**
 * Created by root on 27/6/15.
 */
public class TalukaAdapter extends BaseAdapter {

    Context context;
    ArrayList<Taluka> list;

    public TalukaAdapter(Context context, ArrayList<Taluka> list){

        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getTalId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(this.context).inflate(R.layout.simple_list_item,null);

        TextView tvTalName = (TextView) convertView.findViewById(R.id.tvNames);

        tvTalName.setText(list.get(position).getTalName());

        return convertView;
    }
}
