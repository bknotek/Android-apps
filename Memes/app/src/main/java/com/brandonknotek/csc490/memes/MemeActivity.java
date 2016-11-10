package com.brandonknotek.csc490.memes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import java.util.UUID;


public class MemeActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext, UUID memeID, Boolean create){
        Intent intent = new Intent(packageContext, MemeActivity.class);
        intent.putExtra("EXTRA_MEME_ID",memeID);
        intent.putExtra("CREATE",create);
        return intent;
    }

    public static Intent newIntent(Context packageContext,Boolean create){
        Intent intent = new Intent(packageContext, MemeActivity.class);
        intent.putExtra("CREATE",create);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID memeID = (UUID) getIntent().getSerializableExtra("EXTRA_MEME_ID");
        Boolean create = getIntent().getBooleanExtra("CREATE",false);
        return MemeFragment.newInstance(memeID,create);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFragment();
    }
}

