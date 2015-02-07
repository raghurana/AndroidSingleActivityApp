package com.raghurana.singleactivityapp;

import android.os.Bundle;
import android.text.TextUtils;

import com.raghurana.singleactivityapp.constants.AppConstants;
import com.raghurana.singleactivityapp.constants.FragTag;
import com.raghurana.singleactivityapp.events.EventAggregator;
import com.raghurana.singleactivityapp.ui.fragments.Section1Fragment;
import com.raghurana.singleactivityapp.ui.fragments.Section2Fragment;
import com.raghurana.singleactivityapp.utilities.BundleUtil;

public class FragmentFactory {

    private static Section1Fragment section1Fragment;
    private static Section2Fragment section2Fragment;

    public static BaseFragment getFragmentByTag(final Bundle fragBundle) {

        FragTag tag = (FragTag) fragBundle.getSerializable(AppConstants.FRAG_TAG_BUNDLE_KEY);

        if(tag == FragTag.Section1){
            if(section1Fragment == null) section1Fragment = new Section1Fragment();
            initBaseFrag(section1Fragment, fragBundle, tag);
            return section1Fragment;
        }

        if(tag == FragTag.Section2){
            if(section2Fragment == null) section2Fragment = new Section2Fragment();
            initBaseFrag(section2Fragment, fragBundle, tag);
            return section2Fragment;
        }

        return null;
    }

    public static void cleanupFragmentFactory() {
        section1Fragment = null;
        section2Fragment = null;
    }

    public static void initBaseFrag(BaseFragment baseFragment, Bundle fragBundle, FragTag navTag)  {
        baseFragment.NavTag = navTag;

        String title = BundleUtil.getDisplayTitle(fragBundle);
        if(!TextUtils.isEmpty(title))
            baseFragment.Title = title;

        if(baseFragment.EventBus == null)
            baseFragment.EventBus = EventAggregator.getInstance();

        if (baseFragment.getArguments() == null)
            baseFragment.setArguments(fragBundle);
        else
            baseFragment.getArguments().putAll(fragBundle);
    }
}
