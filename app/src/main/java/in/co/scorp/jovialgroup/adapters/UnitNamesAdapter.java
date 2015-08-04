package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.UnitConverter;

/**
 * Created by root on 12/6/15.
 */
public class UnitNamesAdapter extends BaseAdapter {

    Context context;
    ArrayList<UnitConverter> list;

    public UnitNamesAdapter(Context context, ArrayList<UnitConverter> list){

        this.context = context;
        this.list = list;
        Log.i("value in adapter", "" + list.get(2).getUnitValue());
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(this.context).inflate(R.layout.simple_list_item,null);

        convertView.setFitsSystemWindows(true);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvNames);
        tvName.setText(list.get(position).getUnitName());

        return convertView;
    }
}
