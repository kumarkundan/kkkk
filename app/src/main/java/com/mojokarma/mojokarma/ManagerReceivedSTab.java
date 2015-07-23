package com.mojokarma.mojokarma;

/**
 * Created by kundan on 6/20/2015.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;


public class ManagerReceivedSTab extends Fragment {

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
    private RecievedItemAdapterSTab mAdapter;

    // Button to,from;

    //String user;

    Globals gb = Globals.getInstance();
    /**
     * Initializes the activity
     */

    private void refreshItemsFromTable(final View v) {


        // Get the items that weren't marked as completed and add them in the
        // adapter

        new AsyncTask<Void, Void, Void>() {

            //progress bar thing kkkkkkkkkkkkkkk

            LinearLayout linlaHeaderProgress = (LinearLayout) v.findViewById(R.id.linlaHeaderProgressstab);

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
                    getActivity().runOnUiThread(new Runnable() {

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
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_manager_received_stab,container,false);
        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    getActivity());


            // Get the Mobile Service Table instance to use
            mToDoTable = mClient.getTable(User.class);

            mAdapter = new RecievedItemAdapterSTab(getActivity(), R.layout.row_employeeliststab);
            ListView listViewToDo = (ListView) v.findViewById(R.id.listViewRecievedSTab);
            listViewToDo.setAdapter(mAdapter);

            // Load the items from the Mobile Service
            refreshItemsFromTable(v);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return v;
    }


}

