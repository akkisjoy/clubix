package com.prolificwebworks.theclubix.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.HomeActivity;
import com.prolificwebworks.theclubix.cpicker.CollectionPicker;
import com.prolificwebworks.theclubix.cpicker.Item;
import com.prolificwebworks.theclubix.cpicker.OnItemClickListener;
import com.prolificwebworks.theclubix.entities.Artist;
import com.prolificwebworks.theclubix.entities.ArtistData;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.MyEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/16/2015.
 */
public class FirstArtistFragment extends Fragment implements View.OnClickListener {
    CollectionPicker mPicker;
    int counter = 0;
    ImageView next;
    Set<String> artistIdsList;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artist_collection, container, false);

        artistIdsList = new HashSet<>();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        mPicker = (CollectionPicker) v.findViewById(R.id.artist_picker);
        next = (ImageView) v.findViewById(R.id.iv_next);
        next.setOnClickListener(this);
        callServiceTogetData();

        return v;
    }

    private void callServiceTogetData() {

        Client.INSTANCE.getAllArtist(new Callback<Artist>() {
            @Override
            public void success(Artist allEvents, Response response) {
                List<Item> items = new ArrayList<>();

                Log.e("setItems", items.size() + "");


                for (int i = 0; i < allEvents.getPostData().size(); i++) {

                    ArtistData artistData = allEvents.getPostData().get(i);
                    items.add(new Item("" + artistData.getPostId(), artistData.getPost_title()));
                    Log.e("" + artistData.getPostId(), artistData.getArtist_events());
                }
                progressDialog.dismiss();

                if (items.size() > 0) {
                    Log.e("setItems", items.size() + "");
                    mPicker.setItems(items);
                    mPicker.drawItemView();
                    mPicker.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onClick(Item item, int position) {
                            if (item.isSelected) {
                                artistIdsList.add(item.id);
                                counter++;
                            } else {
                                artistIdsList.remove(item.id);
                                counter--;
                            }
                            MyEnum.INSTANCE.setArtist(artistIdsList);
//                            Toast.makeText(AllClubActivity.this, counter + " Items Selected", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.e("letItems", items.size() + "");
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_next:
                if (MyEnum.INSTANCE.getArtist() != null && MyEnum.INSTANCE.getArtist().size() > 0 || counter > 3) {

                    String ids = android.text.TextUtils.join(",", MyEnum.INSTANCE.getArtist());

                    Client.INSTANCE.submitArtistIds(ids, new Callback<Response>() {
                        @Override
                        public void success(Response response, Response response2) {
                            Intent i = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                            getActivity().startActivity(i);
                            getActivity().overridePendingTransition(android.R.anim.fade_in, 0);
                            getActivity().finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                } else {
                    // no items selected message to be displayed
                    Intent i = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                    getActivity().startActivity(i);
                    getActivity().overridePendingTransition(android.R.anim.fade_in, 0);
                    getActivity().finish();
                }
                break;
        }
    }
}

