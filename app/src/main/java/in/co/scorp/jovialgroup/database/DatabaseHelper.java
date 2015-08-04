package in.co.scorp.jovialgroup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by SCORP on 12/04/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="jovial.db";
    public static final int DB_VERSION = 6;
    public static final String CREATE_UNIT_TABLE = "CREATE TABLE "+ Constants.TBL_UNIT+" ("+
            Constants._ID+" INTEGER PRIMARY KEY,"+
            Constants.NAME+ " TEXT, "+
            Constants.VALUE+ " REAL"+
            ")";
    public static final String CREATE_CIRCULAR_TABLE = "CREATE TABLE "+ Constants.TBL_CIRCULAR+" ("+
            Constants._ID+" INTEGER PRIMARY KEY,"+
            Constants.CTITLE+ " TEXT, "+
            Constants.CDOCUMENT+ " REAL, "+
            Constants.CDATE+" TEXT, "+
            Constants.IS_DOWNLOADED+" INTEGER,"+
            Constants.LOCAL_PATH+" TEXT"+
            ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_UNIT_TABLE);
        sqLiteDatabase.execSQL(CREATE_CIRCULAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TBL_UNIT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TBL_CIRCULAR);

        onCreate(sqLiteDatabase);
    }
}
