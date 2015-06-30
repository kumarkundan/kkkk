package com.mojokarma.mojokarma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kundan on 6/10/2015.
 */
public class TimeLineItemAdapter extends ArrayAdapter<Applaud> {
    /**
     * Adapter context
     */
    Context mContext;

    Globals gb = Globals.getInstance();

    /**
     * Adapter View layout
     */
    int mLayoutResourceId;

    LayoutInflater mInflater;

    public TimeLineItemAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * Returns the view for a specific item on the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final Applaud currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);
        final TextView message = (TextView) row.findViewById(R.id.txt);
        message.setText(currentItem.getContent());
        final TextView from = (TextView) row.findViewById(R.id.from);
        from.setText(currentItem.getFrom());
        final TextView to = (TextView) row.findViewById(R.id.to);
        to.setText(currentItem.getTo());
        // checkBox.setChecked(false);
        //checkBox.setEnabled(true);



       /* if (convertView == null) {

            row = mInflater.inflate(R.layout.row_list, null);
        }*/

        TextView myButton = (TextView) row.findViewById( R.id.to );
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Applaud ad=new Applaud();
                String toname = currentItem.getTo();
                // Globals gb = new Globals();
                gb.setValue(toname);
                Toast.makeText(mContext, toname, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(mContext, Profile.class);
                String x = gb.getValue();
                Log.v("FROM TIMELINE ADAPTER", "X is: " + x);
                mContext.startActivity(in);
            }
        });
        TextView myButton2 = (TextView) row.findViewById( R.id.from );
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Applaud ad=new Applaud();
                String fromname = currentItem.getFrom();
               // Globals gb = new Globals();
                gb.setValue(fromname);
                Intent in=new Intent(mContext,Profile.class);
                String x=gb.getValue();
                Log.v("FROM TIMELINE ADAPTER", "X is: " + x);
                mContext.startActivity(in);

            }
        });


        Bitmap bm2 = BitmapFactory.decodeResource(row.getResources(),
                R.mipmap.kristy);

        Bitmap resizedBitmap2 = Bitmap.createScaledBitmap(bm2, 200, 200, false);
        Bitmap conv_bm2=getCircleBitmap(resizedBitmap2, 100);
        // set circle bitmap
        ImageView mImage2 = (ImageView)row. findViewById(R.id.imageView2);
        mImage2.setImageBitmap(conv_bm2);

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
