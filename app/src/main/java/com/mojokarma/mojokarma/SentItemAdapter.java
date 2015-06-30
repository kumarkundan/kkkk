package com.mojokarma.mojokarma;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by kundan on 6/23/2015.
 */
public class SentItemAdapter extends ArrayAdapter<Applaud> {
    /**
     * Adapter context
     */
    Context mrContext;

    /**
     * Adapter View layout
     */
    int mrLayoutResourceId;

    LayoutInflater mrInflater;
    public SentItemAdapter(Context context, int layoutResourceId) {
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

        final Applaud currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mrContext).getLayoutInflater();
            row = inflater.inflate(mrLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);
        final TextView message = (TextView) row.findViewById(R.id.tabdescription);
        message.setText(currentItem.getContent());
        final TextView to = (TextView) row.findViewById(R.id.tabto);
        to.setText(currentItem.getTo());
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


