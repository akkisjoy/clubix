package com.prolificwebworks.theclubix.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.SingleArtistActivity;
import com.prolificwebworks.theclubix.entities.ArtistData;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vaibhav on 27/10/15.
 */
public class ArtistGridViewAdapter extends RecyclerView.Adapter<ArtistGridViewAdapter.ViewHolder> {


    Fragment context;
    List<ArtistData> artistDataList;

    public ArtistGridViewAdapter(Fragment fragment, List<ArtistData> artistDataList) {
        this.context = fragment;
        this.artistDataList = artistDataList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_gridview_itemview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ArtistData artistData = artistDataList.get(position);
        holder.artistTitle.setText(artistData.getPost_title());
        Picasso.with(context.getActivity()).load(artistData.getFeatured_image()).into(holder.artistImage);

    }

    @Override
    public int getItemCount() {
        if (artistDataList != null)
            return artistDataList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView artistImage;
        public CustomFont artistTitle;


        public ViewHolder(View itemView) {
            super(itemView);

            artistTitle = (CustomFont) itemView.findViewById(R.id.artist_title);
            artistImage = (ImageView) itemView.findViewById(R.id.artist_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("Artist PostId", artistDataList.get(getAdapterPosition()).getPostId());

                    Intent i = new Intent(context.getActivity().getApplicationContext(), SingleArtistActivity.class);
                    i.putExtra("postId", artistDataList.get(getAdapterPosition()).getPostId() + "");
                    context.getActivity().startActivity(i);
                    context.getActivity().overridePendingTransition(R.anim.slide_up, 0);

//
//                    SingleArtistFragment fragment = SingleArtistFragment.newInstance(artistDataList.get(getAdapterPosition()).getPostId() + "");
//
//                    context.getFragmentManager().beginTransaction().replace(R.id.container_body, fragment).addToBackStack(null).commit();
                }
            });
        }
    }
}
