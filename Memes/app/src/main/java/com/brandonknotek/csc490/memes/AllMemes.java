package com.brandonknotek.csc490.memes;


import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AllMemes implements Serializable {
    public static AllMemes allMemes;

    public List<Meme> memes;

    public static AllMemes get(Context context){

        if(allMemes ==  null){
            allMemes = new AllMemes(context);

        }
        return allMemes;
    }

    private AllMemes(Context context){

        memes = new ArrayList<>();


        Meme philosoraptor_01 = new Meme(ContextCompat.getDrawable(context,R.drawable.philosoraptor_meme),
                "WHY DO NOSES RUN...",
                "...WHILE FEET SMELL",
                "PHILOSORAPTOR");
        memes.add(philosoraptor_01);
        Meme philosoraptor_02 = new Meme(ContextCompat.getDrawable(context,R.drawable.philosoraptor_meme),
                "IF YOU HAVE X-RAY VISION AND YOU CLOSE YOUR EYES,",
                "CAN YOU STILL SEE?",
                "PHILOSORAPTOR");
        memes.add(philosoraptor_02);
        Meme bad_luck_01 = new Meme(ContextCompat.getDrawable(context,R.drawable.bad_luck_brian_meme),
                "DOWNLOADS ONE SONG",
                "PRISON",
                "BAD LUCK BRIAN");
        memes.add(bad_luck_01);
        Meme bad_luck_02 = new Meme(ContextCompat.getDrawable(context,R.drawable.bad_luck_brian_meme),
                "PUTS EYE DROPS IN",
                "SUPER GLUE",
                "BAD LUCK BRIAN");
        memes.add(bad_luck_02);
        Meme aliens_01 = new Meme(ContextCompat.getDrawable(context,R.drawable.aliens_meme),
                "IM NOT SAYING IT WAS ALIENS",
                "BUT IT WAS ALIENS",
                "ANCIENT ALIENS");
        memes.add(aliens_01);

        Meme keanu_01 = new Meme(ContextCompat.getDrawable(context,R.drawable.conspiracy_keunu),
                "WHAT IF AIR IS ACTUALLY POISONOUS",
                "AND IT JUST TAKES 80 YEARS TO KILL US",
                "CONSPIRACY KEANU");
        memes.add(keanu_01);
        Meme keanu_02 = new Meme(ContextCompat.getDrawable(context,R.drawable.conspiracy_keunu),
                "WHAT IF ONLY THE STICKERS",
                "WERE MADE IN CHINA",
                "CONSPIRACY KEANU");
        memes.add(keanu_02);
        Meme ten_guy_01 = new Meme(ContextCompat.getDrawable(context,R.drawable.ten_guy_meme),
                "\"IT'S TOO BRIGHT\"",
                "TURNS DOWN MUSIC",
                "10 GUY");
        memes.add(ten_guy_01);
    }

    public void addMeme(Meme m){

        memes.add(m);

    }


    public List<Meme> getMemes(){

        return memes;
    }

    public Meme getMeme(String id){

        for(Meme meme : memes){
            if(meme.getMeme_id().equals(id)){
                return meme;
            }
        }
            return null;
        }
    }


