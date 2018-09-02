package be.pxl.simon.babylistious.utilities;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.pxl.simon.babylistious.data.BabyListContract;

public class DataUtils {

    private static final String LOG_TAG = DataUtils.class.getSimpleName();

    public static void insertInitialData(SQLiteDatabase db) {
        if (db == null) {
            return;
        }

        List<ContentValues> list = new ArrayList<ContentValues>();

        /* Babykleding */
        ContentValues cv = new ContentValues();
        cv.put(BabyListContract.BabyListEntry.COLUMN_DESCRIPTION, "6 rompertjes");
        cv.put(BabyListContract.BabyListEntry.COLUMN_AMOUNT, 6);
        cv.put(BabyListContract.BabyListEntry.COLUMN_CHECKED, true);
        cv.put(BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID, 1);
        cv.put(BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK, "http://www.google.com");
        cv.put(BabyListContract.BabyListEntry.COLUMN_CATEGORY, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(BabyListContract.BabyListEntry.COLUMN_DESCRIPTION, "6 stukjes bovenkleding");
        cv.put(BabyListContract.BabyListEntry.COLUMN_AMOUNT, 6);
        cv.put(BabyListContract.BabyListEntry.COLUMN_CHECKED, false);
        cv.put(BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID, 1);
        cv.put(BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK, "http://www.google.com");
        cv.put(BabyListContract.BabyListEntry.COLUMN_CATEGORY, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(BabyListContract.BabyListEntry.COLUMN_DESCRIPTION, "6 broekjes");
        cv.put(BabyListContract.BabyListEntry.COLUMN_AMOUNT, 6);
        cv.put(BabyListContract.BabyListEntry.COLUMN_CHECKED, false);
        cv.put(BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID, 1);
        cv.put(BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK, "http://www.google.com");
        cv.put(BabyListContract.BabyListEntry.COLUMN_CATEGORY, 1);
        list.add(cv);

        /* Luiers verschonen */
        cv = new ContentValues();
        cv.put(BabyListContract.BabyListEntry.COLUMN_DESCRIPTION, "billendoekjes");
        cv.put(BabyListContract.BabyListEntry.COLUMN_AMOUNT, 1);
        cv.put(BabyListContract.BabyListEntry.COLUMN_CHECKED, false);
        cv.put(BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID, 2);
        cv.put(BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK, "http://www.google.com");
        cv.put(BabyListContract.BabyListEntry.COLUMN_CATEGORY, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(BabyListContract.BabyListEntry.COLUMN_DESCRIPTION, "commode");
        cv.put(BabyListContract.BabyListEntry.COLUMN_AMOUNT, 1);
        cv.put(BabyListContract.BabyListEntry.COLUMN_CHECKED, false);
        cv.put(BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID, 2);
        cv.put(BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK, "http://www.google.com");
        cv.put(BabyListContract.BabyListEntry.COLUMN_CATEGORY, 2);
        list.add(cv);

        try {
            db.beginTransaction();
            // clear the table first
            db.delete(BabyListContract.BabyListEntry.TABLE_NAME, null, null);
            // go through the list and add one by one
            for (ContentValues value: list) {
                db.insert(BabyListContract.BabyListEntry.TABLE_NAME, null, value);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            // ¯\_(ツ)_/¯
            Log.e(LOG_TAG, "SQLException: " + e);
        }
        finally {
            db.endTransaction();
        }
    }

}
