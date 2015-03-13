package com.hrdi.survey.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class DialogConnect extends ProgressDialog {

    private AsyncTask task;

    public DialogConnect(Context context, AsyncTask task) {
        //Create Dialog from Super class
        super(context);

        //Set AsyncTask (ConnectServer) to Dialog
        this.task = task;
    }

    // On Dialog cancel
    public void cancel() {
        // cancel Server connection
        task.cancel(true);
        super.cancel();
    }

}