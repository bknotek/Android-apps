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

        //pre-made memes
        Meme philosoraptor_01 = new Meme("http://i1.kym-cdn.com/photos/images/facebook/000/085/283/philosoraptor.jpg",
                "WHY DO NOSES RUN...",
                "...WHILE FEET SMELL",
                "PHILOSORAPTOR");
        this.addMeme(philosoraptor_01);
        Meme philosoraptor_02 = new Meme("http://i1.kym-cdn.com/photos/images/facebook/000/085/283/philosoraptor.jpg",
                "IF YOU HAVE X-RAY VISION AND YOU CLOSE YOUR EYES,",
                "CAN YOU STILL SEE?",
                "PHILOSORAPTOR");
        this.addMeme(philosoraptor_02);
        Meme bad_luck_01 = new Meme("http://www.dibujatumeme.com/templates/bad-luck-brian-meme-template.jpg",
                "DOWNLOADS ONE SONG",
                "PRISON",
                "BAD LUCK BRIAN");
        this.addMeme(bad_luck_01);
        Meme bad_luck_02 = new Meme("http://www.dibujatumeme.com/templates/bad-luck-brian-meme-template.jpg",
                "PUTS EYE DROPS IN",
                "SUPER GLUE",
                "BAD LUCK BRIAN");
        this.addMeme(bad_luck_02);
        Meme aliens_01 = new Meme("http://www.relatably.com/m/img/meme-generator-ancient-aliens-guy/26am.jpg",
                "IM NOT SAYING IT WAS ALIENS",
                "BUT IT WAS ALIENS",
                "ANCIENT ALIENS");
        this.addMeme(aliens_01);

        Meme keanu_01 = new Meme("https://s-media-cache-ak0.pinimg.com/564x/34/da/db/34dadb7ef616c7239677d6cf40914106.jpg",
                "WHAT IF AIR IS ACTUALLY POISONOUS",
                "AND IT JUST TAKES 80 YEARS TO KILL US",
                "CONSPIRACY KEANU");
        this.addMeme(keanu_01);
        Meme keanu_02 = new Meme("https://s-media-cache-ak0.pinimg.com/564x/34/da/db/34dadb7ef616c7239677d6cf40914106.jpg",
                "WHAT IF ONLY THE STICKERS",
                "WERE MADE IN CHINA",
                "CONSPIRACY KEANU");
        this.addMeme(keanu_02);
        Meme ten_guy_01 = new Meme("https://i.imgflip.com/11igxo.jpg",
                "\"IT'S TOO BRIGHT\"",
                "TURNS DOWN MUSIC",
                "10 GUY");
        this.addMeme(ten_guy_01);
    }

    public void addMeme(Meme m){

        ContentValues values = getContentValues(m);
        mDatabase.insert(MemeDbSchema.MemeTable.NAME,null,values);
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


