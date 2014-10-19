package com.gsy.femstoria;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OfferHelpActivity extends Activity{
	
	private Context _context;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_help);

        _context = this;
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0C575C")));
        int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");

        TextView yourTextView = (TextView)findViewById(titleId);
        yourTextView.setTextColor(Color.parseColor("#F6F6F6"));
        
        Button submit = (Button)findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(_context, StoryListActivity.class);
				_context.startActivity(i);
			}
        });

    }

}
