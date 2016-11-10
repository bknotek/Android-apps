package com.brandonknotek.csc490.memes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import java.util.UUID;

public class MemeListActivity extends SingleFragmentActivity {
    public static final String EXTRA_MEME_ID = "com.brandonknotek.csc490.memes.meme_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static Intent newIntent(Context packageContext, UUID memeId){
        Intent intent = new Intent(packageContext, MemeListActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new MemeListsFragment();
    }


}
