package com.prolificwebworks.theclubix.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.HomeActivity;
import com.prolificwebworks.theclubix.utils.StaticValue;

/**
 * Created by Akki on 10/7/2015.
 */

public class Splash3 extends Fragment {

    private TextView fbSkip;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            AccessToken accessToken = loginResult.getAccessToken();
            editor.putString(StaticValue.MyToken, String.valueOf(accessToken));
            editor.commit();

            Profile profile = Profile.getCurrentProfile();

            if (profile != null) {
                editor.putString(StaticValue.FbName, profile.getName());
//            editor.putString(StaticValue.FbUserName, profile.get);
                editor.putString(StaticValue.FbId, profile.getId());
//            editor.putString(StaticValue.FbEmail, profile.get);
                editor.putString(StaticValue.FbImagUrl, profile.getProfilePictureUri(100, 100) + "");
                editor.commit();
            }

            getFragmentManager().beginTransaction().replace(R.id.frameSplash,new FirstClubFragment()).commit();

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    public Splash3() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences(StaticValue.MyPref, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                editor.putString(StaticValue.MyToken, String.valueOf(currentAccessToken));
                editor.commit();
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    editor.putString(StaticValue.FbName, currentProfile.getName());
//            editor.putString(StaticValue.FbUserName, profile.get);
                    editor.putString(StaticValue.FbId, currentProfile.getId());
//            editor.putString(StaticValue.FbEmail, profile.get);
                    editor.putString(StaticValue.FbImagUrl, currentProfile.getProfilePictureUri(100, 100) + "");
                    editor.commit();
                }
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_3, container, false);
        fbSkip = (TextView) v.findViewById(R.id.fbSkip);
        fbSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(android.R.anim.fade_in, 0);
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, facebookCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(getActivity().getApplicationContext());
    }

    @Override
    public void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(getActivity().getApplicationContext());
    }
}
