package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.District;

/**
 * Created by SCORP on 21/06/15.
 */
public class DistListAdapter extends BaseAdapter {

    Context context;
    ArrayList<District> list;

    public DistListAdapter(Context context, ArrayList<District> list){

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
        return list.get(position).getDistId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.simple_list_item,null);

        TextView tvDistName = (TextView) convertView.findViewById(R.id.tvNames);

        tvDistName.setText(list.get(position).getDistName());

        return convertView;
    }
}
