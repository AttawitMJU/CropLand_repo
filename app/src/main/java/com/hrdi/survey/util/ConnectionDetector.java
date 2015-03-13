package com.hrdi.survey.util;

/**
 * Created by attawit on 1/19/15 AD.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
    private Context _context;

    public ConnectionDetector(Context _context) {
        this._context = _context;
    }

    public boolean isNetworkAvailable() {
        boolean outcome = false;

        if (_context != null) {
            ConnectivityManager cm = (ConnectivityManager) _context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

            for (NetworkInfo tempNetworkInfo : networkInfos) {

                /**
                 * Can also check if the user is in roaming
                 */
                if (tempNetworkInfo.isConnected()) {
                    outcome = true;
                    break;
                }
            }
        }

        return outcome;
    }
}