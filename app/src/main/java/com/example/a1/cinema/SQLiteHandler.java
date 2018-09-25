package com.example.a1.cinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getSimpleName();
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="users";
    public static final String TABLE_NAME="users";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_UID="uid";
    public static final String KEY_CREATED_AT="created_at";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE="CREATE TABLE "+TABLE_NAME+"("+
                KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+
                KEY_EMAIL+" TEXT UNIQUE,"+KEY_UID+" TEXT,"+
                KEY_CREATED_AT+" TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addUser(String name,String email,String uid,String created_at)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_UID,uid);
        values.put(KEY_CREATED_AT,created_at);

        long id=db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }
}
