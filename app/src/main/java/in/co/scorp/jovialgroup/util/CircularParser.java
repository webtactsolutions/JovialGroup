package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Circular;
import in.co.scorp.jovialgroup.models.District;

/**
 * Created by SCORP on 30/06/15.
 */
public class CircularParser {

    public JSONObject object;
    public ArrayList<Circular> list;

    public int getResponseCode(String data){

        try{

            object = new JSONObject(data);


        }catch (Exception e){

            Log.e("Circular Error", e.toString());
        }

        return object.optInt("CODE");
    }

    public ArrayList<Circular> getCircularList(String data){

        list = new ArrayList<>();

        Log.i("parserdata",data);

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                Circular circular = new Circular();

                JSONObject dataObject = dataArray.getJSONObject(i);

                circular.setId(dataObject.optInt(Constants.CID));
                circular.setTitle(dataObject.optString(Constants.CTITLE));
                circular.setDate(getDate(dataObject.optString(Constants.CDATE)));
                circular.setcDocument(dataObject.optString(Constants.CDOCUMENT));
                circular.setIsDownloaded(0);
                list.add(circular);
            }

            return list;

        }catch (Exception e) {

            Log.e("CircularParse: ", e.toString());
        }

        return null;
    }

    public String getDate(String d){

        String year = d.substring(0,4);
        String month = d.substring(5,7);
        String day = d.substring(8,10);

        return day+"-"+month+"-"+year;
    }
}
