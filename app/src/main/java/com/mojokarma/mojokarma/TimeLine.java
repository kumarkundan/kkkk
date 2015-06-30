package com.mojokarma.mojokarma;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeLine extends ActionBarActivity{

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
    private TimeLineItemAdapter mAdapter;

    TextView Date;

    Button to,from;



    /**
     * Initializes the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timrline);
       Toolbar   toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Date= (TextView) findViewById(R.id.date);
        getCurrentDate();

        // Create the Mobile Service Client instance, using the provided
        // Mobile Service URL and key
        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    this);


            // Get the Mobile Service Table instance to use
            mToDoTable = mClient.getTable(Applaud.class);

            mAdapter = new TimeLineItemAdapter(this, R.layout.row_list);
            ListView listViewToDo = (ListView) findViewById(R.id.listViewToDo);
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

            LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);

            @Override
            protected void onPreExecute() {
                // SHOW THE SPINNER WHILE LOADING FEEDS
                linlaHeaderProgress.setVisibility(View.VISIBLE);
            }


            //now list thing kkkkkkkkkkkkk
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MobileServiceList<Applaud> result = mToDoTable.execute().get();
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
                } catch (Exception exception) {
                    //createAndShowDialog(exception, "Error");
                    exception.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_apploud, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }

        if(id==R.id.action_notification){
            startActivity(new Intent(this,MainActivity.class));
        }


        if(id==R.id.action_refresh){
            startActivity(new Intent(this,Employee.class));
        }

        if(id==R.id.action_help){
            startActivity(new Intent(this,UserProfile.class));
        }

        return super.onOptionsItemSelected(item);
    }
    public void getCurrentDate(){

        final Calendar c = Calendar.getInstance();

        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String mm = month_date.format(c.getTime());
        int dd = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        Date.setText(new StringBuilder()
                .append(mm).append(" ")
                .append(dd));

    }

}
