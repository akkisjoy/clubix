package com.prolificwebworks.theclubix.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.entities.ClubData;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.prolificwebworks.theclubix.utils.MyEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vaibhav on 13/10/15.
 */
public class AllCLubAdapter extends RecyclerView.Adapter<AllCLubAdapter.ViewHolder> {

    Fragment context;
    Set<String> clubIdsList;
    List<ClubData> clubDataList;

    public AllCLubAdapter(Fragment context, List<ClubData> eventDataList) {

        this.clubDataList = eventDataList;
        this.context = context;
        clubIdsList = new HashSet<>();
    }


    @Override
    public AllCLubAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_timer, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllCLubAdapter.ViewHolder holder, int position) {

        holder.firstTimeText.setText(clubDataList.get(position).getRestaurant_name());
    }

    @Override
    public int getItemCount() {

        if (clubDataList != null && clubDataList.size() != 0)
            return clubDataList.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CustomFont firstTimeText;
        ImageView firstTimeChecker;

        public ViewHolder(View itemView) {
            super(itemView);

            firstTimeText = (CustomFont) itemView.findViewById(R.id.firstTimeText);
//            firstTimeChecker = (ImageView) itemView.findViewById(R.id.firstTimeChecker);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (clubIdsList.contains(clubDataList.get(getAdapterPosition()).getPostId())) {
                clubIdsList.remove(clubDataList.get(getAdapterPosition()).getPostId());
            } else clubIdsList.add(clubDataList.get(getAdapterPosition()).getPostId());

            MyEnum.INSTANCE.setClub(clubIdsList);

        }
    }
}
