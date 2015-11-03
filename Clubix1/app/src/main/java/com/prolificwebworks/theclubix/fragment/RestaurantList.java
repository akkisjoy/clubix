package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.HomeActivity;
import com.prolificwebworks.theclubix.adapter.RestaurantAdapter;
import com.prolificwebworks.theclubix.entities.Restaurant;
import com.prolificwebworks.theclubix.entities.RestaurantData;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.DialogShower;
import com.prolificwebworks.theclubix.utils.MyEnum;
import com.prolificwebworks.theclubix.utils.RestaurantType;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vaibhav on 25/10/15.
 */
public class RestaurantList extends Fragment {


    RestaurantType restaurantType;
    RecyclerView recyclerView;
    DialogShower constants;


    public static RestaurantList newInstance() {
        RestaurantList fragment = new RestaurantList();

        return fragment;
    }


    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public RestaurantList() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        ((HomeActivity)getActivity()).setActionBarTitle(getString(R.string.title_club));

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        constants = new DialogShower(getActivity());

        callServiceTogetData(restaurantType);


        return v;
    }

    private void callServiceTogetData(RestaurantType restaurantType) {

//        constants.dismiss();

        switch (restaurantType) {
            case CLUB:
                if (MyEnum.INSTANCE.getRestaurant(RestaurantType.CLUB) != null) {

                    setAdapterView(MyEnum.INSTANCE.getRestaurant(RestaurantType.CLUB));

                } else SetRestaurantData();
                break;

            case LOUNGE:
                if (MyEnum.INSTANCE.getRestaurant(RestaurantType.LOUNGE) != null) {
                    setAdapterView(MyEnum.INSTANCE.getRestaurant(RestaurantType.LOUNGE));

                } else SetRestaurantData();

                break;
            case PUB:
                if (MyEnum.INSTANCE.getRestaurant(RestaurantType.PUB) != null) {
                    setAdapterView(MyEnum.INSTANCE.getRestaurant(RestaurantType.PUB));

                } else SetRestaurantData();
                break;

        }


    }

    private void setAdapterView(List<RestaurantData> restaurantDatas) {

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(RestaurantList.this, restaurantDatas);
        recyclerView.setAdapter(restaurantAdapter);
    }


    private void SetRestaurantData() {

        constants.show();
        Client.INSTANCE.getAllRestaurants(new Callback<Restaurant>() {
            @Override
            public void success(Restaurant restaurant, Response response) {

                Log.e("mytag", restaurant.toString());
                constants.dismiss();
                MyEnum.INSTANCE.setRestaurant(restaurant.getPostData());
                setAdapterView(MyEnum.INSTANCE.getRestaurant(restaurantType));


            }

            @Override
            public void failure(RetrofitError error) {

                Log.e("error",error.getMessage());

            }
        });
    }


}
