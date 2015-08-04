package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Msgs;

/**
 * Created by SCORP on 14/04/15.
 */
public class MsgsParser {

    int responseCode;
    ArrayList<Msgs> list = new ArrayList<Msgs>();
    JSONObject object;

    public int getResponseCode(String data){

        try {
             object = new JSONObject(data);

        }catch (Exception e) {

            Log.e("ServiceParse: RESPONSE", e.toString());
        }

        return object.optInt("CODE");

    }


    public ArrayList<Msgs> parseJson(String data) {

        try {

            JSONObject object = new JSONObject(data);

                JSONArray dataArray = object.getJSONArray("DATA");

                for (int i = 0; i < dataArray.length(); i++) {

                    Msgs msgs = new Msgs();

                    JSONObject dataObject = dataArray.getJSONObject(i);
                    msgs.setId(dataObject.optString("msgid"));
                    msgs.setMsg(dataObject.optString("msg"));
                    msgs.setDate(dataObject.optString("msgdate"));

                    list.add(msgs);
                }

        } catch (Exception e) {

            Log.e("ServiceParse: ", e.toString());
        }

        return list;
    }
}
