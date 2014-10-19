package com.gsy.femstoria.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gsy.femstoria.R;
import com.gsy.femstoria.model.NavigationItem;
import com.gsy.femstoria.utility.FontUtil;
import com.gsy.femstoria.utility.FontUtil.FontType;

public class NavigationView extends RelativeLayout {
	
	public NavigationView(NavigationItem item, Context context) {
		super(context);
		
		View navigationCell = View.inflate(context, R.layout.navigation_cell, null);
		TextView title = (TextView)navigationCell.findViewById(R.id.tvNavigationTitle);
		ImageView icon = (ImageView)navigationCell.findViewById(R.id.ivNavigationIcon);
		
//		if(item.iconResId == 0){
//			icon.setVisibility(View.INVISIBLE);
//		} else {
//			icon.setImageDrawable(context.getResources().getDrawable(item.iconResId));
//		}
		
		//title.setTypeface(FontUtil.getTypeface(context, FontType.EXTRA_NORMAL));
		title.setText(item.title);
		
		addView(navigationCell);
	}

}
