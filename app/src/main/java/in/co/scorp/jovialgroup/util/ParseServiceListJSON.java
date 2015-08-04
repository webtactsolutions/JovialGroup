package in.co.scorp.jovialgroup.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Services;

/**
 * Created by SCORP on 13/04/15.
 */
public class ParseServiceListJSON {

    int responseCode;
    ArrayList<Services> list = new ArrayList<Services>();


    public ArrayList<Services> parseJson(String data,Context c) {

        String blockStr =c.getSharedPreferences(Constants.APP_PREFERENCE_NAME, c.MODE_PRIVATE).getString(Constants.BLOCK_NO,"");
        String mojeStr =c.getSharedPreferences(Constants.APP_PREFERENCE_NAME, c.MODE_PRIVATE).getString(Constants.MOJE,"");

        String[] blockArr = blockStr.replace(","," ,").split(",");
        String[] mojeArr = mojeStr.replace(","," ,").split(",");

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                Services services = new Services();

                JSONObject dataObject = dataArray.getJSONObject(i);
                services.setId(dataObject.optInt("sid"));
                services.setName(dataObject.optString("servicename"));
                services.setMoje(mojeArr[i].toString());
                services.setBlockNo(blockArr[i].toString());

                list.add(services);
            }

        }catch (Exception e) {

            Log.e("ServiceParse: ",e.toString());
        }

        return list;
    }




}
