package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.Circular;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 30/06/15.
 */
public class GovCircularAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Circular> list;

    public GovCircularAdapter(Context context, ArrayList<Circular> list){

        this.context = context;
        this.list = list;

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

        view = LayoutInflater.from(this.context).inflate(R.layout.adapter_circular_layout,null);

        ImageView ivDownload = (ImageView) view.findViewById(R.id.btn_download_circ);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_circular_title);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_circular_date);

        if(list.get(i).isDownloaded()==1){

            ivDownload.setVisibility(View.GONE);
        }else if(list.get(i).isDownloaded()==0){

            ivDownload.setVisibility(View.VISIBLE);
        }

        tvTitle.setText(list.get(i).getTitle().toString());
        tvDate.setText(list.get(i).getDate().toString());

        return view;
    }
}
