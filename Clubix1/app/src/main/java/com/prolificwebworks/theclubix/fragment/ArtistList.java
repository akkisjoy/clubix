package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.HomeActivity;
import com.prolificwebworks.theclubix.adapter.ArtistGridViewAdapter;
import com.prolificwebworks.theclubix.entities.Artist;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.DialogShower;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vaibhav on 27/10/15.
 */
public class ArtistList extends Fragment {


    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    DialogShower dialogShower;


    public ArtistList() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artist_gridview, container, false);

        ((HomeActivity)getActivity()).setActionBarTitle(getString(R.string.title_artist));

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        dialogShower = new DialogShower(getActivity());

        callServiceToGetData();


        return v;
    }

    private void callServiceToGetData() {

        dialogShower.show();

        Client.INSTANCE.getAllArtist(new Callback<Artist>() {
            @Override
            public void success(Artist artist, Response response) {


                dialogShower.dismiss();
                ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(ArtistList.this, artist.getPostData());
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(artistGridViewAdapter);


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
