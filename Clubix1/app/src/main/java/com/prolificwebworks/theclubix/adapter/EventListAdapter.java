package com.prolificwebworks.theclubix.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.SingleEventActivity;
import com.prolificwebworks.theclubix.entities.EventData;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.prolificwebworks.theclubix.utils.EventTime;
import com.prolificwebworks.theclubix.utils.MyEnum;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vaibhav on 10/10/15.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    Fragment context;
    List<EventData> allEvents;

    public EventListAdapter(Fragment context, EventTime eventTime) {

        this.context = context;


        switch (eventTime) {
            case TODAY:

                allEvents = MyEnum.INSTANCE.getToday();
                break;

            case TOMORROW:

                allEvents = MyEnum.INSTANCE.getTomorrow();
                break;

            case LATER:

                allEvents = MyEnum.INSTANCE.getLater();
                break;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_recyclerview_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        EventData eventData = allEvents.get(position);

        holder.eventName.setText(eventData.getPost_title());
        holder.time.setText(eventData.getEvent_start_time());
        Picasso.with(context.getActivity()).load(eventData.getFeatured_image()).into(holder.imageBackground);

    }

    @Override
    public int getItemCount() {

        if (allEvents != null)
            return allEvents.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageBackground;
        public CustomFont eventName, time;

        public ViewHolder(View itemView) {
            super(itemView);

            imageBackground = (ImageView) itemView.findViewById(R.id.image_view);
            eventName = (CustomFont) itemView.findViewById(R.id.event_name);
            time = (CustomFont) itemView.findViewById(R.id.time);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

//            FragmentTransaction ft = context.getFragmentManager().beginTransaction();
//            SingleEventFragment fragment = new SingleEventFragment();
//            SingleEventFragment fragment = SingleEventFragment.newInstance(allEvents.get(getAdapterPosition()).getPostId());
//            fragment.show(context.getFragmentManager().beginTransaction(), "lol");

            Intent i = new Intent(context.getActivity().getApplicationContext(), SingleEventActivity.class);
            i.putExtra("postId", allEvents.get(getAdapterPosition()).getPostId() + "");
            context.getActivity().startActivity(i);
            context.getActivity().overridePendingTransition(R.anim.slide_up, 0);



//            fragment.show(fragment)
//            context.getFragmentManager().beginTransaction().replace(R.id.container_body, fragment).addToBackStack(null).commit();

        }
    }
}
