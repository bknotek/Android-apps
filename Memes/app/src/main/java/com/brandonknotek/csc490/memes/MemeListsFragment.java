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
            titleText.setText(String.valueOf(meme.getMemeTitle()));
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