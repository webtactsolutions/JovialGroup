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
import in.co.scorp.jovialgroup.models.Services;

/**
 * Created by SCORP on 17/04/15.
 */
public class TrackerServisesListAdapter extends BaseAdapter {

    public Context context;

    Typeface typefaceTitle;

    ArrayList<Services> list;


    public TrackerServisesListAdapter(Context context,ArrayList<Services> list) {

        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if(view==null) {

            viewHolder = new ViewHolder();
            typefaceTitle = Typeface.createFromAsset(this.context.getAssets(), "oswald_regular.ttf");
            view = LayoutInflater.from(this.context).inflate(R.layout.tracker_services_list_item,null);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_tracker_services_list_item_name);
            view.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) view.getTag();

        viewHolder.tvName.setText(""+list.get(i).getName());

        return view;
    }

    public static class ViewHolder {

        TextView tvName;

    }
}
