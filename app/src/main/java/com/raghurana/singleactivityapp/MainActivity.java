package com.raghurana.singleactivityapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.raghurana.singleactivityapp.ui.drawer.AppNavDrawer;
import com.raghurana.singleactivityapp.ui.drawer.NavItem;
import com.raghurana.singleactivityapp.ui.drawer.NavItemFactory;


public class MainActivity extends ActionBarActivity {

    private AppNavDrawer navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.navDrawer = new AppNavDrawer(this);
        this.navDrawer.setItems(NavItemFactory.getNavItems());
        this.navDrawer.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showFragmentAtPosition(position);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (this.navDrawer.handleMenuItemClick(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    private void showFragmentAtPosition(final int position) {
        MessageBox.show(String.format("%s clicked", position));

        if(this.navDrawer.isOpen()) {
            this.navDrawer.hide();
        }
    }
}
