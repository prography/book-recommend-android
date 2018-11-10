//package org.techtown.a1006_bibly;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DbOpenHelper {
//
//    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
//    private static final int DATABASE_VERSION = 1;
//    public static SQLiteDatabase mDB;
//    private DatabaseHelper mDBHelper;
//    private Context mCtx;
//
//    private class DatabaseHelper extends SQLiteOpenHelper{
//
//        public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
//            super(context, name, factory, version);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db){
//            //데이터베이스의 테이블을 생성합니다. 테이블 구성 시 다른 테이블 명칭을 추가하여
//            //작성하면 하나의 데이터베이스에서 여러 테이블도 생성 가능합니다.
//            db.execSQL(DataBases.CreateDB._CREATE0);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            //버전 업그레이드 시 사용합니다. 이전 버전을 지우고 새 버전을 생성합니다.
//            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
//            onCreate(db);
//        }
//    }
//
//    public DbOpenHelper(Context context){
//        this.mCtx = context;
//    }
//
//    public DbOpenHelper open() throws SQLException{
//        //해당 데이터베이스를 열어서 사용할 수 있도록 해줍니다.
//        //getWritableDatabase()는 데이터베이스를 읽고 쓸 수 있도록 해줍니다.
//        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
//        mDB = mDBHelper.getWritableDatabase();
//        return this;
//    }
//
//    public void create(){
//        mDBHelper.onCreate(mDB);
//    }
//
//    public void close(){
//        //해당 데이터베이스를 닫습니다. 사용 중에는
//        //매번 열고 닫지 않아도 되지만 모두 사용한 후에는 가급적이면 닫아주는 것이 좋습니다
//        mDB.close();
//    }
//
//    // Insert DB
//    public long insertColumn(String userid, String name, long age , String gender){
//        ContentValues values = new ContentValues();
//        values.put(DataBases.CreateDB.USERID, userid);
//        values.put(DataBases.CreateDB.NAME, name);
//        values.put(DataBases.CreateDB.AGE, age);
//        values.put(DataBases.CreateDB.GENDER, gender);
//        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
//    }
//
//    // Update DB
//    public boolean updateColumn(long id, String userid, String name, long age , String gender){
//        ContentValues values = new ContentValues();
//        values.put(DataBases.CreateDB.USERID, userid);
//        values.put(DataBases.CreateDB.NAME, name);
//        values.put(DataBases.CreateDB.AGE, age);
//        values.put(DataBases.CreateDB.GENDER, gender);
//        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
//    }
//
//    // Delete All
//    public void deleteAllColumns() {
//        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
//    }
//
//    // Delete DB
//    public boolean deleteColumn(long id){
//        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
//    }
//    // Select DB
//    public Cursor selectColumns(){
//        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
//    }
//
//    // sort by column
//    public Cursor sortColumn(String sort){
//        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
//        return c;
//    }
//}