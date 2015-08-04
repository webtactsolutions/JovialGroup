package in.co.scorp.jovialgroup.models;

/**
 * Created by SCORP on 21/06/15.
 */
public class District {

    public int distId;
    public String distName;
    public int stateId;

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
}
