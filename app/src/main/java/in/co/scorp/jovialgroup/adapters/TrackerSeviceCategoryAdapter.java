package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.Patiya;
import in.co.scorp.jovialgroup.models.ServiceCategories;

/**
 * Created by SCORP on 17/04/15.
 */
public class TrackerSeviceCategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Patiya> licenseList;
    ArrayList<ServiceCategories> boxList;
    Typeface typefaceTitle;
    Typeface typefaceNormal;

    public TrackerSeviceCategoryAdapter(Context context,ArrayList<ServiceCategories> serviceCategoriesList, ArrayList<Patiya> list) {

        this.context = context;
        this.licenseList = list;
        this.boxList = serviceCategoriesList;

    }

    @Override
    public int getCount() {
        return boxList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return Integer.parseInt(boxList.get(i).getCid());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if(view==null) {

            viewHolder = new ViewHolder();

            typefaceTitle = Typeface.createFromAsset(this.context.getAssets(), "oswald_regular.ttf");
            typefaceNormal = Typeface.createFromAsset(this.context.getAssets(),"normal.ttf");

            view = LayoutInflater.from(this.context).inflate(R.layout.tracker_service_status_list_item,null);

            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_service_category_title);
            viewHolder.tvDateOne = (TextView) view.findViewById(R.id.tv_step_one_date);
            viewHolder.tvDateTwo = (TextView) view.findViewById(R.id.tv_step_two_date);
            viewHolder.tvDateThree = (TextView) view.findViewById(R.id.tv_step_three_date);
            viewHolder.ivStatucIconOne = (ImageView) view.findViewById(R.id.iv_step_icon_one);
            viewHolder.ivStatucIconTwo = (ImageView) view.findViewById(R.id.iv_step_icon_two);
            viewHolder.ivStatucIconThree = (ImageView) view.findViewById(R.id.iv_step_icon_three);

            viewHolder.tvFirstTitle = (TextView) view.findViewById(R.id.tv_step_one_title);
            viewHolder.tvSecondTitle = (TextView) view.findViewById(R.id.tv_step_two_title);
            viewHolder.tvThirdTitle = (TextView) view.findViewById(R.id.tv_step_three_title);


            view.setTag(viewHolder);


        }



        viewHolder = (ViewHolder) view.getTag();

        viewHolder.ivStatucIconOne.setImageResource(R.drawable.ic_ideal_mode);
        viewHolder.tvDateOne.setText("");
        viewHolder.tvDateOne.setVisibility(View.INVISIBLE);

        viewHolder.ivStatucIconTwo.setImageResource(R.drawable.ic_ideal_mode);
        viewHolder.tvDateTwo.setText("");
        viewHolder.tvDateTwo.setVisibility(View.INVISIBLE);

        viewHolder.ivStatucIconThree.setImageResource(R.drawable.ic_ideal_mode);
        viewHolder.tvDateThree.setText("");
        viewHolder.tvDateThree.setVisibility(View.INVISIBLE);

        viewHolder.tvName.setText("" + boxList.get(i).getcStatus());

        String s = boxList.get(i).getcStatus().toString();
        Log.i("modified",s.substring(0,1));

        int cid = Integer.parseInt(boxList.get(i).getCid());

        for(int j=0;j<licenseList.size();j++){

            if(cid==licenseList.get(j).getScid()) {

                String lcid = licenseList.get(j).getLcid();
                String subLcid = lcid.substring(0,1);

                if(subLcid.equals("q")){

                    viewHolder.tvDateOne.setVisibility(View.VISIBLE);

                    viewHolder.tvDateOne.setText(getDate(licenseList.get(j).getDate().substring(0,10)));
                    viewHolder.ivStatucIconOne.setImageResource(R.drawable.ic_queue_orange);
                }
               else if(subLcid.equals("p")){

                    viewHolder.tvDateTwo.setVisibility(View.VISIBLE);
                    viewHolder.tvDateTwo.setText(getDate(licenseList.get(j).getDate().substring(0,10)));
                    viewHolder.ivStatucIconTwo.setImageResource(R.drawable.ic_inprocess);
                }
                else if(subLcid.equals("c")){

                    viewHolder.tvDateThree.setVisibility(View.VISIBLE);
                    viewHolder.tvDateThree.setText(getDate(licenseList.get(j).getDate().substring(0,10)));
                    viewHolder.ivStatucIconThree.setImageResource(R.drawable.ic_completed);
                }
            }
        }





        return view;
    }

    public String getDate(String d){

        String year = d.substring(0,4);
        String month = d.substring(5,7);
        String day = d.substring(8,10);

        return day+"-"+month+"-"+year;
    }

    public static class ViewHolder {

        TextView tvName;
        ImageView ivStatucIconOne;
        ImageView ivStatucIconTwo;
        ImageView ivStatucIconThree;
        TextView tvDateOne;
        TextView tvDateTwo;
        TextView tvDateThree;
        TextView tvFirstTitle;
        TextView tvSecondTitle;
        TextView tvThirdTitle;
    }


}
