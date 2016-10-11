package com.brandonknotek.csc490.memes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;



public class MemeFragment extends Fragment {

    private Meme meme;
    private EditText titleText;
    private EditText topText;
    private EditText bottomText;
    private Button urlButton;
    private ImageView memeImage;
    private Button createButton;
    private String urlText = "";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meme, container, false);



        topText = (EditText) v.findViewById(R.id.top_text);

        bottomText = (EditText) v.findViewById(R.id.bottom_text);

        memeImage = (ImageView) v.findViewById(R.id.meme_Image);

        titleText = (EditText) v.findViewById(R.id.title_text);

        urlButton = (Button)v.findViewById(R.id.choose_Image);
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("ENTER IMAGE URL ");
                builder.setMessage("(COPY AND PASTE FROM BROWSER)");

                final EditText input = new EditText(getContext());
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        urlText = input.getText().toString();
                        if(!urlText.equals("")){
                        Picasso.with(getContext()).load(urlText).into(memeImage);}
                        else{
                            dialog.dismiss();}
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

            }

        });

        createButton = (Button) v.findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meme meme = new Meme(memeImage.getDrawable(),
                        topText.getText().toString(),
                        bottomText.getText().toString(),
                        titleText.getText().toString());

                AllMemes.get(getActivity()).addMeme(meme);
                Intent intent = MemeListActivity.newIntent(getActivity(), meme.getMeme_id());
                startActivity(intent);
            }
        });

        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null  && !savedInstanceState.getString("stringURL").equals("")){
            urlText = savedInstanceState.getString("stringURL");
            Picasso.with(getContext()).load(urlText).into(memeImage);
    }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("stringURL",urlText);
    }
}
