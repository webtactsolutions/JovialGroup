package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Patiya;

/**
 * Created by SCORP on 10/07/15.
 */
public class UserCheckParser {

    ArrayList<Patiya> list;

    JSONObject object;

    public int getResponseCode(String data){

        try {

            object = new JSONObject(data);


        }catch (Exception e){


        }

        return object.optInt("CODE");

    }

}
