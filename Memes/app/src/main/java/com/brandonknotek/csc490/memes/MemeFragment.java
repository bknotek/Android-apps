package com.brandonknotek.csc490.memes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.UUID;

public class MemeFragment extends Fragment {

    private Meme meme;
    private EditText titleText;
    private EditText topText;
    private EditText bottomText;
    private TextView finalTitle;
    private TextView finalBottomText;
    private TextView finalTopText;
    private ImageView finalImage;
    private Button urlButton;
    private ImageView memeImage;
    private Button createButton;
    private Button editButton;
    private String urlText = "";
    private Boolean create;
    private TextView editText;
    private Boolean editing=false;


    public static MemeFragment newInstance(UUID memeId, Boolean create){
        Bundle args = new Bundle();
        args.putSerializable("ARG_MEME_ID", memeId);
        args.putBoolean("CREATE", create);

        MemeFragment fragment = new MemeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        create = getArguments().getBoolean("CREATE");
        if(!create) {
            UUID memeId = (UUID) getArguments().getSerializable("ARG_MEME_ID");
            meme = AllMemes.get(getActivity()).getMeme(memeId);
        }
        if(create){
            setHasOptionsMenu(false);
        }
        }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_meme,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_edit_meme:
                editOptionClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editOptionClicked() {
        editing=true;
        setHasOptionsMenu(false);
        finalBottomText.setVisibility(View.INVISIBLE);
        finalTopText.setVisibility(View.INVISIBLE);
        finalTitle.setVisibility(View.INVISIBLE);
        finalImage.setVisibility(View.INVISIBLE);

        titleText.setVisibility(View.VISIBLE);
        topText.setVisibility(View.VISIBLE);
        bottomText.setVisibility(View.VISIBLE);
        memeImage.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);

        urlButton.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.VISIBLE);

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
                        if (!urlText.equals("")) {
                            Picasso.with(getContext()).load(urlText).into(memeImage);
                        } else {
                            dialog.dismiss();
                        }
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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDoneButtonClicked();
            }
        });
    }

    private void editDoneButtonClicked() {
        editing=false;
        setHasOptionsMenu(true);
        meme.setTopText(topText.getText().toString());
        meme.setBottomText(bottomText.getText().toString());
        meme.setMemeTitle(titleText.getText().toString());

        if(!urlText.equals("")){
        meme.setMemeImage(urlText);}
        else {
            meme.setMemeImage(meme.getImageURL());
        }

        editButton.setVisibility(View.INVISIBLE);
        urlButton.setVisibility(View.INVISIBLE);

        finalBottomText.setText(meme.getBottomText());
        finalTopText.setText(meme.getTopText());
        finalTitle.setText(meme.getMemeTitle());
        Picasso.with(getContext()).load(meme.getImageURL()).into(finalImage);

        finalBottomText.setVisibility(View.VISIBLE);
        finalTopText.setVisibility(View.VISIBLE);
        finalImage.setVisibility(View.VISIBLE);

        titleText.setVisibility(View.INVISIBLE);
        topText.setVisibility(View.INVISIBLE);
        bottomText.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);
        memeImage.setVisibility(View.INVISIBLE);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meme, container, false);

        topText = (EditText) v.findViewById(R.id.top_text);
        bottomText = (EditText) v.findViewById(R.id.bottom_text);
        memeImage = (ImageView) v.findViewById(R.id.meme_Image);
        titleText = (EditText) v.findViewById(R.id.title_text);
        urlButton = (Button)v.findViewById(R.id.choose_Image);
        createButton = (Button) v.findViewById(R.id.create_button);
        editButton = (Button) v.findViewById(R.id.edit_button);
        editText = (TextView) v.findViewById(R.id.editText);

        finalBottomText = (TextView) v.findViewById(R.id.final_bottom_text);
        finalTopText = (TextView) v.findViewById(R.id.final_top_text);
        finalTitle = (TextView) v.findViewById(R.id.final_title_text);
        finalImage = (ImageView) v.findViewById(R.id.final_image);

        if(create) {
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
                            if (!urlText.equals("")) {
                                Picasso.with(getContext()).load(urlText).into(memeImage);
                            } else {
                                dialog.dismiss();
                            }
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

            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!urlText.equals("")) {
                        Meme meme = new Meme(urlText,
                                topText.getText().toString(),
                                bottomText.getText().toString(),
                                titleText.getText().toString());
                        AllMemes.get(getActivity()).addMeme(meme);
                        Intent intent = MemeListActivity.newIntent(getActivity(), meme.getMeme_id());
                        startActivity(intent);
                    } else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("No Image URL Entered");
                        builder.setMessage("Must enter an Image URL before MEME can be created");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                    }
                }
            });
        }
        else {
            UUID memeId = (UUID) getArguments().getSerializable("ARG_MEME_ID");
            meme = AllMemes.get(getActivity()).getMeme(memeId);
            Picasso.with(getContext()).load(meme.getImageURL()).into(finalImage);
            Picasso.with(getContext()).load(meme.getImageURL()).into(memeImage);

            listItemClicked();
        }
        return v;

    }

    private void listItemClicked() {
        finalTopText.setText(meme.getTopText());
        finalTopText.setVisibility(View.VISIBLE);
        finalBottomText.setText(meme.getBottomText());
        finalBottomText.setVisibility(View.VISIBLE);


        titleText.setText(meme.getMemeTitle());
        titleText.setVisibility(View.INVISIBLE);
        topText.setText(meme.getTopText());
        topText.setVisibility(View.INVISIBLE);
        bottomText.setText(meme.getBottomText());
        bottomText.setVisibility(View.INVISIBLE);
        memeImage.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);


        urlButton.setVisibility(View.INVISIBLE);
        createButton.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null  && !savedInstanceState.getString("stringURL").equals("")) {
            urlText = savedInstanceState.getString("stringURL");
            Picasso.with(getContext()).load(urlText).into(memeImage);

        }

        if(savedInstanceState!=null){
            editing = savedInstanceState.getBoolean("editing");

            if(editing){
                editOptionClicked();
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("stringURL",urlText);
        outState.putBoolean("editing",editing);
    }

    @Override
    public void onPause() {
        super.onPause();

        if(!create){
        AllMemes.get(getActivity())
                .updateMeme(meme);}
    }
}
