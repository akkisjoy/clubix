package com.prolificwebworks.theclubix.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.entities.FacebookRegister;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.StaticValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/7/2015.
 */

public class Splash3 extends Fragment {

    //    private TextView fbSkip;
    private String fbName, fbUserName = "", fbEmail = "", fbId, fbImageUrl;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    FacebookRegister facebookRegister;
    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            AccessToken accessToken = loginResult.getAccessToken();
            editor.putString(StaticValue.MyToken, String.valueOf(accessToken)).apply();

            Profile profile = Profile.getCurrentProfile();
//            GraphRequest.newMeRequest(
//                    loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject json, GraphResponse response) {
//                            if (response.getError() != null) {
//                                // handle error
//                                System.out.println("ERROR");
//                            } else {
//                                System.out.println("Success");
//                                try {
//
//                                    String jsonresult = String.valueOf(json);
//                                    System.out.println("JSON Result" + jsonresult);
//
//                                    if (json.getString("email") != null) {
//                                        fbEmail = json.getString("email");
//                                    } else {
//                                        fbEmail = "";
//                                    }
//
//
//                                    fbId = json.getString("id");
//                                    String str_firstname = json.getString("first_name");
//                                    String str_lastname = json.getString("last_name");
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//
//                    }).executeAsync();

            if (profile != null) {

                fbName = profile.getName();
                fbId = profile.getId();
                fbImageUrl = profile.getProfilePictureUri(200, 200) + "";

                facebookRegister = new FacebookRegister();
                facebookRegister.setName(fbName);
                facebookRegister.setEmail("");
                facebookRegister.setFbid(fbId);
                facebookRegister.setImageUrl(fbImageUrl);
                facebookRegister.setUserName("");

//                callFacebookService();

                editor.putString(StaticValue.FbName, profile.getName()).apply();
//            editor.putString(StaticValue.FbUserName, profile.get);
                editor.putString(StaticValue.FbId, profile.getId()).apply();
//            editor.putString(StaticValue.FbEmail, profile.get);
                editor.putString(StaticValue.FbImagUrl, profile.getProfilePictureUri(200, 200) + "").apply();
            }

            LocationFragment locationFragment = LocationFragment.newInstance("splash");
            getFragmentManager().beginTransaction().replace(R.id.frameSplash, locationFragment).commit();
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
                editor.putString(StaticValue.MyToken, String.valueOf(currentAccessToken)).apply();

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    editor.putString(StaticValue.FbName, currentProfile.getName()).apply();
//            editor.putString(StaticValue.FbUserName, profile.get);
                    editor.putString(StaticValue.FbId, currentProfile.getId()).apply();
//            editor.putString(StaticValue.FbEmail, profile.get);
                    editor.putString(StaticValue.FbImagUrl, currentProfile.getProfilePictureUri(200, 200) + "").apply();
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
//        fbSkip = (TextView) v.findViewById(R.id.fbSkip);
//        fbSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
//                getActivity().startActivity(i);
//                getActivity().overridePendingTransition(android.R.anim.fade_in, 0);
//                getActivity().finish();
//            }
//        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        List<String> permissionNeeds = Arrays.asList("email", "public_profile");
        loginButton.setReadPermissions(permissionNeeds);
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

    void callFacebookService() {
        Client.INSTANCE.registerUser(facebookRegister, new Callback<FacebookRegister>() {
            @Override
            public void success(FacebookRegister facebookRegister, Response response) {
                Log.e("FacebookRegister", "Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RetrofitError", error.getMessage());
            }
        });
    }
}