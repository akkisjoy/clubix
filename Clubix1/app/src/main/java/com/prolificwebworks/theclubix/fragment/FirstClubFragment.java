package com.prolificwebworks.theclubix.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.cpicker.CollectionPicker;
import com.prolificwebworks.theclubix.cpicker.Item;
import com.prolificwebworks.theclubix.cpicker.OnItemClickListener;
import com.prolificwebworks.theclubix.entities.Club;
import com.prolificwebworks.theclubix.entities.ClubData;
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
public class FirstClubFragment extends Fragment implements View.OnClickListener {

    CollectionPicker mPicker;
    int counter = 0;
    ImageView next;
    Set<String> clubIdsList;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.club_collection, container, false);

        clubIdsList = new HashSet<>();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        mPicker = (CollectionPicker) v.findViewById(R.id.club_picker);
        next = (ImageView) v.findViewById(R.id.iv_next);
        next.setOnClickListener(this);
        callServiceTogetData();

        return v;
    }

    private void callServiceTogetData() {

        Client.INSTANCE.getAllClubs(new Callback<Club>() {
            @Override
            public void success(Club allEvents, Response response) {
                List<Item> items = new ArrayList<>();
                Log.e("setItems", items.size() + "");
                for (int i = 0; i < allEvents.getPostData().size(); i++) {
                    ClubData clubData = allEvents.getPostData().get(i);
                    items.add(new Item("" + clubData.getCub_id(), clubData.getCub_name()));
                    Log.e(allEvents.getPostData().get(i).getCub_id(), allEvents.getPostData().get(i).getCub_name() + "");
                }

                if (items.size() > 0) {
                    progressDialog.dismiss();
                    Log.e("setItems", items.size() + "");
                    mPicker.setItems(items);
                    mPicker.drawItemView();
                    mPicker.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onClick(Item item, int position) {
                            if (item.isSelected) {
                                clubIdsList.add(item.id);
                                counter++;
                            } else {
                                clubIdsList.remove(item.id);
                                counter--;
                            }
                            MyEnum.INSTANCE.setClub(clubIdsList);
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
                if (MyEnum.INSTANCE.getClub() != null && MyEnum.INSTANCE.getClub().size() > 0 && counter > 3) {

                    String ids = android.text.TextUtils.join(",", MyEnum.INSTANCE.getClub());

                    Client.INSTANCE.submitClubIds(ids, new Callback<Response>() {
                        @Override
                        public void success(Response response, Response response2) {
                            getFragmentManager().beginTransaction().replace(R.id.frameSplash, new FirstArtistFragment()).commit();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                } else {
                    getFragmentManager().beginTransaction().replace(R.id.frameSplash, new FirstArtistFragment()).commit();
                }
                break;
        }
    }
}
