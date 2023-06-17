package com.example.myapplication.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.myapplication.R;
import com.example.myapplication.services.ApplicationService;

public class LoadingDialog {
    private final Activity activity;
    private AlertDialog loadDialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        ApplicationService applicationService = ApplicationService.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        loadDialog = builder.create();
        loadDialog.show();
    }

    public void dismiss() {
        loadDialog.dismiss();
    }
}
