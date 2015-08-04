package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Taluka;

/**
 * Created by root on 27/6/15.
 */
public class TalukaParser {

    public JSONObject object;
    public ArrayList<Taluka> list;

    public int getResponseCode(String data){

        try{

            object = new JSONObject(data);


        }catch (Exception e){

            Log.e("TalukaResp Error", e.toString());
        }

        return object.optInt("CODE");
    }

    public ArrayList<Taluka> getTalukaList(String data){

        list = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                Taluka taluka = new Taluka();

                JSONObject dataObject = dataArray.getJSONObject(i);

                taluka.setDistId(dataObject.optInt(Constants.DIST_ID));
                taluka.setTalId(dataObject.optInt(Constants.TAL_ID));
                taluka.setTalName(dataObject.optString(Constants.TAL_NAME).toString());

                list.add(taluka);
            }

            return list;

        }catch (Exception e) {

            Log.e("TalukaServiceParse: ", e.toString());
        }

        return null;
    }
}
