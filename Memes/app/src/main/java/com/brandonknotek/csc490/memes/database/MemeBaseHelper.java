package com.brandonknotek.csc490.memes.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATEBASE_NAME = "memeBase.db";

    public MemeBaseHelper(Context context){
        super(context, DATEBASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MemeDbSchema.MemeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MemeDbSchema.MemeTable.Cols.UUID + ", " +
                MemeDbSchema.MemeTable.Cols.TITLE + ", " +
                MemeDbSchema.MemeTable.Cols.TOPTEXT + ", " +
                MemeDbSchema.MemeTable.Cols.BOTTOMTEXT + ", "+
                MemeDbSchema.MemeTable.Cols.IMAGEURL +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
