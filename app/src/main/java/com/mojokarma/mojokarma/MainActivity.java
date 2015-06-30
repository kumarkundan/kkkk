package com.mojokarma.mojokarma;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;


///////////////////////////////////////////////////
// this Main Actvity is parent class of tab Actvity
///////////////////////////////////////////////////

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, Received.class);
        spec = tabHost.newTabSpec("Recieved").setIndicator("Received")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Sent.class);
        spec = tabHost.newTabSpec("Sent").setIndicator("Sent")
                .setContent(intent);
        tabHost.addTab(spec);

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