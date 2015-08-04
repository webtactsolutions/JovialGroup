package in.co.scorp.jovialgroup.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.Circular;
import in.co.scorp.jovialgroup.models.UnitConverter;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 18/07/15.
 */
public class GovCircularDB {


    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    public GovCircularDB(Context context){

        dbHelper = new DatabaseHelper(context);

    }

    public ArrayList<Circular> getCirculars(){

        ArrayList<Circular> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(Constants.TBL_CIRCULAR,null,null,null,null,null,null,null);

        Circular circular;

        if(c.moveToFirst()){

            circular = new Circular();
            circular.setTitle(c.getString(c.getColumnIndex(Constants.CTITLE)));
            circular.setDate(c.getString(c.getColumnIndex(Constants.CDATE)));
            circular.setcDocument(c.getString(c.getColumnIndex(Constants.CDOCUMENT)));
            circular.setId(c.getLong(c.getColumnIndex(Constants._ID)));
            circular.setIsDownloaded(c.getInt(c.getColumnIndex(Constants.IS_DOWNLOADED)));
            circular.setLocalPath(c.getString(c.getColumnIndex(Constants.LOCAL_PATH)));

            list.add(circular);
        }

        return list;
    }


    public boolean isDownloaded(int id){

        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(Constants.TBL_CIRCULAR,null,Constants._ID+"="+id,null,null,null,null,null);

        Circular circular;

        if(c.moveToFirst()){

            if(c.getInt(c.getColumnIndex(Constants.IS_DOWNLOADED))==1){

                return true;
            }else {

                return false;
            }

        }
        else{

            return false;
        }

    }

    public void insertCirculars(ArrayList<Circular> list){

        ArrayList<ContentValues> contentValues = new ArrayList<>();

        ContentValues values;

        for(int i=0;i<list.size();i++){

            values = new ContentValues();

            values.put(Constants.CTITLE,list.get(i).getTitle());
            values.put(Constants.CDATE, list.get(i).getDate());
            values.put(Constants.CDOCUMENT, list.get(i).getcDocument());
            values.put(Constants.IS_DOWNLOADED,0);
            values.put(Constants.LOCAL_PATH,list.get(i).getLocalPath());

            contentValues.add(values);
        }

        db = dbHelper.getWritableDatabase();

        for(int j=0;j<contentValues.size();j++){

            db.insert(Constants.TBL_CIRCULAR,null,contentValues.get(j));
        }

    }

    public long setDownloaded(Circular circular){

        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.CTITLE,circular.getTitle());
        values.put(Constants.CDATE, circular.getDate());
        values.put(Constants.CDOCUMENT, circular.getcDocument());
        values.put(Constants.IS_DOWNLOADED, 1);
        values.put(Constants.LOCAL_PATH, circular.getLocalPath());

        return db.update(Constants.TBL_CIRCULAR,values,Constants._ID+"="+circular.getId(),null);
    }

    public Circular getCircular(int id){

        Circular circular;

        db = dbHelper.getReadableDatabase();

        Cursor c = db.query(Constants.TBL_CIRCULAR, null,Constants._ID+"="+id,null,null,null,null);

        if(c.moveToFirst()){

            circular = new Circular();
            circular.setTitle(c.getString(c.getColumnIndex(Constants.CTITLE)));
            circular.setDate(c.getString(c.getColumnIndex(Constants.CDATE)));
            circular.setcDocument(c.getString(c.getColumnIndex(Constants.CDOCUMENT)));
            circular.setId(c.getLong(c.getColumnIndex(Constants._ID)));
            circular.setIsDownloaded(c.getInt(c.getColumnIndex(Constants.IS_DOWNLOADED)));

            return circular;
        }

        return null;
    }


}
