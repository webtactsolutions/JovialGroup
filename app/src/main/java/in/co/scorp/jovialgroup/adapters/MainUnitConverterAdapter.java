package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.UnitConverter;

/**
 * Created by root on 12/6/15.
 */
public class MainUnitConverterAdapter extends BaseAdapter {

    Context context;
    ArrayList<UnitConverter> list;
    Typeface typefaceNormal;
    Typeface typefaceBold;
    double multiplier;
    double baseMultiplier;

    public MainUnitConverterAdapter(Context context, ArrayList<UnitConverter> list,double baseMultiplier ,double multiplier){

        this.context = context;
        this.list = list;
        this.multiplier = multiplier;
        this.baseMultiplier = baseMultiplier;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(this.context).inflate(R.layout.main_unit_converter_list_item, null);

        TextView tvUnitName, tvUnitValue;
        typefaceNormal = Typeface.createFromAsset(context.getAssets(), "normal.ttf");

        tvUnitName = (TextView) convertView.findViewById(R.id.tv_unit_name);
        tvUnitName.setTypeface(typefaceNormal);
        tvUnitValue = (TextView) convertView.findViewById(R.id.tv_unit_value);
        tvUnitValue.setTypeface(typefaceNormal);

        tvUnitName.setText(list.get(position).getUnitName());
        double value = list.get(position).getUnitMultiplier()/this.baseMultiplier;
        DecimalFormat decimalFormat=new DecimalFormat("#.####");
        tvUnitValue.setText(""+decimalFormat.format((value*this.multiplier)));

        return convertView;
    }
}
