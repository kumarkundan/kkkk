package com.mojokarma.mojokarma;

/**
 * Created by Srinath shree on 30-06-2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapterUserTab extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapterUserTab is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapterUserTab is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterUserTab(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            ReceivedUserTab tab1 = new ReceivedUserTab();
            return tab1;
        }
      else  if(position==1)            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
        SentUserTab tab2 = new SentUserTab();
            return tab2;
        }
         else {

           TweetsUserTab tab3=new TweetsUserTab();
           return tab3; //HERE I WILL GET ERROR IF I WILL NOT USE FRAGMENT
       }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}