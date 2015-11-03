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
import com.prolificwebworks.theclubix.entities.Restaurant;
import com.prolificwebworks.theclubix.entities.RestaurantData;
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
public class SingleClubActivity extends AppCompatActivity {

    TextView followClub, time, clubDescription, getDirection;
    ImageView imageView, backIcon;
    String postId, userId = "1";
    DialogShower constants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_single_club);

        postId = getIntent().getStringExtra("postId");

        backIcon = (ImageView) findViewById(R.id.back_icon);
        time = (CustomFont) findViewById(R.id.time);
        getDirection = (CustomFont) findViewById(R.id.get_direction);
        imageView = (ImageView) findViewById(R.id.image_view);
        followClub = (TextView) findViewById(R.id.follow_club);
        clubDescription = (TextView) findViewById(R.id.club_description);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        followClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client.INSTANCE.followPost(postId, userId, new Callback<AllEvents>() {
                    @Override
                    public void success(AllEvents allEvents, Response response) {
                        if (allEvents.getMessage().equalsIgnoreCase("success")) {
                            Toast.makeText(SingleClubActivity.this, "This artist is followed by you now", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });

        constants = new DialogShower(SingleClubActivity.this);
        constants.show();

        callServiceTogetData();

    }

    private void callServiceTogetData() {

        Client.INSTANCE.getSingleRestaurants(postId, new Callback<Restaurant>() {
            @Override
            public void success(Restaurant restaurant, Response response) {

                constants.dismiss();
                updateUi(restaurant);
                Log.e("Success", "Success");

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("NSuccess", error.getMessage());

            }
        });
    }

    private void updateUi(Restaurant restaurant) {

        final RestaurantData restaurantData = restaurant.getPostData().get(0);

        time.setText(restaurantData.getPost_date() + " " + restaurantData.getBookingstarting_time());
        clubDescription.setText(Html.fromHtml(restaurantData.getPost_content()));

        Picasso.with(SingleClubActivity.this).load(restaurantData.getRestaurant_logo()).into(imageView);

        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Method.getDirection(SingleClubActivity.this, restaurantData.getGeolocation_formatted_address().trim());
            }
        });

    }
}
