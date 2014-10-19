package com.gsy.femstoria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.gsy.femstoria.asynctask.AsyncTaskListener;
import com.gsy.femstoria.asynctask.PostAsyncTask;
import com.gsy.femstoria.model.CreatePost;

public class PostStoryActivity extends Activity {

	private static final String SHOULD_REFRESH = "shouldRefresh";
	
	private Context mContext;
	private EditText story;
	private CheckBox postAsAnonymous;
	private Button submit;
	private EditText name;
	private DataStore dataStore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_story);
		
		story = (EditText)findViewById(R.id.etStory);
		postAsAnonymous = (CheckBox)findViewById(R.id.cbAnonymous);
		name = (EditText)findViewById(R.id.etName);

		mContext = this;
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

//		ActionBar bar = getActionBar();
//		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0C575C")));
//		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
//				"id", "android");
//
//		TextView yourTextView = (TextView) findViewById(titleId);
//		yourTextView.setTextColor(Color.parseColor("#F6F6F6"));
		
		dataStore = new DataStore(this);
		name.setText(dataStore.getUserName());
		
		submit = (Button) findViewById(R.id.btnSubmit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String userName = name.getText().toString();
				
				if(postAsAnonymous.isChecked()){
					userName = "";
				} else {
					dataStore.setUserName(userName);
				}
				
				DataStore dataStore = new DataStore(mContext);
				
				CreatePost post = new CreatePost(dataStore.getUserId(), userName, story.getText().toString());
				
				new PostAsyncTask(new AsyncTaskListener<Boolean>(){

					@Override
					public void onPostExecuteCallBack(Boolean success) {
						
						submit.setEnabled(true);
						submit.setText(R.string.post_story_post);
						//handle result
						if(success){
							Intent i = new Intent();
						    i.putExtra(SHOULD_REFRESH, true);
						    setResult(1, i);
						    finish();
						}
					}

					@Override
					public void onPreExecuteCallBack() {
						submit.setText(R.string.posting);
						submit.setEnabled(false);
					}
					
				}).execute(post);

//				// 1. Instantiate an AlertDialog.Builder with its constructor
//				AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
//
//				// 2. Chain together various setter methods to set the dialog
//				// characteristics
//				builder.setMessage("We're sorry to hear that dear. Hang in there, chin up! You have a world of women behind you. Here's a website to Help you out.");
//
//				builder.setNegativeButton(R.string.proceed,
//						new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int id) {
//								Intent i = new Intent(_context, StoryListActivity.class);
//								startActivity(i);
//							}
//						});
//
//				builder.setPositiveButton("Visit Site",
//						new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int id) {
//								Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//								startActivity(browserIntent);
//								
//								Intent i = new Intent(_context, StoryListActivity.class);
//								startActivity(i);
//
//							}
//						});
//
//				// 3. Get the AlertDialog from create()
//				AlertDialog dialog = builder.create();
//				dialog.show();

			}
		});
		
		story.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count < 1){
					submit.setEnabled(false);
				} else {
					submit.setEnabled(true);
				}
			}
			
		});
		
		postAsAnonymous.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					name.setEnabled(false);
				} else {
					name.setEnabled(true);
				}
			}
		});
	}
}
