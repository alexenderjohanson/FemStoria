package com.gsy.femstoria.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gsy.femstoria.R;
import com.gsy.femstoria.asynctask.AsyncTaskListener;
import com.gsy.femstoria.asynctask.HugAsyncTask;
import com.gsy.femstoria.model.Post;
import com.gsy.femstoria.utility.TimeHelper;

public class StoryView extends RelativeLayout{

	private int TRANSITION_DURATION = 500;
	private TextView hugsDot;
	private TextView hugs;
	private Button hug;
	private TransitionDrawable transition;
	
	
	public StoryView(final Post story, final Context context) {
		super(context);
		
		View storyCell = View.inflate(context, R.layout.story_cell, null);
		TextView userName = (TextView) storyCell.findViewById(R.id.tvUserName);
		TextView datePosted = (TextView) storyCell.findViewById(R.id.tvDatePosted);
		TextView storyText = (TextView) storyCell.findViewById(R.id.tvStory);
		hugsDot = (TextView) storyCell.findViewById(R.id.tvHugsDot);
		hugs = (TextView) storyCell.findViewById(R.id.tvHugs);
		hug = (Button)storyCell.findViewById(R.id.btnHug);
		transition = (TransitionDrawable) hug.getBackground();

		
		hug.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				HugAsyncTask asyncTask = new HugAsyncTask(!story.huged, context, new AsyncTaskListener<Boolean>(){

					@Override
					public void onPostExecuteCallBack(Boolean success) {
						if(success){
							story.huged = !story.huged; 
						} else {
							
							if(story.huged){
								story.hugs++;
								setHug(true);
							} else {
								story.hugs--;
								setHug(false);
							}
							
							setHugs(story.hugs, context);
						}
					}

					@Override
					public void onPreExecuteCallBack() {
						
						if(story.huged){
							story.hugs--;
							setHug(false);
						} else {
							story.hugs++;
							setHug(true);
						}
						
						setHugs(story.hugs, context);
					}
					
				});
				
				asyncTask.execute(story.postId);
			}
		});
		
		if(story.huged){
			setHug(true);			
		}
		
		if(story.hugs > 0){
			hugsDot.setVisibility(View.VISIBLE);
			hugs.setVisibility(View.VISIBLE);
			hugs.setText(String.format(context.getText(R.string.hugs).toString(), story.hugs));
		}
		
		String passedTime = TimeHelper.getPassedTime(story.postedTime);
		datePosted.setText(passedTime);
		//storyText.setTypeface(FontUtil.getTypeface(context, FontType.NORMAL));
		storyText.setText(story.message);
		
		if(story.userName == null || story.userName.equals("")){
			userName.setText(R.string.common_anonymous);
		} else {
			userName.setText(story.userName);
		}
		
		addView(storyCell);
	}
	
	private void setHug(boolean hugged){
		if(hugged){
			hug.setText(R.string.common_hugged);
			hug.setTextColor(Color.WHITE);
			transition.startTransition(TRANSITION_DURATION);
		} else {
			hug.setText(R.string.common_hug);
			hug.setTextColor(Color.BLACK);
			transition.reverseTransition(TRANSITION_DURATION);
		}
	}
	
	private void setHugs(int storyHugs, Context context){
		if(storyHugs > 0){
			hugsDot.setVisibility(View.VISIBLE);
			hugs.setVisibility(View.VISIBLE);
			hugs.setText(String.format(context.getText(R.string.hugs).toString(), storyHugs));
		} else if (storyHugs < 1){
			hugsDot.setVisibility(View.GONE);
			hugs.setVisibility(View.GONE);
		}
	}
}
