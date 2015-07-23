

package com.mojokarma.mojokarma;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

/**
 * Created by kundan on 6/4/2015.
 */
public class Profile extends ActionBarActivity  {

    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<Applaud> mToDoTable;

    private MobileServiceTable<User> mUser;

    /**
     * Adapter to sync the items list with the view
     */
    private ProfileItemAdapter mAdapter;

    TextView Date;

    Button to,from,received,sent,tweets;

    String x;

    Globals gb = Globals.getInstance();

    final String[] desig = new String[1];


    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
     Toolbar   toolbar=(Toolbar)findViewById(R.id.app_bar);

        final TextView designation= (TextView) findViewById(R.id.txt2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        received= (Button) findViewById(R.id.btn1);

       received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Profile.this, MainActivityUserTab.class);
                startActivity(in);
            }
        });

        sent= (Button) findViewById(R.id.btn2);

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Profile.this,MainActivityUserTab.class);
                startActivity(in);
            }
        });
        tweets= (Button) findViewById(R.id.btn1);

        tweets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Profile.this,MainActivityUserTab.class);
                startActivity(in);
            }
        });

        username= (TextView) findViewById(R.id.txtusername);

        x=gb.getValue();
        Log.v("FROM TIMELINE ADAPTER", "X IN PROFILE is: " + x);

       // String recievedusername=getIntent().getExtras().getString("toname");
        username.setText(x);


        /*Globals gb=new Globals();
        gb.setClickedUser(recievedusername);*/


        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.kristy);

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, 200, 200, false);
        Bitmap conv_bm=getCircleBitmap(resizedBitmap, 100);
        // set circle bitmap
        ImageView mImage = (ImageView) findViewById(R.id.profile_image);
        mImage.setImageBitmap(conv_bm);
        // TODO Auto-generated method stub
        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    this);


            // Get the Mobile Service Table instance to use

            mToDoTable = mClient.getTable(Applaud.class);
            mUser = mClient.getTable(User.class);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        final MobileServiceList<User> result =
                                mUser.where().field("name").eq(x).execute().get();
                        for (User item : result) {
                           // Log.i(TAG, "Read object with ID " + item.id);
                            desig[0] = item.getDesignation();
                            Log.v("FINALLY DESIGNATION IS", desig[0]);

                        }

                    } catch (Exception exception) {
                       exception.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    gb.setDesignation(desig[0]);
                    designation.setText(desig[0]);
                }
            }.execute();
//


            mAdapter = new ProfileItemAdapter(this, R.layout.row_profile);
            ListView listViewToDo = (ListView) findViewById(R.id.listViewToDo2);
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

            LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress2);

            int result2,result3;

            @Override
            protected void onPreExecute() {
                // SHOW THE SPINNER WHILE LOADING FEEDS
                linlaHeaderProgress.setVisibility(View.VISIBLE);
            }


            //now list thing kkkkkkkkkkkkk
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Log.v("Here is x",x);
                    final MobileServiceList<Applaud> result = mToDoTable.where().field("to").eq(x).execute().get();
                    result2 = mToDoTable.where().field("to").eq(x).execute().get().getTotalCount();
                    result3 = mToDoTable.where().field("from").eq(x).execute().get().getTotalCount();
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

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.v("TOTALLLL COUUUUUUNT", String.valueOf(result2));
                TextView recievedno= (TextView) findViewById(R.id.rect);
                recievedno.setText(String.valueOf(result2));
                recievedno.setTextSize(20.0f);
                recievedno.setTextColor(Color.WHITE);
                TextView sentno= (TextView) findViewById(R.id.sent);
                sentno.setText(String.valueOf(result3));
                sentno.setTextSize(20.0f);
                sentno.setTextColor(Color.WHITE);
            }
        }.execute();

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
        canvas.drawCircle(100,100, 89, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addnew) {
            Intent i;
            i=new Intent(Profile.this,ApplaudSomeone.class);
            i.putExtra("designation",desig[0]);
            startActivity(i);
            return true;
        }

        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
   /** public void giveMeDesignation(View row, final Applaud currentItem) {

        /** mClient = new MobileServiceClient(
                 "https://apploud.azure-mobile.net/",
                 "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                 this);

        // Get the Mobile Service Table instance to use
        final String[] design = new String[1];
        //mUser = mClient.getTable(User.class);
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
        return;

    }**/
}