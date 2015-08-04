package in.co.scorp.jovialgroup.models;

/**
 * Created by root on 27/6/15.
 */
public class Records {

    public long recId;
    public String recDate;
    public String recfTYpe;
    public String recFile;
    public int recCity;
    public int recDist;
    public int recTal;
    public int recServiceId;
    public String recServiceName;
    public String filePath;
    public String localPath;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getRecId() {
        return recId;
    }

    public void setRecId(long recId) {
        this.recId = recId;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public String getRecfTYpe() {
        return recfTYpe;
    }

    public void setRecfTYpe(String recfTYpe) {
        this.recfTYpe = recfTYpe;
    }

    public String getRecFile() {
        return recFile;
    }

    public void setRecFile(String recFile) {
        this.recFile = recFile;
    }

    public int getRecCity() {
        return recCity;
    }

    public void setRecCity(int recCity) {
        this.recCity = recCity;
    }

    public int getRecDist() {
        return recDist;
    }

    public void setRecDist(int recDist) {
        this.recDist = recDist;
    }

    public int getRecTal() {
        return recTal;
    }

    public void setRecTal(int recTal) {
        this.recTal = recTal;
    }

    public int getRecServiceId() {
        return recServiceId;
    }

    public void setRecServiceId(int recServiceId) {
        this.recServiceId = recServiceId;
    }

    public String getRecServiceName() {
        return recServiceName;
    }

    public void setRecServiceName(String recServiceName) {
        this.recServiceName = recServiceName;
    }
}
