package com.brandonknotek.csc490.memes.database;


public class MemeDbSchema {
    public static final class MemeTable{
        public static final String NAME = "memes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TOPTEXT = "topText";
            public static final String BOTTOMTEXT = "bottomText";
            public static final String TITLE = "title";
            public static final String IMAGEURL = "imageURL";
        }
    }
}
