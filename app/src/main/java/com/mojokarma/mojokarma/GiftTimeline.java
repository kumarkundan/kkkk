package com.mojokarma.mojokarma;

/**
 * Created by kundan on 6/20/2015.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;


public class GiftTimeline extends ActionBarActivity {

    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<User> mToDoTable;

    /**
     * Adapter to sync the items list with the view
     */
    private GiftTimelineItemAdapter mAdapter;

    // Button to,from;

    //String user;

    Globals gb = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gifttimeline);
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);




        // Create the Mobile Service Client instance, using the provided
        // Mobile Service URL and key
        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    this);


            mToDoTable = mClient.getTable(User.class);

            mAdapter = new GiftTimelineItemAdapter(this, R.layout.row_gift);
            ListView listViewToDo = (ListView) findViewById(R.id.glistViewToDo);
            listViewToDo.setAdapter(mAdapter);

            // Load the items from the Mobile Service
            refreshItemsFromTable();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

    /**
     * Initializes the activity
     */


    private void refreshItemsFromTable() {


        // Get the items that weren't marked as completed and add them in the
        // adapter

        new AsyncTask<Void, Void, Void>() {

            //progress bar thing kkkkkkkkkkkkkkk

            LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.glinlaHeaderProgress);

            @Override
            protected void onPreExecute() {
                // SHOW THE SPINNER WHILE LOADING FEEDS
                linlaHeaderProgress.setVisibility(View.VISIBLE);
            }


            //now list thing kkkkkkkkkkkkk
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // String x=gb.getValue();

                    final MobileServiceList<User> result = mToDoTable.execute().get();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter.clear();
                            for (User item : result) {
                                mAdapter.add(item);

                            }
                            linlaHeaderProgress.setVisibility(View.GONE);
                        }
                    });
                }  catch (Exception exception) {
                    //createAndShowDialog(exception, "Error");
                    exception.printStackTrace();
                }
                return null;
            }
        }.execute();

    }
}

