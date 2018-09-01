package be.pxl.simon.babylistious.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import be.pxl.simon.babylistious.utilities.DataUtils;


public class BabyListDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "babylist.db";

    public static final int DATABASE_VERSION = 4;

    public BabyListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_BABYLIST_TABLE =
                "CREATE TABLE " + BabyListContract.BabyListEntry.TABLE_NAME + " (" +
                        BabyListContract.BabyListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        BabyListContract.BabyListEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                        BabyListContract.BabyListEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                        BabyListContract.BabyListEntry.COLUMN_CHECKED + " BOOLEAN NOT NULL, " +
                        BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID + " INTEGER NOT NULL, " +
                        BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK + " TEXT NOT NULL, " +
                        BabyListContract.BabyListEntry.COLUMN_CATEGORY + " INTEGER NOT NULL" + ");";

        db.execSQL(SQL_CREATE_BABYLIST_TABLE);
        Log.d("DB", "CREATE DATABASE");

        DataUtils.insertInitialData(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BabyListContract.BabyListEntry.TABLE_NAME);
        onCreate(db);
    }

}
