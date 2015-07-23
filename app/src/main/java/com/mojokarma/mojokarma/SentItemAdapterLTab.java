package com.mojokarma.mojokarma;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

/**
 * Created by kundan on 6/23/2015.
 */

public class SentItemAdapterLTab extends ArrayAdapter<User> {


    private MobileServiceTable<Applaud> mUser;

    private MobileServiceClient mClient;

    /**
     *
     * Adapter context
     */
    Context mrContext;

    /**
     * Adapter View layout
     */
    int mrLayoutResourceId;

    LayoutInflater mrInflater;
    public SentItemAdapterLTab(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mrContext = context;
        mrLayoutResourceId = layoutResourceId;
        mrInflater = LayoutInflater.from(mrContext);
    }
    /**
     * Returns the view for a specific item on the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final User currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mrContext).getLayoutInflater();
            row = inflater.inflate(mrLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);
        //final TextView message = (TextView) row.findViewById(R.id.tabdescription);
        // message.setText(currentItem.getContent());
        final TextView name = (TextView) row.findViewById(R.id.profilefrom);
        name.setText(currentItem.getContent());

        final TextView designation = (TextView) row.findViewById(R.id.desig);
        //Log.v("Here ISSSSSSSSSSSSSSS",currentItem.getDesignation());
        designation.setText(currentItem.getDesignation());

        final TextView txt= (TextView) row.findViewById(R.id.ltabtxt);

        final int[] result = new int[1];

        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    getContext());

            mUser = mClient.getTable(Applaud.class);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        result[0] =
                                mUser.where().field("from").eq(currentItem.getContent()).execute().get().getTotalCount();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    // gb.setDesignation(desig[0]);


                    txt.setText(String.valueOf(result[0]));

                }
            }.execute();


        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // checkBox.setChecked(false);
        //checkBox.setEnabled(true);



       /* if (convertView == null) {

            row = mInflater.inflate(R.layout.row_list, null);
        }*/

      /*  Button myButton = (Button) row.findViewById( R.id.tabto );
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Applaud ad=new Applaud();
                String toname=currentItem.getTo();
                Toast.makeText(mrContext, toname, Toast.LENGTH_SHORT).show();
                Intent in=new Intent(mrContext,Profile.class);
                in.putExtra("toname",toname);
                mrContext.startActivity(in);
            }
        });
        Button myButton2 = (Button) row.findViewById( R.id.from );
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Applaud ad=new Applaud();
                String toname = currentItem.getTo();
                Intent in=new Intent(mrContext,Profile.class);
                in.putExtra("toname",toname);
                mrContext.startActivity(in);
            }
        });*/


        return row;
    }
}
