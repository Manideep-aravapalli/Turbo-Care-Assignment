package com.example.turbocareassignment.util;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.turbocareassignment.R;

public class ProgressCaller extends Application {

    public static Dialog dialog;

    public static void showProgress(Context context) {
        try {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (dialog != null) {
                dialog.setContentView(R.layout.dialog_view);
                dialog.setCancelable(false);
                dialog.show();
            } else {
                dialog = new Dialog(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgress() {
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    //Log.e("TAG", "is Showing: ");
                    dialog.dismiss();
                    dialog.hide();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
