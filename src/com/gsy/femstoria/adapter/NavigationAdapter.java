package com.gsy.femstoria.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gsy.femstoria.model.NavigationItem;
import com.gsy.femstoria.views.NavigationView;

public class NavigationAdapter extends BaseAdapter {

	private List<NavigationItem> navigationItems;
	private Context context;
	
	public NavigationAdapter(Context context, List<NavigationItem> navigationItem){
		this.context = context;
		navigationItems = navigationItem;
	}
	
	@Override
	public int getCount() {
		return navigationItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navigationItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View cell = new NavigationView(navigationItems.get(position), context);
		return cell;
	}

}
