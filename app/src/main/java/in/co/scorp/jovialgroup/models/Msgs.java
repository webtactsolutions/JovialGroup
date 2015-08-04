package in.co.scorp.jovialgroup.models;

/**
 * Created by SCORP on 12/04/15.
 */
public class Msgs {

    public String id;
    public String msg;
    public String date;

    public String getMsg() {
        return msg;
    }

    public void setId(String id) { this.id = id;}

    public String getId(){ return id; }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
