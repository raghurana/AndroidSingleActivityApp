package com.raghurana.singleactivityapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.raghurana.singleactivityapp.events.EventAggregator;
import com.raghurana.singleactivityapp.events.EventType;
import com.raghurana.singleactivityapp.events.IEventSubscriber;

public class DisplayController implements IEventSubscriber {

    //region Fields

    private final ActionBarActivity context;
    private final EventAggregator eventBus;
    private final int fragmentsContainerId;
    private BaseFragment lastVisibleFragment;

    //endregion

    //region Constructor(s)

    public DisplayController(ActionBarActivity context, EventAggregator eventBus, int fragmentsContainerId) {
        this.context = context;
        this.eventBus = eventBus;
        this.fragmentsContainerId = fragmentsContainerId;
    }

    //endregion

    //region Public Methods

    public BaseFragment getLastVisibleFragment() {
        return this.lastVisibleFragment;
    }

    public void subscribeToAppEvents(){
        this.eventBus.subscribe(EventType.DisplayFragment, this);
        this.eventBus.subscribe(EventType.ShowActionBar, this);
        this.eventBus.subscribe(EventType.HideTitleBar, this);
    }

    public void unsubscribeFromAppEvents() {
        this.eventBus.unsubscribe(this);
    }

    //endregion

    //region IEventSubscriber Members

    @Override
    public void onEventTriggered(EventType eventType, Object publisher, Bundle data) {

        //region When a DisplayFragment event is triggered
        if (eventType == EventType.DisplayFragment) {
            final BaseFragment fragToShow = FragmentFactory.getFragmentByTag(data);

            if (publisher != null && !publisher.equals(fragToShow)) {
                if (publisher instanceof BaseFragment) {
                    fragToShow.Previous = (BaseFragment) publisher;
                } else {
                    fragToShow.Previous = null;
                }
            }

            context.getSupportActionBar().setTitle(fragToShow.Title);

            this.showFragment(fragToShow);
            this.lastVisibleFragment = fragToShow;
        }
        //endregion

        //region When Show Title Bar event is triggered
        if(eventType == EventType.ShowActionBar) {
            context.getSupportActionBar().setHomeButtonEnabled(true);
            context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //endregion

        //region When Hide Title Bar event is triggered
        if(eventType == EventType.HideTitleBar) {
            context.getSupportActionBar().setHomeButtonEnabled(false);
            context.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        //endregion
    }

    //endregion

    //region Private Methods

    private void showFragment(final BaseFragment frag) {
        final FragmentManager manager = this.context.getFragmentManager();
        final FragmentTransaction txn = manager.beginTransaction();

        if (this.lastVisibleFragment != null) {
            txn.hide(this.lastVisibleFragment);
        }

        if (!frag.isAdded()) {
            txn.add(fragmentsContainerId, frag);
        } else {
            txn.show(frag);
        }

        txn.commit();
        manager.executePendingTransactions();
    }

    //endregion
}
