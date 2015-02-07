package com.raghurana.singleactivityapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.raghurana.singleactivityapp.BaseFragment;
import com.raghurana.singleactivityapp.R;
import com.raghurana.singleactivityapp.constants.FragTag;
import com.raghurana.singleactivityapp.events.EventType;
import com.raghurana.singleactivityapp.utilities.BundleUtil;

public class Section1Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView           = inflater.inflate(R.layout.fragment_section1, container,false);
        Button section3Button   = (Button) fragView.findViewById(R.id.ShowSection3Button);
        section3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSection3ButtonClicked();
            }
        });
        return fragView;
    }

    private void onSection3ButtonClicked() {
        Bundle fragBundle = BundleUtil.getBundleByFragTag(FragTag.Section3, "Section 3");
        EventBus.publish(EventType.DisplayFragment, this, fragBundle);
    }

}
