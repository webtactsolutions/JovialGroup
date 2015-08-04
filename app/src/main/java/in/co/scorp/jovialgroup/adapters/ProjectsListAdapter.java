package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.R;

/**
 * Created by SCORP on 11/04/15.
 */
public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ViewHolder> {

    Context context;
    String[] names;
    int[] photos;
    String[] address;

    public ProjectsListAdapter(Context contexts,String[] names,String[] address, int[] photos) {

        this.names = names;
        this.photos = photos;
        this.address = address;
        this.context = contexts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.projects_row_item,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvName.setText(names[i].toString());
        viewHolder.tvAddress.setText(address[i].toString());
        viewHolder.ivPhotos.setImageResource(photos[i]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvAddress;
        public ImageView ivPhotos;
        Typeface typefaceTitle;
        Typeface typefaceNormal;

        public ViewHolder(View itemView) {
            super(itemView);

            typefaceTitle = Typeface.createFromAsset(itemView.getContext().getAssets(), "oswald_regular.ttf");
            typefaceNormal = Typeface.createFromAsset(itemView.getContext().getAssets(),"normal.ttf");


            ivPhotos = (ImageView) itemView.findViewById(R.id.ivProjectPhoto);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);

            YoYo.with(Techniques.FlipInX).duration(1000).playOn(itemView);

        }
    }
}
