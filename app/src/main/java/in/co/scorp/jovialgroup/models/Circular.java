package in.co.scorp.jovialgroup.models;

/**
 * Created by SCORP on 30/06/15.
 */
public class Circular {

    public long id;
    public String title;
    public String date;
    public String cDocument;
    public int isDownloaded;
    public String localPath;

    public int getIsDownloaded() {
        return isDownloaded;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public int isDownloaded() {
        return isDownloaded;
    }

    public void setIsDownloaded(int isDownloaded) {
        this.isDownloaded = isDownloaded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getcDocument() {
        return cDocument;
    }

    public void setcDocument(String cDocument) {
        this.cDocument = cDocument;
    }
}
