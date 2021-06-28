package com.dynamicdevs.phonesellerapp.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dynamicdevs.phonesellerapp.model.data.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "phone.db";
    public static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Phone";
    public static final String COLUMN_PHONE_ID = "Phone_id";
    public static final String COLUMN_MODEL = "Model";
    public static final String COLUMN_MAKE = "Make";
    public static final String COLUMN_PRICE = "Price";

    public PhoneDBHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String createCommand = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_PHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_MAKE + " TEXT, " +
                COLUMN_PRICE + " REAL)";

        sqLiteDatabase.execSQL(createCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        String upgrade = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(upgrade);
        onCreate(sqLiteDatabase);
    }

    public void insertPhone(Phone phone){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MODEL, phone.getModel());
        contentValues.put(COLUMN_MAKE, phone.getMaker());
        contentValues.put(COLUMN_PRICE, phone.getPrice());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Phone> getAllPhones(){
        List<Phone> phones = new ArrayList<Phone>();
        String getQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(getQuery, null);
        cursor.move(-1);
        while (cursor.moveToNext()){
            Phone phone = new Phone(cursor.getInt(cursor.getColumnIndex(COLUMN_PHONE_ID)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MAKE)));
            phones.add(phone);
        }

        return phones;
    }

    public void deletePhone(Phone phone){
        String deleteQuery = "DELETE * FROM " + TABLE_NAME + " WHERE " + COLUMN_PHONE_ID + " = " + phone.getID();
        getWritableDatabase().execSQL(deleteQuery);
    }
}
