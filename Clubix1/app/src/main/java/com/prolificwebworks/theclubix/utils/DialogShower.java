package com.prolificwebworks.theclubix.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by vaibhav on 10/10/15.
 */
public class DialogShower {


    ProgressDialog progressDialog;

    public DialogShower(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("please wait");

    }

    public void show() {
        progressDialog.show();
    }

    public void dismiss() {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }


}
