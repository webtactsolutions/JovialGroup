package in.co.scorp.jovialgroup.models;

/**
 * Created by root on 27/6/15.
 */
public class City {

    public int cityId;
    public String cityName;
    public int talId;
    public int distId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getTalId() {
        return talId;
    }

    public void setTalId(int talId) {
        this.talId = talId;
    }

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }
}
