package org.techtown.a1006_bibly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE = "database.db";
    public static String TABLE ="mytable";
    public static String POST_TITLE ="post_title";
    public static String POST_CONTENT ="post_content";
    String br;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
        br = "CREATE TABLE " + TABLE + "(" + POST_TITLE + " Text, " + POST_CONTENT +  " Text);";
        db.execSQL(br);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
    }

    public void insertdata(String post_title, String post_content) {
        System.out.print("Hello "+br);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(POST_TITLE, post_title);
        contentValues.put(POST_CONTENT, post_content);
        db.insert(TABLE,null,contentValues);
    }

    public List<DataModel> getdata(){
        // DataModel dataModel = new DataModel();
        List<DataModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String post_title = cursor.getString(cursor.getColumnIndexOrThrow("post_title"));
            String post_content = cursor.getString(cursor.getColumnIndexOrThrow("post_content"));
            dataModel.setPost_title(post_title);
            dataModel.setPost_content(post_content);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }

//        for (DataModel mo:data ) {
//            Log.i("Hellomo",""+mo.getCity());
//        }

        //

        return data;
    }
}