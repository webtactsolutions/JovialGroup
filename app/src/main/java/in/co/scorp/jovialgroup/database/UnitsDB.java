package in.co.scorp.jovialgroup.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import in.co.scorp.jovialgroup.models.UnitConverter;
import in.co.scorp.jovialgroup.util.Constants;

/**
 * Created by root on 12/6/15.
 */
public class UnitsDB {

    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    UnitConverter unitConverter;

    public UnitsDB(Context context){

        dbHelper = new DatabaseHelper(context);

    }

//    public ArrayList<UnitConverter> getUnitNames(){
//
//        ArrayList<UnitConverter> list = new ArrayList<>();
//        db = dbHelper.getReadableDatabase();
//        Cursor c = db.query(Constants.TBL_UNIT,new String[]{Constants.NAME},null,null,null,null,null,null);
//
//
//        unitConverter = new UnitConverter();
//        unitConverter.setUnitName("Sq. Meter");
//        unitConverter.setUnitValue(new BigDecimal(1));
//        unitConverter.setUnitMultiplier(1);
//
//        list.add(unitConverter);
//
//        unitConverter = new UnitConverter();
//        unitConverter.setUnitName("Are");
//        unitConverter.setUnitValue(new BigDecimal(0.01));
//        unitConverter.setUnitMultiplier(1);
//
//        list.add(unitConverter);
//
//        unitConverter = new UnitConverter();
//        unitConverter.setUnitName("Hectare");
//        unitConverter.setUnitValue(new BigDecimal(0.0001));
//        unitConverter.setUnitMultiplier(1);
//
//        list.add(unitConverter);
//
//        unitConverter = new UnitConverter();
//        unitConverter.setUnitName("Sq. Feet");
//        unitConverter.setUnitValue(new BigDecimal(10.76391));
//        unitConverter.setUnitMultiplier(1);
//
//        list.add(unitConverter);
//
//        if(c.moveToFirst()){
//
//            UnitConverter unitConverter = new UnitConverter();
//            unitConverter.setUnitName(c.getString(c.getColumnIndex(Constants.NAME)));
//            list.add(unitConverter);
//        }
//
//        return list;
//    }

    public ArrayList<UnitConverter> getUnits(){

        ArrayList<UnitConverter> list = new ArrayList<>();

        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(Constants.TBL_UNIT,null,null,null,null,null,null,null);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Sqr. Meter");
//        unitConverter.setUnitValue(new BigDecimal(1).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(1);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Are");
//        unitConverter.setUnitValue(new BigDecimal(0.01).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.01);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Hectare");
//        unitConverter.setUnitValue(new BigDecimal(0.0001).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.0001);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Sq. Feet");
//        unitConverter.setUnitValue(new BigDecimal(10.7639).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(10.76391041671);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Sq. Yard");
//        unitConverter.setUnitValue(new BigDecimal(1.196).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(1.195990046301);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Sq. Kilomiter");
//        unitConverter.setUnitValue(new BigDecimal(0).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.000001);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Sq. Inch");
//        unitConverter.setUnitValue(new BigDecimal(1550.0031).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(1550.003100006);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Sq. Mile");
//        unitConverter.setUnitValue(new BigDecimal(0).setScale(6, BigDecimal.ROUND_HALF_EVEN));
//        unitConverter.setUnitMultiplier(Double.parseDouble(new DecimalFormat("#.############").format(3.86102158542e-7)));
        unitConverter.setUnitMultiplier(3.86102158542e-7);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Vigha(16)");
//        unitConverter.setUnitValue(new BigDecimal(0).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.00061711938);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Vigha(23.5)");
//        unitConverter.setUnitValue(new BigDecimal(0).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.0003949564);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Guntha");
//        unitConverter.setUnitValue(new BigDecimal(0.0099).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.00987391016);

        list.add(unitConverter);

        unitConverter = new UnitConverter();
        unitConverter.setUnitName("Acre");
//        unitConverter.setUnitValue(new BigDecimal(0.0002).setScale(6, BigDecimal.ROUND_HALF_EVEN));
        unitConverter.setUnitMultiplier(0.0002471053814672);

        list.add(unitConverter);



        if(c.moveToFirst()){

            UnitConverter unitConverter = new UnitConverter();
            unitConverter.setUnitMultiplier(c.getDouble(c.getColumnIndex(Constants.MULTIPLIER)));
//            unitConverter.setUnitValue(BigDecimal.valueOf(c.getLong(c.getColumnIndex(Constants.VALUE))));
            unitConverter.setUnitName(c.getString(c.getColumnIndex(Constants.NAME)));
            list.add(unitConverter);
        }

        return list;

    }

    public long addUnit(UnitConverter unitConverter){

        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.NAME,unitConverter.getUnitName());
        values.put(Constants.VALUE, unitConverter.getUnitValue());

        return db.insert(Constants.TBL_UNIT,null, values);
    }

}
