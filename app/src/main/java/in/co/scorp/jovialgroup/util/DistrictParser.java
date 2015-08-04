package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.District;
import in.co.scorp.jovialgroup.models.LoginWithUser;

/**
 * Created by SCORP on 21/06/15.
 */
public class DistrictParser {

    public JSONObject object;
    public ArrayList<District> list;

    public int getResponseCode(String data){

        try{

            object = new JSONObject(data);


        }catch (Exception e){

            Log.e("LoginParserResp Error", e.toString());
        }

        return object.optInt("CODE");
    }

    public ArrayList<District> getDistricList(String data){

        list = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                District district = new District();

                JSONObject dataObject = dataArray.getJSONObject(i);

                district.setDistId(dataObject.optInt(Constants.DIST_ID));
                district.setDistName(dataObject.optString(Constants.DIST_NAME));
                district.setStateId(dataObject.optInt(Constants.STATE_ID));

                list.add(district);
            }

            return list;

        }catch (Exception e) {

            Log.e("ServiceParse: ", e.toString());
        }

        return null;
    }
}
