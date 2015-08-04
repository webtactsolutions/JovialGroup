package in.co.scorp.jovialgroup.models;

/**
 * Created by SCORP on 13/04/15.
 */
public class Services {

    private int id;
    private String name;

    public String getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(String blockNo) {
        this.blockNo = blockNo;
    }

    public String getMoje() {
        return moje;
    }

    public void setMoje(String moje) {
        this.moje = moje;
    }

    private String blockNo;
    private String moje;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
