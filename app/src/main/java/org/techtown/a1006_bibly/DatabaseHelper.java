package org.techtown.a1006_bibly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Video.VideoColumns.CATEGORY;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "database.db";
    public static final String TABLE = "mytable";
    public static final String POST_TITLE ="post_title";
    public static final String POST_CONTENT ="post_content";
//    public static final String CATE ="cate";
    String br;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
//         br = "CREATE TABLE IF NOT EXISTS " + TABLE
//                + "("
//                + POST_TITLE + " Text, "
//                + POST_CONTENT +  " Text, "
//                + CATE + " Text);";
        br = "CREATE TABLE " + TABLE
                + "("
                + POST_TITLE + " Text, "
                + POST_CONTENT + " Text);";
        db.execSQL(br);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
    }

    public void insertdata(String post_title, String post_content/*, String cate*/) {
        //System.out.print("Hello "+br);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(POST_TITLE, post_title);
        contentValues.put(POST_CONTENT, post_content);
//        contentValues.put(CATE, cate);
        db.insert(TABLE, null, contentValues);
    }

    public List<DataModel> getdata(){
        List<DataModel> data=new ArrayList<DataModel>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String post_title = cursor.getString(cursor.getColumnIndexOrThrow("post_title"));
            String post_content = cursor.getString(cursor.getColumnIndexOrThrow("post_content"));
//            String cate = cursor.getString(cursor.getColumnIndexOrThrow("cate"));
            dataModel.setPost_title(post_title);
            dataModel.setPost_content(post_content);
//            dataModel.setCate(cate);
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
