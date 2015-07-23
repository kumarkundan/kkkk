package com.mojokarma.mojokarma;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.mojokarma.mojokarma.javapapers.android.twitterfeedreader.app.TwitterAsyncTask;
import com.mojokarma.mojokarma.javapapers.android.util.AndroidNetworkUtility;


public class TweetsUserTab extends Fragment{


    public class MainActvity extends ListActivity{

    final static String twitterScreenName = "kkajnabi";
    final static String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(getActivity())) {
            new TwitterAsyncTask().execute(twitterScreenName,this);
        } else {
            Log.v(TAG, "Network not Available!");
        }
    }
    }
}


