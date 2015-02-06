package com.raghurana.singleactivityapp.ui.drawer;

import com.raghurana.singleactivityapp.R;

import java.util.ArrayList;
import java.util.List;

public class NavItemFactory {

    public static List<NavItem> getNavItems() {
        final List<NavItem> items = new ArrayList<NavItem>();
        items.add(new NavItem("Section 1", R.drawable.ic_launcher));
        items.add(new NavItem("Section 2", R.drawable.ic_launcher));
        items.add(new NavItem("Section 3", R.drawable.ic_launcher));
        return items;
    }
}
