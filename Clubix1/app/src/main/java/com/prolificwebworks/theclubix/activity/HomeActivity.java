package com.prolificwebworks.theclubix.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidsx.rateme.OnRatingListener;
import com.androidsx.rateme.RateMeDialog;
import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.fragment.EventFragment;
import com.prolificwebworks.theclubix.fragment.FragmentDrawer;
import com.prolificwebworks.theclubix.fragment.HomeFragment;
import com.prolificwebworks.theclubix.fragment.LocationFragment;
import com.prolificwebworks.theclubix.utils.CircleTransform;
import com.prolificwebworks.theclubix.utils.Method;
import com.prolificwebworks.theclubix.utils.StaticValue;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = HomeActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    ImageView fbImage;
    TextView fbName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fbImage = (ImageView) findViewById(R.id.fbImage);
        fbName = (TextView) findViewById(R.id.fbName);

        Picasso.with(HomeActivity.this).load(getSharedPreferences(StaticValue.MyPref, Context.MODE_PRIVATE).getString(StaticValue.FbImagUrl, "")).transform(new CircleTransform()).into(fbImage);
        fbName.setText(getSharedPreferences(StaticValue.MyPref, Context.MODE_PRIVATE).getString(StaticValue.FbName, ""));

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        displayView(0);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                fragment = LocationFragment.newInstance("menu");
                title = "My Location";
                break;
            case 5:
                Method.feedbackApp(HomeActivity.this);
                break;
            case 6:
                Method.shareApp(HomeActivity.this);
                break;
            case 7:
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case 9:
                showCustomRateMeDialog();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showCustomRateMeDialog() {
        new RateMeDialog.Builder(getPackageName(), getString(R.string.app_name))
                .setHeaderBackgroundColor(getResources().getColor(R.color.colorAccent1))
                .setBodyBackgroundColor(getResources().getColor(android.R.color.background_light))
                .setBodyTextColor(getResources().getColor(android.R.color.black))
                .enableFeedbackByEmail("support@theclubix.com")
                .showAppIcon(R.mipmap.ic_launcher)
                .setShowShareButton(true)
                .setRateButtonBackgroundColor(getResources().getColor(R.color.colorAccent1))
                .setRateButtonPressedBackgroundColor(getResources().getColor(R.color.colorAccent2))
                .setOnRatingListener(new OnRatingListener() {
                    @Override
                    public void onRating(OnRatingListener.RatingAction action, float rating) {
//                        Toast.makeText(HomeActivity.this,
//                                "Rate Me action: " + action + " (rating: " + rating + ")", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        // Nothing to write
                    }
                })
                .build()
                .show(getFragmentManager(), "custom-dialog");
    }
}
