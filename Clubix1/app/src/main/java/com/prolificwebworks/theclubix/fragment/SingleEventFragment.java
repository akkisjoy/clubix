package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.entities.AllEvents;
import com.prolificwebworks.theclubix.entities.EventData;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.prolificwebworks.theclubix.utils.DialogShower;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SingleEventFragment extends Fragment {

    CustomFont title, city, address, time, description, getDirection, bookNow, invite;
    ImageView imageView;
    String postId;
    DialogShower constants;

    public static SingleEventFragment newInstance(String postId) {
        SingleEventFragment fragment = new SingleEventFragment();
        Bundle args = new Bundle();
        args.putString("postId", postId);
        fragment.setArguments(args);

        return fragment;
    }

    public SingleEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postId = getArguments() != null ? getArguments().getString("postId") : "";

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_single_event, container, false);

        title = (CustomFont) viewGroup.findViewById(R.id.title);
        city = (CustomFont) viewGroup.findViewById(R.id.city);
        address = (CustomFont) viewGroup.findViewById(R.id.address);
        time = (CustomFont) viewGroup.findViewById(R.id.time);
        description = (CustomFont) viewGroup.findViewById(R.id.description);
        getDirection = (CustomFont) viewGroup.findViewById(R.id.get_direction);
        bookNow = (CustomFont) viewGroup.findViewById(R.id.book_now);
        invite = (CustomFont) viewGroup.findViewById(R.id.invite);
        imageView =(ImageView) viewGroup.findViewById(R.id.image_view);


        constants = new DialogShower(getActivity());
        constants.show();


        callServiceTogetData();


        return viewGroup;
    }

    private void callServiceTogetData() {

        Client.INSTANCE.getSingleEvent(postId, new Callback<AllEvents>() {
            @Override
            public void success(AllEvents allEvents, Response response) {

                constants.dismiss();
                updateUi(allEvents);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void updateUi(AllEvents allEvents) {

        EventData eventData = allEvents.getPostData().get(0);

        title.setText(eventData.getPost_title());
        city.setText(eventData.getEvent_city());
        address.setText(eventData.getEvent_address());
        time.setText(eventData.getEvent_start_date() + " " + eventData.getEvent_start_time());
        description.setText(eventData.getPost_content());
        Picasso.with(getActivity()).load(eventData.getFeatured_image()).into(imageView);

    }
}
