package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.District;
import in.co.scorp.jovialgroup.models.Records;

/**
 * Created by root on 27/6/15.
 */
public class RecordsParser {

    public JSONObject object;
    public ArrayList<Records> list;

    public int getResponseCode(String data){

        try{

            object = new JSONObject(data);


        }catch (Exception e){

            Log.e("RECORDS RESP Error", e.toString());
        }

        return object.optInt("CODE");
    }

    public ArrayList<Records> getRecordsList(String data){

        list = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                Log.e("RECORDS PARSE nos: ", ""+i);

                Records records = new Records();

                JSONObject dataObject = dataArray.getJSONObject(i);

                records.setRecId(dataObject.optLong(Constants.REC_ID));
                records.setRecDist(dataObject.optInt(Constants.REC_DIST_ID));
                records.setRecTal(dataObject.optInt(Constants.REC_TAL_ID));
                records.setRecCity(dataObject.optInt(Constants.REC_CITY_ID));
                records.setRecfTYpe(dataObject.optString(Constants.REC_FILE_TYPE));
                records.setRecFile(dataObject.optString(Constants.REC_FILE));
                records.setRecDate(dataObject.optString(Constants.REC_DATE));
                records.setRecServiceId(dataObject.optInt(Constants.REC_SERVICE_ID));
                records.setRecServiceName(dataObject.getString(Constants.REC_SERVICE_NAME));
                list.add(records);
            }

            return list;

        }catch (Exception e) {

            Log.e("RECORDS PARSE: ", e.toString());
        }

        return null;
    }
}
