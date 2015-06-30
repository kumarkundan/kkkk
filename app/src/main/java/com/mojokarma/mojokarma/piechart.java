package com.mojokarma.mojokarma;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class piechart extends ActionBarActivity {

  MyView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);
        a=(MyView) findViewById(R.id.chart);

    }
  /*  private void openChart() {
        String[] code = new String[] { "Froyo", "Gingerbread",
                "IceCream Sandwich", "Jelly Bean", "KitKat" };
        // Pie Chart Section Value
        double[] distribution = { 0.5, 9.1, 7.8, 45.5, 33.9 };
        // Color of each Pie Chart Sections

        int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN,
                Color.RED };



        // Instantiating CategorySeries to plot Pie Chart

        CategorySeries distributionSeries = new CategorySeries(
                " Android version distribution as on October 1, 2012");

        for (int i = 0; i < distribution.length; i++) {

            // Adding a slice with its values and name to the Pie Chart

            distributionSeries.add(code[i], distribution[i]);

        }

        // Instantiating a renderer for the Pie Chart

        DefaultRenderer defaultRenderer = new DefaultRenderer();

        for (int i = 0; i < distribution.length; i++) {

            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();

            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);

//Adding colors to the chart

            defaultRenderer.setBackgroundColor(Color.BLACK);
            defaultRenderer.setApplyBackgroundColor(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }
        defaultRenderer
                .setChartTitle("Android version distribution as on December 1, 2014. ");
        defaultRenderer.setChartTitleTextSize(20);

        defaultRenderer.setZoomButtonsVisible(false);

        // this part is used to display graph on the xml80
        // Creating an intent to plot bar chart using dataset and

        // multipleRenderer

        // Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),

        // distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");

        // Start Activity
        // startActivity(intent);

        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);

        // remove any views before u paint the chart90
        chartContainer.removeAllViews();

        // drawing pie chart

        mChart = ChartFactory.getPieChartView(getBaseContext(),

                distributionSeries, defaultRenderer);
        // adding the view to the linearlayout

        chartContainer.addView(mChart);

    }*/







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_piechart, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
