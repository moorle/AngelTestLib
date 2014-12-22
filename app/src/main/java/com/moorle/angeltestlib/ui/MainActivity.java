package com.moorle.angeltestlib.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.moorle.angeltestlib.R;
import com.moorle.angeltestlib.model.AngelModel;


public class MainActivity extends BaseActivity
        implements NavDrawerFragment.NavDrawerCallbacks {

    private static final String TAG = "MainActivity";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavDrawerFragment mNavDrawerFragment;

    private Fragment mCurrentFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavDrawerFragment = (NavDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavDrawerFragment.setupNavDrawer(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        AngelModel.getInstance().loadData(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onNavDrawerItemSelected(int itemId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (itemId) {
            case NavDrawerFragment.NAVDRAWER_ITEM_CHOICE:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ChoiceQuestionFragment.newInstance(itemId))
                        .commit();
                break;
            case NavDrawerFragment.NAVDRAWER_ITEM_2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, BlankFragment.newInstance(itemId))
                        .commit();
                break;
            case NavDrawerFragment.NAVDRAWER_ITEM_3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, BlankFragment.newInstance(itemId))
                        .commit();
                break;
            case NavDrawerFragment.NAVDRAWER_ITEM_4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, BlankFragment.newInstance(itemId))
                        .commit();
                break;


        }

        mCurrentFragment = null;
    }

    public void onSectionAttached(int itemId) {
        switch (itemId) {
            case NavDrawerFragment.NAVDRAWER_ITEM_CHOICE:
                mTitle = getString(R.string.navdrawer_item_choice);
                break;
            case NavDrawerFragment.NAVDRAWER_ITEM_2:
                mTitle = getString(R.string.navdrawer_item_2);
                break;
            case NavDrawerFragment.NAVDRAWER_ITEM_3:
                mTitle = getString(R.string.navdrawer_item_3);
                break;
            case NavDrawerFragment.NAVDRAWER_ITEM_4:
                mTitle = getString(R.string.navdrawer_item_4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
