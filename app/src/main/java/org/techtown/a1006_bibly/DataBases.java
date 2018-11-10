package org.techtown.a1006_bibly;

import android.provider.BaseColumns;

public final class DataBases {
    public static final class CreateDB implements BaseColumns {
//        public static final String USERID = "userid";
//        public static final String NAME = "name";
//        public static final String AGE = "age";
//        public static final String GENDER = "gender";
//        public static final String _TABLENAME0 = "usertable";
//        public static final String _CREATE0
//                = "create table if not exists "
//                + _TABLENAME0 + "("
//                + _ID + " integer primary key autoincrement, "
//                + USERID + " text not null , "
//                + NAME + " text not null , "
//                + AGE + " integer not null , "
//                + GENDER + " text not null );";

        public static final String BOOKID = "bookid";
        public static final int BOOKIMG = R.drawable.dummy_1_1;
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
//        public static final String RATE = "rate";
//        public static final String WISESAYING = "wisesaying";
//        public static final String SUMMARY = "summary";
        public static final String _TABLENAME0 = "booktable";
        public static final String _CREATE0
                = "create table if not exists "
                + _TABLENAME0 + "("
                + _ID + " integer primary key autoincrement, " //??
                + BOOKID + " text not null , "
                + BOOKIMG + " text not null , "
                + TITLE + " integer not null , "
                + AUTHOR + " text not null );";
    }
}
