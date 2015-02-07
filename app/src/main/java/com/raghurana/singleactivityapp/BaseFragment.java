package com.raghurana.singleactivityapp;

import android.app.Fragment;
import android.os.Bundle;

import com.raghurana.singleactivityapp.constants.FragTag;
import com.raghurana.singleactivityapp.events.EventAggregator;
import com.raghurana.singleactivityapp.events.EventType;

public class BaseFragment extends Fragment {

    //region Fields
    protected boolean hasInternalBackstack;
    public BaseFragment Previous;
    public EventAggregator EventBus;
    public FragTag NavTag;
    public String Title;
    //endregion

    //region Fragment Members

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        checkTitleBar();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(final boolean hidden) {
        if (!hidden) {
            this.onShow();
        } else {
            this.onHide();
        }
        super.onHiddenChanged(hidden);
    }

    //endregion

    //region Public Methods

    public boolean hasInternalBackstack() {
        return this.hasInternalBackstack;
    }

    public void onBackPressed() {
    }

    //endregion

    //region Protected Methods

    protected void onShow() {
        checkTitleBar();
    }

    protected void onHide() {
    }

    protected boolean showTitleBar() {
        return true;
    }

    protected void checkTitleBar() {
        if (showTitleBar())
            EventBus.publish(EventType.ShowActionBar, null, null);
        else
            EventBus.publish(EventType.HideTitleBar, null, null);
    }

    //endregion
}
