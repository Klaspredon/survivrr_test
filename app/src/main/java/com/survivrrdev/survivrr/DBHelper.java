package com.survivrrdev.survivrr;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserInformation.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserInfo.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserInfo.TABLE_NAME);
        onCreate(db);
    }

    public void insertUserInfo(String username, String password, String name, String phone,
                               String address, String birthday, String gender, String contact_name,
                               String contact_phone, String contact_address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserInfo.COLUMN_USERNAME, username);
        contentValues.put(UserInfo.COLUMN_PASSWORD, password);
        contentValues.put(UserInfo.COLUMN_NAME, name);
        contentValues.put(UserInfo.COLUMN_PHONE, phone);
        contentValues.put(UserInfo.COLUMN_ADDRESS, address);
        contentValues.put(UserInfo.COLUMN_NAME_ICE, contact_name);
        contentValues.put(UserInfo.COLUMN_PHONE_ICE, contact_phone);
        contentValues.put(UserInfo.COLUMN_ADDRESS_ICE, contact_address);
        contentValues.put(UserInfo.COLUMN_BIRTHDAY, birthday);
        contentValues.put(UserInfo.COLUMN_GENDER, gender);

        db.insert(UserInfo.TABLE_NAME, null, contentValues);

        db.close();
    }

    public UserInfo getUserInfo(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UserInfo.TABLE_NAME,
                new String[]{UserInfo.COLUMN_ID, UserInfo.COLUMN_NAME, UserInfo.COLUMN_PHONE,
                        UserInfo.COLUMN_ADDRESS, UserInfo.COLUMN_BIRTHDAY, UserInfo.COLUMN_GENDER,
                        UserInfo.COLUMN_NAME_ICE, UserInfo.COLUMN_PHONE_ICE,
                        UserInfo.COLUMN_ADDRESS_ICE},
                UserInfo.COLUMN_USERNAME + " = ?",
                new String[]{username},null,null,null,null
        );

        if (cursor != null){
            cursor.moveToFirst();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(cursor.getInt(cursor.getColumnIndex(UserInfo.COLUMN_ID)));
        userInfo.setUserName(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_NAME)));
        userInfo.setUserPhone(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_PHONE)));
        userInfo.setUserAddress(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_ADDRESS)));
        userInfo.setUserBirthday(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_BIRTHDAY)));
        userInfo.setUserGender(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_GENDER)));
        userInfo.setUserContactName(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_NAME_ICE)));
        userInfo.setUserContactPhone(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_PHONE_ICE)));
        userInfo.setUserContactAddress(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_ADDRESS_ICE)));

        cursor.close();
        db.close();

        return userInfo;
    }

    public List<UserInfo> getAllUserInfo(){
        List<UserInfo> userInfos = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + UserInfo.TABLE_NAME + " ORDER BY "
                + UserInfo.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(cursor.getInt(cursor.getColumnIndex(UserInfo.COLUMN_ID)));
                userInfo.setUserName(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_NAME)));
                userInfo.setUserPhone(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_PHONE)));
                userInfo.setUserAddress(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_ADDRESS)));
                userInfo.setUserBirthday(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_BIRTHDAY)));
                userInfo.setUserGender(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_GENDER)));
                userInfo.setUserContactName(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_NAME_ICE)));
                userInfo.setUserContactPhone(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_PHONE_ICE)));
                userInfo.setUserContactAddress(cursor.getString(cursor.getColumnIndex(UserInfo.COLUMN_ADDRESS_ICE)));

                userInfos.add(userInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userInfos;
    }

    public int getNumberOfUsers(){
        String countQuery = "SELECT * FROM " + UserInfo.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();
        db.close();

        return count;
    }

    public void updateUserInfo(String username, UserInfo userInfo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfo.COLUMN_NAME, userInfo.getUserName());
        contentValues.put(UserInfo.COLUMN_PHONE, userInfo.getUserPhone());
        contentValues.put(UserInfo.COLUMN_ADDRESS, userInfo.getUserAddress());
        contentValues.put(UserInfo.COLUMN_NAME_ICE, userInfo.getUserContactName());
        contentValues.put(UserInfo.COLUMN_PHONE_ICE, userInfo.getUserContactPhone());
        contentValues.put(UserInfo.COLUMN_ADDRESS_ICE, userInfo.getUserContactAddress());
        contentValues.put(UserInfo.COLUMN_BIRTHDAY, userInfo.getUserBirthday());
        contentValues.put(UserInfo.COLUMN_GENDER, userInfo.getUserGender());

        int id = db.update(UserInfo.TABLE_NAME, contentValues, UserInfo.COLUMN_USERNAME + " = ? ",
                new String[]{username});
        Log.d("updateuserinfo","Id is " + String.valueOf(id));
        db.close();
    }

    public void deleteUserInfo(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UserInfo.TABLE_NAME, UserInfo.COLUMN_USERNAME + " = ? ",
                new String[]{username});
        db.close();
    }

    public boolean checkUserInfo(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserInfo.TABLE_NAME,
                new String[]{UserInfo.COLUMN_ID},
                UserInfo.COLUMN_USERNAME + " = ? ",
                new String[]{username},
                null,null,null
        );

        int count = cursor.getCount();

        cursor.close();
        db.close();

        return (count > 0);
    }

    public boolean checkUserInfo(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserInfo.TABLE_NAME,
                new String[]{UserInfo.COLUMN_ID},
                UserInfo.COLUMN_USERNAME + " = ?" + " AND " + UserInfo.COLUMN_PASSWORD + " = ?",
                new String[]{username,password},
                null,null,null
        );

        int count = cursor.getCount();

        cursor.close();
        db.close();

        return (count > 0);
    }

}