package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.City;
import in.co.scorp.jovialgroup.models.District;

/**
 * Created by root on 27/6/15.
 */
public class CityAdapter extends BaseAdapter {

    Context context;
    ArrayList<City> list;

    public CityAdapter(Context context, ArrayList<City> list) {

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
        return list.get(position).getCityId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.simple_list_item,null);

        TextView tvCityName = (TextView) convertView.findViewById(R.id.tvNames);

        tvCityName.setText(list.get(position).getCityName());

        return convertView;
    }
}
