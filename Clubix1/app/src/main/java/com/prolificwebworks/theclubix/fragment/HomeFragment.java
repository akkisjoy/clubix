package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.adapter.EventPagerAdapter;
import com.prolificwebworks.theclubix.entities.ArtistImage;
import com.prolificwebworks.theclubix.entities.ClubImage;
import com.prolificwebworks.theclubix.entities.HomeImage;
import com.prolificwebworks.theclubix.entities.HomeImageData;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.StaticValue;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/9/2015.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener {

    private SliderLayout mDemoSlider;
    private ImageView homeClub, homeArtist, homeEvent;
    private HashMap<String, String> url_maps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);

        url_maps = new HashMap<>();

        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);
        homeArtist = (ImageView) v.findViewById(R.id.homeArtist);
        homeClub = (ImageView) v.findViewById(R.id.homeClub);
        homeEvent = (ImageView) v.findViewById(R.id.homeEvent);

        homeArtist.setOnClickListener(this);
        homeClub.setOnClickListener(this);
        homeEvent.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getHomeData();
    }

    void setSlider(HashMap<String, String> url_maps) {
        this.url_maps = url_maps;
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
//                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeArtist:
                Toast.makeText(getActivity(), "Artist clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.homeClub:
                Toast.makeText(getActivity(), "Club clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.homeEvent:
                getFragmentManager().beginTransaction().replace(R.id.container_body, new EventFragment()).commit();
                break;
        }
    }

    private void getHomeData() {
        Client.INSTANCE.getHomePageImage(new Callback<HomeImage>() {
            @Override
            public void success(HomeImage homeImage, Response response) {

                Log.e("Success Size", homeImage.getPostData().get(0).getHeader_image().size()+"");
                for (int i = 0; i < homeImage.getPostData().get(0).getHeader_image().size(); i++) {
                    url_maps.put("Image" + i, homeImage.getPostData().get(0).getHeader_image().get(i));
                }

                setSlider(url_maps);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RetrofitError", error.getMessage());
            }
        });

        Client.INSTANCE.getArtistImage(new Callback<ArtistImage>() {
            @Override
            public void success(ArtistImage artistImage, Response response) {
                Log.e("Success String", artistImage.getPostData().get(0).getArtist_image() + "");
                Picasso.with(getActivity()).load(artistImage.getPostData().get(0).getArtist_image()).into(homeArtist);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        Client.INSTANCE.getClubImage(new Callback<ClubImage>() {
            @Override
            public void success(ClubImage clubImage, Response response) {
                Log.e("Success String", clubImage.getPostData().get(0).getClub_image() + "");
                Picasso.with(getActivity()).load(clubImage.getPostData().get(0).getClub_image()).into(homeClub);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onDestroy() {
        Picasso.with(getActivity()).cancelRequest(homeClub);
        Picasso.with(getActivity()).cancelRequest(homeArtist);
        super.onDestroy();
    }
}
