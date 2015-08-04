package in.co.scorp.jovialgroup.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import in.co.scorp.jovialgroup.models.ImageSlider;

/**
 * Created by root on 8/6/15.
 */
public class ImageSliderParser {

    JSONObject obj;

    public int getResponseCode (String data) {

        try {

          obj = new JSONObject(data);
          return obj.getInt(Constants.CODE_CAP);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    public int[] getMtocken(String data) throws JSONException {

        int[] tokens = null;

        JSONObject jsonObj = new JSONObject(data);

        JSONArray jsonArray = jsonObj.getJSONArray("DATA");

        for(int i=0;i<jsonArray.length();i++){

            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.has("mtoken")){

                tokens[i] = obj.getInt(Constants.TOCKEN);
            }

        }

        return tokens;

    }

    public ArrayList<ImageSlider> getImageSlider(String data){

        ArrayList<ImageSlider> sliders = null;

        if(getResponseCode(data)!=0) {


        }

        return sliders;
    }

}
