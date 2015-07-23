package com.mojokarma.mojokarma;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

/**
 * Created by kundan on 6/23/2015.
 */

    public class ProfileItemAdapter extends ArrayAdapter<Applaud> {

    /**
     * Adapter context
     */
    Context mrContext;

    /**
     * Adapter View layout
     */
    int mrLayoutResourceId;

    private MobileServiceTable<User> mUser;

    private MobileServiceClient mClient;

    LayoutInflater mrInflater;
    public ProfileItemAdapter(Context context, int layoutResourceId) {
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

        final TextView to = (TextView) row.findViewById(R.id.profilefrom);
        final TextView message = (TextView) row.findViewById(R.id.profiletxt);

        message.setText(currentItem.getContent());

        to.setText(currentItem.getFrom());

        Log.v("FINALLY OOOUUTT", "");
        //Profile itemp=new Profile();
//        itemp.giveMeDesignation(row,currentItem);

        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    getContext());


        // Get the Mobile Service Table instance to use
        final String[] design = new String[1];
        mUser = mClient.getTable(User.class);
        final TextView designation = (TextView) row.findViewById(R.id.designnnnnnattiion);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MobileServiceList<User> result =
                            mUser.where().field("name").eq(currentItem.getFrom()).execute().get();
                    for (User item : result) {
                        // Log.i(TAG, "Read object with ID " + item.id);
                        design[0] = item.getDesignation();
                        Log.v("FINALLY DESIGNATION IS", design[0]);

                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // gb.setDesignation(desig[0]);
                designation.setText(design[0]);
            }
        }.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }






        Bitmap bm2 = BitmapFactory.decodeResource(row.getResources(),
                R.mipmap.gomez);

        Bitmap resizedBitmap2 = Bitmap.createScaledBitmap(bm2, 200, 200, false);
        Bitmap conv_bm2=getCircleBitmap(resizedBitmap2, 100);
        // set circle bitmap
        ImageView mImage2 = (ImageView)row. findViewById(R.id.PROFILEimageView);
        mImage2.setImageBitmap(conv_bm2);


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
    private Bitmap getCircleBitmap(Bitmap bitmap , int pixels) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(),bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(100, 100, 86, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }
}

