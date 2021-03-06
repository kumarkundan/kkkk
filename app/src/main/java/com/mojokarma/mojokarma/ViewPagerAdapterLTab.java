package com.mojokarma.mojokarma;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Srinath shree on 03-07-2015.
 */
public class ViewPagerAdapterLTab  extends FragmentStatePagerAdapter {


    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapterUserTab is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapterUserTab is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterLTab(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            ManagerReceivedLTab tab1 = new ManagerReceivedLTab();
            return tab1;
        }
        else  if(position==1)            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            ManagerSentLTab tab2 = new ManagerSentLTab ();
            return tab2;
        }
        else {

            ManagerTweetsLTab tab3=new ManagerTweetsLTab();
            return tab3;
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


