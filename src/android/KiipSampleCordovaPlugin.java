package com.bhnath.kiip.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.bhnath.kiipsamplelibrary.HackerNews;

public class KiipSampleCordovaPlugin extends CordovaPlugin {
    HackerNews hackerNews;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	if (action.equals("initSDK")) {
	    this.initSDK(callbackContext);
	    return true;
	}

	if (action.equals("showHackerNews")) {
	    this.showHackerNews(callbackContext);
	    return true;
	}
	return false;
    }

    private void initSDK(final CallbackContext callbackContext) {
	if (hackerNews == null) {
	    hackerNews = new HackerNews();
	}

	cordova.getThreadPool().execute(new Runnable() {
            public void run() {
            	Context context = cordova.getActivity();
				hackerNews.initSDK(context);
				callbackContext.success("initSDK called successfully.");
            }
        });
    }

    private void showHackerNews(final CallbackContext callbackContext) {
	if (hackerNews == null) {
	    callbackContext.error("initSDK not called.");
	    return;
	}
	cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                hackerNews.showHackerNews();
                callbackContext.success("showHackerNews called successfully.");
            }
        });
    }
}