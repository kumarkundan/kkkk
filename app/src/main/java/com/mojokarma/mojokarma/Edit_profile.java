package com.mojokarma.mojokarma;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYSeries;

import java.net.MalformedURLException;


public class Edit_profile extends ActionBarActivity {

    private MobileServiceClient mClient;

    private ProfileItemAdapter mAdapter;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<Applaud> mToDoTable;

    private MobileServiceTable<User> mUser;

    String x;

    Globals gb = Globals.getInstance();

    private GraphicalView mChart;
    private XYSeries series,series1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final TextView designation = (TextView) findViewById(R.id.de);


        //x=gb.getValue();
        SharedPreferences prefs = getSharedPreferences("login anme", MODE_PRIVATE);
        x = prefs.getString("name", "");

        TextView username = (TextView) findViewById(R.id.txtusername);

        username.setText(x);
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.kristy);

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, 200,200, false);
        Bitmap conv_bm=getCircleBitmap1(resizedBitmap,100);
        // set circle bitmap
        ImageView mImage = (ImageView) findViewById(R.id.profile_image);
        mImage.setImageBitmap(conv_bm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // drawBarChart();

        try {
            mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                    this);


            // Get the Mobile Service Table instance to use
            final String[] desig = new String[1];
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

            mAdapter = new ProfileItemAdapter(this, R.layout.row_profile);
            ListView listViewToDo = (ListView) findViewById(R.id.listViewToDo2e);
            listViewToDo.setAdapter(mAdapter);

            new AsyncTask<Void, Void, Void>() {

                //progress bar thing kkkkkkkkkkkkkkk

                LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress2e);

                int result2, result3;

                @Override
                protected void onPreExecute() {
                    // SHOW THE SPINNER WHILE LOADING FEEDS
                    linlaHeaderProgress.setVisibility(View.VISIBLE);
                }


                //now list thing kkkkkkkkkkkkk
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Log.v("Here is x", x);
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
                    TextView recievedno = (TextView) findViewById(R.id.recte);
                    recievedno.setText(String.valueOf(result2));
                    recievedno.setTextSize(20.0f);
                    recievedno.setTextColor(Color.WHITE);
                    TextView sentno = (TextView) findViewById(R.id.sente);
                    sentno.setText(String.valueOf(result3));
                    sentno.setTextSize(20.0f);
                    sentno.setTextColor(Color.WHITE);
                }
            }.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getCircleBitmap1(Bitmap bitmap , int pixels) {
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
        canvas.drawCircle(100, 100, 89, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }


    /**private  void drawBarChart(){
        // X-axis data
        double years=12;
        // Y-axis data arrays
        double Maxpoints[]={1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000};
        double Pointsrec[]={200,300,200,400,0,100,500,600,800,600,400,700};
        // Create XY series for the first data array
        series = new XYSeries(" Max Points ");
        for(int i=0;i<years;i++){
            series.add(i, Maxpoints[i]);
        }
        // Create XYSeries for the second data array
        series1 = new XYSeries("ReceivedUserTab Points");
        for(int i=0;i<years;i++){
            series1.add(i, Pointsrec[i]);
        }
        // Create XY Series list and add the series to the list
        XYMultipleSeriesDataset SeriesDataset =new XYMultipleSeriesDataset();
        SeriesDataset.addSeries(series);
        SeriesDataset.addSeries(series1);
        // Create renderer for the series
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        // Set bar color
        renderer.setColor(Color.parseColor("#1BCFB3"));
        renderer.setFillPoints(true);
        renderer.setLineWidth(2);
        // Create render for the series1
        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        // Set bar color
        renderer1.setColor(Color.parseColor("#FF740A"));
        renderer1.setFillPoints(true);
        renderer1.setLineWidth(2);
        // Create renderer list and add renderers to the list
        XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax(12);
        mRenderer.setYAxisMin(0);
        mRenderer.setYAxisMax(1000);
        mRenderer.setXLabels(0);
        mRenderer.setYLabels(0);
        mRenderer.setBarSpacing(1);
        mRenderer.setShowGridY(false);
        mRenderer.setChartTitle("Applauds in 2014");
        mRenderer.setLabelsColor(Color.parseColor("#1BCFB3"));
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer1);
        // Set chart background color
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setMarginsColor(Color.WHITE);

        // Get stacked bar chart view
        // Types of bar chart: DEFAULT and STACKED
        mChart = ChartFactory.getBarChartView(this, SeriesDataset, mRenderer, BarChart.Type.STACKED);
        // Add the bar chart view to the layout to show
        LinearLayout chartlayout=(LinearLayout)findViewById(R.id.barchart);
        chartlayout.addView(mChart);
    }**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.tick){
            startActivity(new Intent(this,UserProfile.class));
        }


        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
