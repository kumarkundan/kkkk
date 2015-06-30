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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        final TextView message = (TextView) row.findViewById(R.id.profiletxt);
        message.setText(currentItem.getContent());
        final TextView to = (TextView) row.findViewById(R.id.profilefrom);
        to.setText(currentItem.getFrom());
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

