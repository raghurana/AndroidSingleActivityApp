package com.raghurana.singleactivityapp.ui.drawer;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.raghurana.singleactivityapp.R;

import java.util.List;

public class AppNavDrawer {

    private NavItemAdapter itemsAdapter;
    private final Context context;
    private final ListView drawerListView;
    private final DrawerLayout mainDrawerLayout;
    private final ActionBarDrawerToggle drawerToggle;

    public AppNavDrawer(final Activity context) {

        this.context = context;
        this.mainDrawerLayout   = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        this.drawerListView     = (ListView) context.findViewById(R.id.leftDrawerListView);

        this.drawerToggle       = new ActionBarDrawerToggle(context, this.mainDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(final View drawerView) {
                context.invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(final View drawerView) {
                context.invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        this.mainDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                AppNavDrawer.this.drawerToggle.syncState();
            }
        });

        this.drawerToggle.setDrawerIndicatorEnabled(true);
        this.mainDrawerLayout.setDrawerListener(this.drawerToggle);
    }

    public void setItems(final List<NavItem> items) {
        this.itemsAdapter = new NavItemAdapter(this.context, items);
        this.drawerListView.setAdapter(this.itemsAdapter);
    }

    public void setItemClickListener(final AdapterView.OnItemClickListener itemClickListener) {
        this.drawerListView.setClickable(true);
        this.drawerListView.setOnItemClickListener(itemClickListener);
    }

    public boolean handleMenuItemClick(final MenuItem item) {
        return this.drawerToggle.onOptionsItemSelected(item);
    }

    public boolean isOpen() {
        return this.mainDrawerLayout.isDrawerOpen(Gravity.LEFT);
    }

    public void show() {
        this.mainDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void hide() {
        this.mainDrawerLayout.closeDrawers();
        this.drawerToggle.setDrawerIndicatorEnabled(true);
    }

    public String getNavTextByPosition(final int position) {
        if (this.itemsAdapter != null) {
            return this.itemsAdapter.getNavTextByPosition(position);
        }

        return "";
    }
}
