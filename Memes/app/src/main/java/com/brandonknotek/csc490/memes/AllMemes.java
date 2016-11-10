package com.brandonknotek.csc490.memes;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.brandonknotek.csc490.memes.database.MemeBaseHelper;
import com.brandonknotek.csc490.memes.database.MemeCursorWrapper;
import com.brandonknotek.csc490.memes.database.MemeDbSchema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AllMemes {
    public static AllMemes allMemes;

    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static AllMemes get(Context context){

        if(allMemes ==  null){
            allMemes = new AllMemes(context);

        }
        return allMemes;


    }

    private AllMemes(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new MemeBaseHelper(mContext)
                .getWritableDatabase();

    }

    public void addMeme(Meme m){

        ContentValues values = getContentValues(m);
        mDatabase.insert(MemeDbSchema.MemeTable.NAME,null,values);
    }

    public void deleteMeme(Meme m, String title ){
        ContentValues values = getContentValues(m);
        mDatabase.delete(MemeDbSchema.MemeTable.NAME,"title=?",new String[]{title});
    }

    public List<Meme> getMemes(){

        List<Meme> memes = new ArrayList<>();
        MemeCursorWrapper cursor = queryMemes(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                memes.add(cursor.getMeme());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return memes;
    }

    public Meme getMeme(UUID id){

        MemeCursorWrapper cursor = queryMemes(MemeDbSchema.MemeTable.Cols.UUID + " = ?",
                                              new String[] { id.toString() });
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMeme();
        } finally {
            cursor.close();
        }
        }

    public void updateMeme(Meme meme){
        String uuidString = meme.getMeme_id().toString();
        ContentValues values = getContentValues(meme);

        mDatabase.update(MemeDbSchema.MemeTable.NAME, values,
                MemeDbSchema.MemeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Meme meme){
        ContentValues values = new ContentValues();
        values.put(MemeDbSchema.MemeTable.Cols.UUID,meme.getMeme_id().toString());
        values.put(MemeDbSchema.MemeTable.Cols.TITLE,meme.getMemeTitle());
        values.put(MemeDbSchema.MemeTable.Cols.TOPTEXT,meme.getTopText());
        values.put(MemeDbSchema.MemeTable.Cols.BOTTOMTEXT,meme.getBottomText());
        values.put(MemeDbSchema.MemeTable.Cols.IMAGEURL,meme.getImageURL());



        return values;
    }

    private MemeCursorWrapper queryMemes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MemeDbSchema.MemeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MemeCursorWrapper(cursor);
    }
    }


