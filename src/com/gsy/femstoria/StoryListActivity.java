package com.gsy.femstoria;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;

import com.gsy.femstoria.callbacks.ListViewCallbacks;
import com.gsy.femstoria.model.Post;

/**
 * An activity representing a list of Stories. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link StoryDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link StoryListFragment} and the item details (if present) is a
 * {@link StoryDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link StoryListFragment.Callbacks} interface to listen for item selections.
 */
public class StoryListActivity extends FragmentActivity implements ListViewCallbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	//public static Context CONTEXT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_list);
		CONTEXT = this;

//		ActionBar bar = getActionBar();
//		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0C575C")));
//		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
//				"id", "android");

//		TextView yourTextView = (TextView) findViewById(titleId);
//		yourTextView.setTextColor(Color.parseColor("#F6F6F6"));

		if (findViewById(R.id.story_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((StoryListFragment) getSupportFragmentManager().findFragmentById(
					R.id.story_list)).setActivateOnItemClick(true);
		}

		//Log.d("facebook", Session.getActiveSession().getAccessToken());
		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.story_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.add:
				Intent i = new Intent(CONTEXT, PostStoryActivity.class);
				startActivityForResult(i, 1);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 1) {

		     if(resultCode == RESULT_OK){
		    	 StoryListFragment fragment = (StoryListFragment)getSupportFragmentManager().findFragmentById(R.id.story_list);
		    	 fragment.reload();
		    	 //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.story_list)).commit();
		     }
		  }
	}

	/**
	 * Callback method from {@link StoryListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(Post post) {
		
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putSerializable(StoryDetailFragment.ARG_POST, post);
			StoryDetailFragment fragment = new StoryDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.story_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, StoryDetailActivity.class);
			detailIntent.putExtra(StoryDetailFragment.ARG_POST, post);
			startActivity(detailIntent);
		}
	}
}
