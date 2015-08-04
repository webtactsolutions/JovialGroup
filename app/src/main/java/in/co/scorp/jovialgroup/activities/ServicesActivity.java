package in.co.scorp.jovialgroup.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.co.scorp.jovialgroup.MainActivity;
import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.adapters.ServicesListviewAdapter;

public class ServicesActivity extends ActionBarActivity {

    Typeface typefaceTitle;
    Typeface typefaceNormal;

    ListView lvServicesListView;
    ServicesListviewAdapter adapter;

    TextView tvServicesTitle;
    String[] servicesTitle = {

            "Portability Report",
            "Land Choice",
            "Preparation of Concepts",
            "Serve with Contours",
            "All types of Planning",
            "Project Management",
            "Non Agriculture Process",
            "Tenure Change Work",
            "All types of Construction Work",
            "Structural Design & Engineering",
            "Quantity Surveying & Estimation",
            "Interior Designing & Decoration",
            "Landscaping & Site Development Design",
            "Property Valuation",
            "Legal Advisor of Revenue",
            "Town Planning and Development Plan",
            "Soil and Concrete Testing"
    };

    String[] serviceDesc = {

            "Considering all the aspects related to the Jovial Group project, we are able to achieve success in your project by giving complete details with report.",
            "Jovial Group will help you find land according to your project and according to government rules. We are expert in choosing/finding land related to your project by keeping in mind the location, locality and future development.",
            "Jovial Group has vast experience in developing your project according to your needs with international standards, which will stand different in the world.",
            "Jovial Group has vast experience in doing survey with minuteness and according to your project level.",
            "Jovial Group does planning related to residential, industrial, commercial and educational projects.",
            "Jovial Group helps you in your project and will be there till the end of your project and also give information related to government permission like Plan Pass, N.A., B.C.U., O.C., Structure Stability, Completion Certificate from the government office.",
            "Jovial group has expertise in process of converting Agriculture Land to Non-Agriculture Land in short time and quick from related government office according to your project.",
            "Jovial Group is associated with work of converting old rules to new rules of land in short time.",
            "Jovial Group has vast experience of residential, commercial, industrial and educational construction and all the above facilities are available here.",
            "Jovial Group has proficient experience in providing facility of structural design for residential, commercial, industrial and educational construction.",
            "Jovial Group does survey of your project in all aspects and provide you the estimate expense.",
            "Jovial Group has proficient experience in providing facility of interior designing for residential, commercial, industrial and educational needs.",
            "Jovial Group has proficient experience in providing facility of landscape design and site development for residential, commercial, industrial and educational needs.",
            "Jovial Group helps in providing valuation of your residential, commercial, industrial and educational property in reference to government approval.",
            "Jovial Group is a legal advisor for your project which includes paper work like deeds, partnership agreement, construction agreement and consideration agreement.",
            "Jovial Group has facility to develop Town Planning (T.P.) and Development Plan (D.P.) according to Development Planning Act 1976.",
            "Jovial Group has facility available for all types of soil testing and concrete testing."
    };
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        LinearLayout serviceContainer = (LinearLayout) findViewById(R.id.services_container);
        YoYo.with(Techniques.RollIn).duration(800).playOn(serviceContainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar_services);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();
                onBackPressed();

            }
        });

        typefaceTitle = Typeface.createFromAsset(this.getAssets(), "oswald_regular.ttf");
        typefaceNormal = Typeface.createFromAsset(this.getAssets(),"normal.ttf");



        lvServicesListView = (ListView) findViewById(R.id.servicesListView);

        adapter = new ServicesListviewAdapter(this,servicesTitle,serviceDesc);

        lvServicesListView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(ServicesActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
