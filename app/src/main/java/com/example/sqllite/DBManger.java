package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class DBManger extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "demo.db";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "personal";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String ADD = "address";
    private static final String GENDER = "gender";

    private Context mContext;

    DBManger(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    // câu lệnh Create Table

    /*private String mSQL = String.format(
            "CREATE TABLE %s ( %s integer primary key, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
            TABLE_NAME, ID, NAME, PHONE, ADD, GENDER);*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + ID
                + " integer primary key, "
                + NAME
                + " "
                + "TEXT, "
                + PHONE
                + " TEXT, "
                + ADD
                + " TEXT, "
                + GENDER
                + " "
                + "TEXT)";
        db.execSQL(sqlQuery);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(mContext.getString(R.string.drop_table) + TABLE_NAME);
        onCreate(db);
        Log.d(TAG, "onUpgrade");
    }

    // thêm thông tin người use vào SQL
    void addPersonal(Personal personal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, personal.getName());
        values.put(PHONE, personal.getPhone());
        values.put(ADD, personal.getAdd());
        values.put(GENDER, personal.getGender());

        db.insert(TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "add personal success");
    }

    private static final String TAG = "make";

    public Personal getPersonalById(int id) {
        // read data from database in sqlLite
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_NAME, new String[] { ID, NAME, PHONE, ADD, GENDER }, ID + "=?",
                        new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        assert cursor != null;
        Personal personal =
                new Personal(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4));
        cursor.close();
        db.close();
        return personal;
    }
}
