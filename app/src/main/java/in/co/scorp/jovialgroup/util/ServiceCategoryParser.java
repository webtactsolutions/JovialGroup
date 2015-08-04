package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.ServiceCategories;

/**
 * Created by SCORP on 17/04/15.
 */
public class ServiceCategoryParser {

    int responseCode;
    ArrayList<ServiceCategories> list = new ArrayList<ServiceCategories>();

    public ArrayList<ServiceCategories> parseJson(String data) {

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                ServiceCategories services = new ServiceCategories();

                JSONObject dataObject = dataArray.getJSONObject(i);
                services.setcStatus(dataObject.optString("cstatus"));
                services.setCid(dataObject.optString("cid"));

                list.add(services);
            }

        }catch (Exception e) {

            Log.e("ServiceParse: ", e.toString());
        }

        return list;
    }
}
