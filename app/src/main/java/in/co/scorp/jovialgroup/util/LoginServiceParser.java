package in.co.scorp.jovialgroup.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.co.scorp.jovialgroup.models.LoginWithUser;

/**
 * Created by SCORP on 13/04/15.
 */
public class LoginServiceParser {

    public ArrayList<LoginWithUser> list = new ArrayList<LoginWithUser>();
    int responseCode;
    JSONObject object;

    public int getResponseCode(String data) {

        try {

            object = new JSONObject(data);


        } catch (Exception e) {

            Log.e("LoginParserResp Error", e.toString());
        }

        return object.optInt("CODE");
    }

    public ArrayList<LoginWithUser> parseJson(String data) {

        try {

            JSONObject object = new JSONObject(data);
            JSONArray dataArray = object.getJSONArray("DATA");

            for (int i = 0; i < dataArray.length(); i++) {

                LoginWithUser loginWithUser = new LoginWithUser();

                JSONObject dataObject = dataArray.getJSONObject(i);

                loginWithUser.setUserId(dataObject.optInt("userid"));
                loginWithUser.setUsername(dataObject.optString("username"));
                loginWithUser.setPassword(dataObject.optString("password"));
                loginWithUser.setName(dataObject.optString("name"));
                loginWithUser.setEmail(dataObject.optString("email"));
                loginWithUser.setMobile(dataObject.optString("mobile"));
                loginWithUser.setDob(getDate(dataObject.optString("dob")));
                loginWithUser.setOrderId(dataObject.getString("orderid"));
                loginWithUser.setOrderAmnt(dataObject.getLong("orderamt"));
                loginWithUser.setRole(Integer.parseInt(dataObject.getString("userrole")));
                loginWithUser.setServices(dataObject.optString("services"));
                loginWithUser.setBlockNo(dataObject.optString("blockno"));
                loginWithUser.setMoje(dataObject.optString("moje"));
                loginWithUser.setStatus(dataObject.optString("status"));
                loginWithUser.setDelstatus(dataObject.optString("delstatus"));
                loginWithUser.setRegdate(dataObject.optString("regdate"));
                loginWithUser.setExpDate(dataObject.optString("expdate"));

                list.add(loginWithUser);
            }

        } catch (Exception e) {

            Log.e("ServiceParse: ", e.toString());
        }

        return list;
    }

    public String getDate(String d){

        String year = d.substring(0,4);
        String month = d.substring(5,7);
        String day = d.substring(8,10);

        return day+"-"+month+"-"+year;
    }


}
