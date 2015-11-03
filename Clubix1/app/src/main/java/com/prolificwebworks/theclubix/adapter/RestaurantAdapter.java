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
import com.prolificwebworks.theclubix.activity.SingleClubActivity;
import com.prolificwebworks.theclubix.entities.RestaurantData;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vaibhav on 25/10/15.
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {


    List<RestaurantData> restaurantDataList;
    Fragment context;

    public RestaurantAdapter(Fragment fragment, List<RestaurantData> restaurantDataList) {

        this.restaurantDataList = restaurantDataList;
        context = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clubs_itemview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RestaurantData restaurantData = restaurantDataList.get(position);

        Log.e("Club details", restaurantData.getPost_title() + " " + restaurantData.getRestaurant_logo());

        holder.clubTitle.setText(restaurantData.getPost_title());
        Picasso.with(context.getActivity()).load(restaurantData.getRestaurant_logo().trim()).fit().into(holder.clubImage);
    }

    @Override
    public int getItemCount() {

        if (restaurantDataList != null)
            return restaurantDataList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView clubImage;
        public CustomFont clubTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            clubImage = (ImageView) itemView.findViewById(R.id.club_image);
            clubTitle = (CustomFont) itemView.findViewById(R.id.club_title);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

//            SingleClubFragment fragment = SingleClubFragment.newInstance(restaurantDataList.get(getAdapterPosition()).getPostId());
//            context.getFragmentManager().beginTransaction().replace(R.id.container_body, fragment).addToBackStack(null).commit();

            Intent i = new Intent(context.getActivity().getApplicationContext(), SingleClubActivity.class);
            i.putExtra("postId", restaurantDataList.get(getAdapterPosition()).getPostId() + "");
            context.getActivity().startActivity(i);
            context.getActivity().overridePendingTransition(R.anim.slide_up, 0);

        }
    }
}
