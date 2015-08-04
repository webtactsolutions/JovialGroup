package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.co.scorp.jovialgroup.R;

/**
 * Created by SCORP on 10/04/15.
 */
public class ServicesListviewAdapter extends BaseAdapter {

    public String[] services;
    public String[] servceDetail;
    public Context context;

    Typeface typefaceTitle;
    Typeface typefaceNormal;


    public ServicesListviewAdapter(Context contexts, String[] services, String[] serviceDetail){

        this.services = services;
        this.servceDetail = serviceDetail;
        this.context = contexts;
        typefaceTitle = Typeface.createFromAsset(this.context.getAssets(), "oswald_regular.ttf");
        typefaceNormal = Typeface.createFromAsset(this.context.getAssets(),"normal.ttf");

    }

    @Override
    public int getCount() {
        return services.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {

        TextView tvTitle,tvDesc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if(view==null) {

            viewHolder = new ViewHolder();

            view = LayoutInflater.from(this.context).inflate(R.layout.services_list_row,null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_service_title);
            viewHolder.tvDesc = (TextView) view.findViewById(R.id.tv_service_desc);

            view.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) view.getTag();

        viewHolder.tvTitle.setText(services[i].toString());
        viewHolder.tvDesc.setText(servceDetail[i].toString());

        return view;
    }
}
