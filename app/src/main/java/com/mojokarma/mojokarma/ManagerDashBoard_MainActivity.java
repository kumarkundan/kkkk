package com.mojokarma.mojokarma;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;


public class ManagerDashBoard_MainActivity extends ActionBarActivity {


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

    TextView a,b,c;
    ViewPager pager;
    ViewPagerAdapterSTab adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Received","Sent","Tweets"};
    int Numboftabs =3;
   LinearLayout mainLayout;
    PieChart mChart;
    // we're going to display pie chart for smartphones martket shares
    float[] yData = { 15, 10, 15, 8, 4,10 };
    String[] xData ={"Colloboration","Technical Excellence","Innovation","Integrity","Nimble","Passion"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
      Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ViewPagerAdapterSTab(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager2);
        final TextView recievedText= (TextView) findViewById(R.id.rectxt);
        final TextView sentText= (TextView) findViewById(R.id.sendxt);
        final TextView tweetText= (TextView) findViewById(R.id.tweetstxt);

        new AsyncTask<Void, Void, Void>() {

            //progress bar thing kkkkkkkkkkkkkkk

            LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress2);

            int result2,result3;

            @Override
            protected void onPreExecute() {
                // SHOW THE SPINNER WHILE LOADING FEEDS
               // linlaHeaderProgress.setVisibility(View.VISIBLE);
            }


            //now list thing kkkkkkkkkkkkk
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mClient = new MobileServiceClient(
                            "https://apploud.azure-mobile.net/",
                            "ekaeTCOfAomLFAWiZtuwXltIneSuxo19",
                            getApplicationContext());


                    // Get the Mobile Service Table instance to use
                  //  final String[] desig = new String[1];
                    mToDoTable = mClient.getTable(Applaud.class);
                    //Log.v("Here is x", x);
                    //final MobileServiceList<Applaud> result = mToDoTable.where().field("to").eq(x).execute().get();
                    result2= mToDoTable.select("content").execute().get().getTotalCount();
                   // result3 = mToDoTable.where().field("from").eq(x).execute().get().getTotalCount();
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

                recievedText.setText(String.valueOf(result2));
                recievedText.setTextSize(30.0f);
                recievedText.setTextColor(Color.WHITE);

                sentText.setText(String.valueOf(result2));
                sentText.setTextSize(30.0f);
                sentText.setTextColor(Color.WHITE);

                //tweetText.setText(String.valueOf(result2));
                tweetText.setTextSize(30.0f);
                tweetText.setTextColor(Color.WHITE);
            }
        }.execute();











        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.materialTabHost2);
        tabs.setDistributeEvenly(true);
        // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width


        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accentcolor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

       a=(TextView)findViewById(R.id.rectxt);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerDashBoard_MainActivity.this,ManagerMainActivityLTab.class);
                startActivity(i);
            }
        });


        b=(TextView)findViewById(R.id.sendxt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerDashBoard_MainActivity.this, ManagerMainActivityLTab.class);
                startActivity(i);
            }
        });



        c=(TextView)findViewById(R.id.tweetstxt);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerDashBoard_MainActivity.this, ManagerMainActivityLTab.class);
                startActivity(i);
            }
        });

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mChart = new PieChart(this);

        mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.parseColor("#ffffff"));

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Employee Analysis");

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);


        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        final ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //  int colors[]={R.color.teal10,R.color.teal9,R.color.teal8,R.color.teal7,R.color.teal6,};
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display the activity when value selected

                if (xData[e.getXIndex()].equals("Colloboration"))
                    startActivity(new Intent(ManagerDashBoard_MainActivity.this, Colloboration.class));


                else if (xData[e.getXIndex()].equals("Technical Excellence"))
                    startActivity(new Intent(ManagerDashBoard_MainActivity.this, TechicalExcellence.class));


                else if (xData[e.getXIndex()].equals("Nimble"))
                    startActivity(new Intent(ManagerDashBoard_MainActivity.this, Nimble.class));


                else if (xData[e.getXIndex()].equals("Innovation"))
                    startActivity(new Intent(ManagerDashBoard_MainActivity.this, Innovation.class));


                else if (xData[e.getXIndex()].equals("Integrity"))
                    startActivity(new Intent(ManagerDashBoard_MainActivity.this, Integrity.class));


                else
                    startActivity(new Intent(ManagerDashBoard_MainActivity.this, Passion.class));
            }

            @Override
            public void onNothingSelected() {

            }
        });


        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
        mChart.getLegend().setEnabled(false);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee, menu);
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
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        if(id==R.id.action_giftapproval){
            startActivity(new Intent(this, GiftTimeline.class));
        }

        return super.onOptionsItemSelected(item);
    }





}
