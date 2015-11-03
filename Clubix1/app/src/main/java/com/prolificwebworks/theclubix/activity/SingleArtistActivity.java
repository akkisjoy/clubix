package com.prolificwebworks.theclubix.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.entities.AllEvents;
import com.prolificwebworks.theclubix.entities.Artist;
import com.prolificwebworks.theclubix.entities.ArtistData;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.DialogShower;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/31/2015.
 */
public class SingleArtistActivity extends AppCompatActivity {
    TextView followArtist,artistDescription, upcoming;
    ImageView imageView, backIcon;
    String postId;
    String userId = "1";
    DialogShower constants;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_single_artist);

        postId = getIntent().getStringExtra("postId");

        followArtist = (TextView) findViewById(R.id.follow_artist);
        artistDescription = (TextView) findViewById(R.id.artist_description);
        upcoming = (TextView) findViewById(R.id.upcoming_events);
        imageView = (ImageView) findViewById(R.id.image_view);
        backIcon = (ImageView) findViewById(R.id.back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        constants = new DialogShower(this);
        constants.show();

        callServiceTogetData();
    }



    private void callServiceTogetData() {

        Log.e("int postId", "" + Integer.parseInt(postId));

        Client.INSTANCE.getSingleArtist(Integer.parseInt(postId), new Callback<Artist>() {
            @Override
            public void success(Artist artist, Response response) {

                constants.dismiss();
                updateUi(artist);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void updateUi(Artist artist) {

        final ArtistData artistData = artist.getPostData().get(0);

        artistDescription.setText(Html.fromHtml(artistData.getPost_content()));
        Picasso.with(SingleArtistActivity.this).load(artistData.getFeatured_image()).into(imageView);

        followArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Client.INSTANCE.followPost(postId, userId, new Callback<AllEvents>() {
                    @Override
                    public void success(AllEvents allEvents, Response response) {
                        if (allEvents.getMessage().equalsIgnoreCase("success")) {
                            Toast.makeText(SingleArtistActivity.this, "This artist is followed by you now", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
