package com.mojokarma.mojokarma;

/**
 * Created by kundan on 6/20/2015.
 */
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;


public class Sent extends ActionBarActivity {
    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<Applaud> mToDoTable;

    /**
     * Adapter to sync the items list with the view
     */
    private SentItemAdapter mAdapter;

    Button to,from;

    String user;

    Globals gb = Globals.getInstance();

    /**
     * Initializes the activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent);

      /*  Globals gb = (Globals) getApplicationContext();
        user = gb.getClickedUser();
        Log.v( "SomeLabel", "X is: " +user );*/

        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    this);


            // Get the Mobile Service Table instance to use
            mToDoTable = mClient.getTable(Applaud.class);

            mAdapter = new SentItemAdapter(this, R.layout.tab_row_list);
            ListView listViewToDo = (ListView) findViewById(R.id.listViewSent);
            listViewToDo.setAdapter(mAdapter);

            // Load the items from the Mobile Service
            refreshItemsFromTable();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
    private void refreshItemsFromTable() {


        // Get the items that weren't marked as completed and add them in the
        // adapter

        new AsyncTask<Void, Void, Void>() {

            //progress bar thing kkkkkkkkkkkkkkk

            LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgresssent);

            @Override
            protected void onPreExecute() {
                // SHOW THE SPINNER WHILE LOADING FEEDS
                linlaHeaderProgress.setVisibility(View.VISIBLE);
            }


            //now list thing kkkkkkkkkkkkk
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String x=gb.getValue();

                    final MobileServiceList<Applaud> result = mToDoTable.where().field("from").eq(x).execute().get();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter.clear();
                            for (Applaud item : result) {
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
