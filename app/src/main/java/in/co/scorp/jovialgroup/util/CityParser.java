package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.City;
import in.co.scorp.jovialgroup.models.District;

/**
 * Created by root on 27/6/15.
 */
public class CityParser {

    public JSONObject object;
    public ArrayList<City> list;

    public int getResponseCode(String data){

        try{

            object = new JSONObject(data);


        }catch (Exception e){

            Log.e("city parse Error", e.toString());
        }

        return object.optInt("CODE");
    }

    public ArrayList<City> getCitiesList(String data){

        list = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                City city = new City();

                JSONObject dataObject = dataArray.getJSONObject(i);

                city.setTalId(dataObject.optInt(Constants.TAL_ID));
                city.setCityName(dataObject.optString(Constants.CITY_NAME));
                city.setCityId(dataObject.optInt(Constants.CITY_ID));

                list.add(city);
            }

            return list;

        }catch (Exception e) {

            Log.e("ServiceParse: ", e.toString());
        }

        return null;
    }
}
