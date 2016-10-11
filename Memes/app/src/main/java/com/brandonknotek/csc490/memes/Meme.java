package com.brandonknotek.csc490.memes;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.UUID;


public class Meme implements Serializable{


    private Drawable memeImage;
    private String topText;
    private String bottomText;
    private String memeTitle;
    private UUID meme_id;

    public Meme(){
        //Generate id
       meme_id = UUID.randomUUID();
    }
    public Meme (Drawable memeImage,String topText,String bottomText,String memeTitle){
        meme_id = UUID.randomUUID();

        this.memeImage = memeImage;
        this.topText = topText;
        this.bottomText = bottomText;
        this.memeTitle =memeTitle;
    }

    public UUID getMeme_id() {
        return meme_id;
    }

    public Drawable getMemeImage() {
        return memeImage;
    }

    public void setMemeImage(Drawable memeImage) {
        this.memeImage = memeImage;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public String getMemeTitle() {
        return memeTitle;
    }

    public void setMemeTitle(String memeTitle) {
        this.memeTitle = memeTitle;
    }
}
