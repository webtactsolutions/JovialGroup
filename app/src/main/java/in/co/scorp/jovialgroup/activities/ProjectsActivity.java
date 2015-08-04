package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.adapters.ProjectsListAdapter;

public class ProjectsActivity extends ActionBarActivity {

    public RecyclerView projectList;
    public RecyclerView.LayoutManager layoutManager;
    public ProjectsListAdapter adapter;


    public String[] projectName = {
            "Global Indian International School",
            "Podar International School",
            "Euro School",
            "Sai Drashti Residency",
            "Sai Drashti Residency",
            "Sai Srushti",
            "Sai Srushti",
            "Sai Srushti",
            "Raj Residency",
            "Prayosha Heights",
            "Prayosha Heights",
            "Shanti Kunj",
            "Valak Aashirvad Height",
            "Dreamland Mall",
            "Dreamland Mall",
            "Shubh Villa",
            "Shubh Villa",
            "Shubh Villa",
            "Rhythm Mall",
            "Rhythm Mall",
            "Rhythm Mall"

    };

    public String[] addresses = {

            "Chalthan - Surat, Gujarat, India",
            "Simada - Surat, Gujarat, India",
            "Simada - Surat, Gujarat, India",
            "Olpad - Surat, Gujarat, India",
            "Olpad - Surat, Gujarat, India",
            "Olpad - Surat, Gujarat, India",
            "Olpad - Surat, Gujarat, India",
            "Olpad - Surat, Gujarat, India",
            "Laskana - Surat, Gujarat, India",
            "Pune, Maharashtra, India",
            "Pune, Maharashtra, India",
            "Kamrej - Surat, Gujarat, India",
            "Surat, Gujarat, India",
            "Varachha - Surat, Gujarat, India",
            "Varachha - Surat, Gujarat, India",
            "Near Singanpore - Surat, Gujarat, India",
            "Near Singanpore - Surat, Gujarat, India",
            "Near Singanpore - Surat, Gujarat, India",
            "Mumbai, Maharashtra, India",
            "Mumbai, Maharashtra, India",
            "Mumbai, Maharashtra, India"

    };

    public int[] projectPhotos = {

            R.drawable.global_indian_school,
            R.drawable.podar,
            R.drawable.euro,
            R.drawable.sai_drashti,
            R.drawable.sai_drashti_two,
            R.drawable.sai_srusti,
            R.drawable.sai_srushti_two,
            R.drawable.sai_strusti_three,
            R.drawable.raj_residency,
            R.drawable.prayosha_two,
            R.drawable.prayosha_heights,
            R.drawable.shanti_kunj,
            R.drawable.vaalak,
            R.drawable.dreamland,
            R.drawable.dream_land_two,
            R.drawable.shubh_villa,
            R.drawable.shubh_vill_two,
            R.drawable.shub_villa_three,
            R.drawable.rhythm_mall,
            R.drawable.rythem_mall_two,
            R.drawable.rythem_mall_three,

    };
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        LinearLayout projectsContainer = (LinearLayout) findViewById(R.id.projects_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(projectsContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_projects);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });

        projectList = (RecyclerView) findViewById(R.id.projects_list);
        projectList.setHasFixedSize(true);

        adapter = new ProjectsListAdapter(this,projectName,addresses,projectPhotos);

        projectList.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        projectList.setLayoutManager(layoutManager);
    }


    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(ProjectsActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
