package com.prolificwebworks.theclubix.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.entities.AllEvents;
import com.prolificwebworks.theclubix.entities.EventData;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.CustomFont;
import com.prolificwebworks.theclubix.utils.DialogShower;
import com.prolificwebworks.theclubix.utils.Method;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/31/2015.
 */
public class SingleEventActivity extends AppCompatActivity{

    CustomFont title, time, description, getDirection, bookNow, invite, clubName, artistName;
    ImageView imageView, backIcon;
    String postId, clubId, artistId;
    DialogShower constants;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_single_event);

        postId = getIntent().getStringExtra("postId");

        backIcon = (ImageView) findViewById(R.id.back_icon);
        title = (CustomFont) findViewById(R.id.title);
        time = (CustomFont) findViewById(R.id.time);
        description = (CustomFont) findViewById(R.id.description);
        getDirection = (CustomFont) findViewById(R.id.get_direction);
        bookNow = (CustomFont) findViewById(R.id.book_now);
        invite = (CustomFont) findViewById(R.id.invite);
        imageView = (ImageView) findViewById(R.id.image_view);
        clubName = (CustomFont) findViewById(R.id.club_name);
        artistName = (CustomFont) findViewById(R.id.artist_name);

        constants = new DialogShower(SingleEventActivity.this);
        constants.show();

        callServiceTogetData();
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

        final EventData eventData = allEvents.getPostData().get(0);

        title.setText(eventData.getPost_title());
        time.setText(eventData.getEvent_start_date() + " " + eventData.getEvent_start_time());
        description.setText(Html.fromHtml(eventData.getPost_content()));
        if (eventData.getEvent_artist().size()>0){
            artistName.setText(eventData.getEvent_artist().get(0).getArtist_title());
            artistId = eventData.getEvent_artist().get(0).getArtist_id();
        }else {
            artistName.setText("No Information");
            artistId = null;
        }
        clubId = eventData.getEvent_club_id();
        clubName.setText(eventData.getEvent_venue_name());

        Picasso.with(SingleEventActivity.this).load(eventData.getFeatured_image()).into(imageView);

        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Method.getDirection(SingleEventActivity.this, eventData.getEvent_address().trim());
            }
        });

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Method.shareApp(SingleEventActivity.this);
            }
        });

        clubName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SingleClubFragment fragment = SingleClubFragment.newInstance(eventData.getPostId());
//                fragment.show(getFragmentManager().beginTransaction(), "club");

                Intent i = new Intent(SingleEventActivity.this, SingleClubActivity.class);
                i.putExtra("postId", clubId);
                startActivity(i);
                overridePendingTransition(R.anim.slide_up, 0);
            }
        });

        artistName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (artistId != null){
                    Intent i = new Intent(SingleEventActivity.this, SingleArtistActivity.class);
                    i.putExtra("postId", artistId);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_up, 0);
                }
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();

            }
        });

    }
}
