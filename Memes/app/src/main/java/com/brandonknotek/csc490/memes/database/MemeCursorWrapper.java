package com.brandonknotek.csc490.memes.database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.brandonknotek.csc490.memes.Meme;

import java.util.UUID;


public class MemeCursorWrapper extends CursorWrapper{
    public MemeCursorWrapper(Cursor cursor){ super(cursor);}

    public Meme getMeme(){
        String uuidString = getString(getColumnIndex(MemeDbSchema.MemeTable.Cols.UUID));
        String title = getString(getColumnIndex(MemeDbSchema.MemeTable.Cols.TITLE));
        String topText = getString(getColumnIndex(MemeDbSchema.MemeTable.Cols.TOPTEXT));
        String bottomText = getString(getColumnIndex(MemeDbSchema.MemeTable.Cols.BOTTOMTEXT));
        String imageUrl = getString(getColumnIndex(MemeDbSchema.MemeTable.Cols.IMAGEURL));

        Meme meme = new Meme(UUID.fromString(uuidString));
        meme.setMemeTitle(title);
        meme.setTopText(topText);
        meme.setBottomText(bottomText);
        meme.setMemeImage(imageUrl);

        return meme;
    }




}
