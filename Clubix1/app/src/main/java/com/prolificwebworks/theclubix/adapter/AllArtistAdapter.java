package com.prolificwebworks.theclubix.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.entities.ArtistData;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.prolificwebworks.theclubix.utils.MyEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vaibhav on 13/10/15.
 */
public class AllArtistAdapter extends RecyclerView.Adapter<AllArtistAdapter.ViewHolder> {

    Context context;
    Set<String> eventIdsList;
    List<ArtistData> artistDataList;

    public AllArtistAdapter(Context context, List<ArtistData> eventDataList) {

        this.artistDataList = eventDataList;
        this.context = context;
        eventIdsList = new HashSet<>();
    }

    @Override
    public AllArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_recyclerview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllArtistAdapter.ViewHolder holder, int position) {


        holder.firstTimeText.setText(artistDataList.get(position).getPost_title());


    }

    @Override
    public int getItemCount() {

        if (artistDataList != null && artistDataList.size() != 0)
            return artistDataList.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CustomFont firstTimeText;
       // ImageView firstTimeChecker;

        public ViewHolder(View itemView) {
            super(itemView);

            firstTimeText = (CustomFont) itemView.findViewById(R.id.firstTimeText);
//            firstTimeChecker = (ImageView) itemView.findViewById(R.id.firstTimeChecker);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (eventIdsList.contains(artistDataList.get(getAdapterPosition()).getPostId())) {
                eventIdsList.remove(artistDataList.get(getAdapterPosition()).getPostId());
            } else eventIdsList.add(artistDataList.get(getAdapterPosition()).getPostId());

            MyEnum.INSTANCE.setArtist(eventIdsList);

        }
    }
}
