package com.brandonknotek.csc490.memes;

import java.util.UUID;

public class Meme {


    private String imageURL;
    private String topText;
    private String bottomText;
    private String memeTitle;
    private UUID meme_id;

    public Meme(){
        //Generate id
        this(UUID.randomUUID());
    }

    public Meme(UUID id){
        meme_id = id;
    }

    public Meme (String memeImage,String topText,String bottomText,String memeTitle){

        meme_id = UUID.randomUUID();
        this.imageURL = memeImage;
        this.topText = topText;
        this.bottomText = bottomText;
        this.memeTitle =memeTitle;
    }

    public UUID getMeme_id() {
        return meme_id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setMemeImage(String imageURL) {
        this.imageURL = imageURL;
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
