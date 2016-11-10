package com.brandonknotek.csc490.memes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MemeListsFragment extends Fragment {

    private RecyclerView memeRecyclerView;
    private MemeAdapter memeAdapter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //pre-made memes
        Meme philosoraptor_01 = new Meme("http://i1.kym-cdn.com/photos/images/facebook/000/085/283/philosoraptor.jpg",
                "WHY DO NOSES RUN...",
                "...WHILE FEET SMELL",
                "-PHILOSORAPTOR-");

        Meme philosoraptor_02 = new Meme("http://i1.kym-cdn.com/photos/images/facebook/000/085/283/philosoraptor.jpg",
                "IF YOU HAVE X-RAY VISION AND YOU CLOSE YOUR EYES,",
                "CAN YOU STILL SEE?",
                "-PHILOSORAPTOR-");
        AllMemes.get(getActivity()).deleteMeme(philosoraptor_02,"-PHILOSORAPTOR-");
        AllMemes.get(getActivity()).addMeme(philosoraptor_01);
        AllMemes.get(getActivity()).addMeme(philosoraptor_02);

        Meme bad_luck_01 = new Meme("http://www.dibujatumeme.com/templates/bad-luck-brian-meme-template.jpg",
                "DOWNLOADS ONE SONG",
                "PRISON",
                "-BAD LUCK BRIAN-");

        Meme bad_luck_02 = new Meme("http://www.dibujatumeme.com/templates/bad-luck-brian-meme-template.jpg",
                "PUTS EYE DROPS IN",
                "SUPER GLUE",
                "-BAD LUCK BRIAN-");
        AllMemes.get(getActivity()).deleteMeme(bad_luck_02,"-BAD LUCK BRIAN-");
        AllMemes.get(getActivity()).addMeme(bad_luck_01);
        AllMemes.get(getActivity()).addMeme(bad_luck_02);

        Meme aliens_01 = new Meme("http://www.relatably.com/m/img/meme-generator-ancient-aliens-guy/26am.jpg",
                "IM NOT SAYING IT WAS ALIENS",
                "BUT IT WAS ALIENS",
                "-ANCIENT ALIENS-");
        AllMemes.get(getActivity()).deleteMeme(aliens_01,"-ANCIENT ALIENS-");
        AllMemes.get(getActivity()).addMeme(aliens_01);

        Meme keanu_01 = new Meme("https://s-media-cache-ak0.pinimg.com/564x/34/da/db/34dadb7ef616c7239677d6cf40914106.jpg",
                "WHAT IF AIR IS ACTUALLY POISONOUS",
                "AND IT JUST TAKES 80 YEARS TO KILL US",
                "-CONSPIRACY KEANU-");

        Meme keanu_02 = new Meme("https://s-media-cache-ak0.pinimg.com/564x/34/da/db/34dadb7ef616c7239677d6cf40914106.jpg",
                "WHAT IF ONLY THE STICKERS",
                "WERE MADE IN CHINA",
                "-CONSPIRACY KEANU-");
        AllMemes.get(getActivity()).deleteMeme(keanu_02,"-CONSPIRACY KEANU-");
        AllMemes.get(getActivity()).addMeme(keanu_01);
        AllMemes.get(getActivity()).addMeme(keanu_02);

        Meme ten_guy_01 = new Meme("https://i.imgflip.com/11igxo.jpg",
                "\"IT'S TOO BRIGHT\"",
                "TURNS DOWN MUSIC",
                "-10 GUY-");
        AllMemes.get(getActivity()).deleteMeme(ten_guy_01,"-10 GUY-");
        AllMemes.get(getActivity()).addMeme(ten_guy_01);

    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meme_list, container, false);

        memeRecyclerView = (RecyclerView) view.findViewById(R.id.meme_recycler_view);
        memeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_meme_list,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_meme:
                Intent intent = MemeActivity.newIntent(getActivity(),true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void updateUI(){
        AllMemes allMemes = AllMemes.get(getActivity());
        List<Meme> memes = allMemes.getMemes();



        if(memeAdapter==null){
            memeAdapter = new MemeAdapter(memes);
            memeRecyclerView.setAdapter(memeAdapter);}
        else{
            memeAdapter.setMemes(memes);
            memeAdapter.notifyDataSetChanged();
        }

    }

    private class MemeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Meme meme;
        private ImageView memeImage;
        private TextView titleText;
        private TextView topText;
        private TextView bottomText;

        public MemeHolder(View itemView){

            super(itemView);
            itemView.setOnClickListener(this);

            memeImage = (ImageView)itemView.findViewById(R.id.list_meme_thumbnail);
            titleText = (TextView)itemView.findViewById(R.id.list_item_meme_title);
            topText = (TextView)itemView.findViewById(R.id.list_item_thumbnail_top_text);
            bottomText = (TextView)itemView.findViewById(R.id.list_item_thumbnail_bottom_text);

        }

        public void bindMeme(Meme meme){
            this.meme = meme;

            Picasso.with(getContext()).load(meme.getImageURL()).into(memeImage);
            memeImage.setBackground(getResources().getDrawable(R.drawable.border));
            titleText.setText("-"+String.valueOf(meme.getMemeTitle())+"-");
            topText.setText(String.valueOf(meme.getTopText()));
            bottomText.setText(String.valueOf(meme.getBottomText()));
        }



        @Override
        public void onClick(View v) {
            Intent intent = MemeActivity.newIntent(getActivity(), meme.getMeme_id(),false);
            startActivity(intent);
        }
    }

    private class MemeAdapter extends RecyclerView.Adapter<MemeHolder>{
        private List<Meme> memes;

        public MemeAdapter(List<Meme> memes){

            this.memes = memes;
        }

        @Override
        public void onBindViewHolder(MemeHolder holder, int position) {
            Meme meme = memes.get(position);
            holder.bindMeme(meme);
        }


        @Override
        public MemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_meme,parent,false);


            return new MemeHolder(view);
        }

        @Override
        public int getItemCount() {
            return memes.size();
        }

        public void setMemes(List<Meme> memes){
            this.memes = memes;
        }
    }
}