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

public class SignUpActivity extends Activity {
	
	private Context context;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        context = this;
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(false);
        
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0C575C")));
        int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");

        TextView yourTextView = (TextView)findViewById(titleId);
        yourTextView.setTextColor(Color.parseColor("#F6F6F6"));
        
        Button signUp = (Button)findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, StoryListActivity.class);
				context.startActivity(i);
			}
        	
        });

    }


}
