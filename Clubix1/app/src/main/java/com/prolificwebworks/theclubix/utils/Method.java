package com.prolificwebworks.theclubix.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

/**
 * Created by Akki on 10/27/2015.
 */
public class Method {

    public static void showAlert(Context context, String Text){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(Text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static void getDirection(Context context, String address){
        String map = "http://maps.google.co.in/maps?q=" + address;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        context.startActivity(i);
    }

    public static void feedbackApp(Context context) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@theclubix.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "I want to tell you about ");
        email.putExtra(Intent.EXTRA_TEXT, "Here comes your message");
        email.setType("message/rfc822");
        context.startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    public static void shareApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey, try out this awesome app to find out clubs, artists and parties nearby and join the party peeps.\nhttps://play.google.com/store/apps/details?id="
                        + context.getPackageName());
        context.startActivity(Intent.createChooser(intent,
                "Share to Friends"));
    }

    public static void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id="
                + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id="
                            + context.getPackageName())));
        }
    }


}
