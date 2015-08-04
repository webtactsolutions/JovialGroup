package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Patiya;

/**
 * Created by SCORP on 18/04/15.
 */
public class StatusParser {

   // int responseCode;
    ArrayList<Patiya> list;

    JSONObject object;

    public int getStatusCode(String data){

        try {

            object = new JSONObject(data);


        }catch (Exception e){


        }

        return object.optInt("CODE");

    }

    public ArrayList<Patiya> parseJson(String data) {

        try {

            list = new ArrayList<Patiya>();

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                Patiya services = new Patiya();

                JSONObject dataObject = dataArray.getJSONObject(i);

                services.setScid(dataObject.getInt("scid"));
                services.setDate(dataObject.getString("ldate"));
                services.setLcid(dataObject.getString("cid"));

                list.add(services);
            }

            Log.i("Ldate",""+list.size());

        }catch (Exception e) {

            Log.e("Status Parser: ", e.toString());
        }

        return list;
    }
}
