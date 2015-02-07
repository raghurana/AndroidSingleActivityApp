package com.raghurana.singleactivityapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raghurana.singleactivityapp.BaseFragment;
import com.raghurana.singleactivityapp.R;

public class Section2Fragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_section2, container,false);
        return fragView;
    }

}
