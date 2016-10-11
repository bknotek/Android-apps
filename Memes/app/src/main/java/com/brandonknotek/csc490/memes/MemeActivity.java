package com.brandonknotek.csc490.memes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;


public class MemeActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, MemeActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new MemeFragment();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFragment();


    }
}

