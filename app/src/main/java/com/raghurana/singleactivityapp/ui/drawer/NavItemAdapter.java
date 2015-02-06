package com.raghurana.singleactivityapp.ui.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.raghurana.singleactivityapp.R;

import java.util.List;

public class NavItemAdapter extends BaseAdapter {

    private final List<NavItem> navItems;
    private final LayoutInflater inflater;

    public NavItemAdapter(final Context ctx, final List<NavItem> navItems) {
        this.navItems = navItems;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return this.navItems.size();
    }

    @Override
    public Object getItem(final int position) {
        return this.navItems.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.list_item_nav_drawer, null);
        }

        final NavItem currentItem = (NavItem) this.getItem(position);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.NavItemImageView);
        final TextView textView = (TextView) convertView.findViewById(R.id.NavItemTextView);

        imageView.setImageResource(currentItem.IconId);
        textView.setText(currentItem.Text);

        return convertView;
    }

    public String getNavTextByPosition(final int position) {
        return this.navItems.get(position).Text;
    }
}