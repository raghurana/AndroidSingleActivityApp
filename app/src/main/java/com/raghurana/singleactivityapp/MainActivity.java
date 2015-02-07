package com.raghurana.singleactivityapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.raghurana.singleactivityapp.constants.FragTag;
import com.raghurana.singleactivityapp.events.EventAggregator;
import com.raghurana.singleactivityapp.events.EventType;
import com.raghurana.singleactivityapp.ui.drawer.AppNavDrawer;
import com.raghurana.singleactivityapp.ui.drawer.NavItemFactory;
import com.raghurana.singleactivityapp.utilities.BundleUtil;
import com.raghurana.singleactivityapp.utilities.DisplayUtil;


public class MainActivity extends ActionBarActivity {

    private AppNavDrawer navDrawer;
    private EventAggregator eventBus;
    private DisplayController displayController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.navDrawer = new AppNavDrawer(this);
        this.navDrawer.setItems(NavItemFactory.getNavItems());
        this.navDrawer.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showFragmentAtPosition(position);
            }
        });

        this.eventBus = EventAggregator.getInstance();
        this.displayController = new DisplayController(this, eventBus, R.id.container);
        this.displayController.subscribeToAppEvents();

        eventBus.publish(EventType.ShowActionBar, null );
        showFragmentAtPosition(0);
    }

    @Override
    protected void onDestroy() {
        this.displayController.unsubscribeFromAppEvents();
        FragmentFactory.cleanupFragmentFactory();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        final BaseFragment visibleFrag = this.displayController.getLastVisibleFragment();

        if(navDrawer.isOpen()) {
            navDrawer.hide();
            return;
        }

        if (visibleFrag == null || visibleFrag.Previous == null) {
            super.onBackPressed();
            return;
        }

        if (visibleFrag.hasInternalBackstack()) {
            visibleFrag.onBackPressed();
            return;
        }

        this.eventBus.publish(EventType.DisplayFragment, visibleFrag.Previous, visibleFrag.Previous.getArguments());
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (this.navDrawer.handleMenuItemClick(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    private void showFragmentAtPosition(final int position) {
        FragTag tag;

        switch (position) {
            case 0:
                tag = FragTag.Section1;
                break;
            case 1:
                tag = FragTag.Section2;
                break;
            default:
                tag = FragTag.Section1;
                break;
        }

        showFragmentByTag(tag, this.navDrawer.getNavTextByPosition(position));
    }

    private void showFragmentByTag(FragTag tag, String actionBarTitle) {
        this.eventBus.publish(EventType.DisplayFragment, this, BundleUtil.getBundleByFragTag(tag, actionBarTitle));

        if(this.navDrawer.isOpen()) {
            this.navDrawer.hide();
        }
    }
}
