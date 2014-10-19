package com.gsy.femstoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gsy.femstoria.asynctask.AsyncTaskListener;
import com.gsy.femstoria.asynctask.SplashAsyncTask;
import com.gsy.femstoria.utility.FontUtil;
import com.gsy.femstoria.utility.FontUtil.FontType;

public class SplashActivity extends Activity implements AsyncTaskListener<Void>{

	private TextView femStoria;
	private Animation textAnimation;
	private SplashActivity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		activity = this;
		
		LinearLayout logoLayout = (LinearLayout)findViewById(R.id.llLogo);
		femStoria = (TextView)findViewById(R.id.tvFemStoria);
		//femStoria.setTypeface(FontUtil.getTypeface(this, FontType.NORMAL));
		
		textAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		textAnimation.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation animation) {
				new SplashAsyncTask(activity, activity).execute();
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.down_from_top);
		animation.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation animation) {
				femStoria.setVisibility(View.VISIBLE);
				femStoria.setAnimation(textAnimation);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				
			}			
		});
		logoLayout.startAnimation(animation);
		
	}

	@Override
	public void onPostExecuteCallBack(Void result) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}

	@Override
	public void onPreExecuteCallBack() {
		// TODO Auto-generated method stub
		
	}
}
