package com.gsy.femstoria;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private static final String SHOULD_REFRESH = "shouldRefresh";
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment	mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence				mTitle; 
	private boolean						mShouldRefresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		
		switch(position){
			case 1:
				Intent i = new Intent(this, PostStoryActivity.class);
				startActivityForResult(i, 1);
				return;
			case 2:
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("message/rfc822");
				intent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.feedback_title));
				intent.putExtra(Intent.EXTRA_EMAIL, new String[] { getText(R.string.feedback_email).toString() });
				Intent mailer = Intent.createChooser(intent, null);
				startActivity(mailer);
				return;
		}
		
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, new StoryListFragment()).commit();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == 1){
			mShouldRefresh = data.getBooleanExtra(SHOULD_REFRESH, false);
			
			if(mShouldRefresh){
				onNavigationDrawerItemSelected(0);
			}
		}
	};

	public void onSectionAttached(int number) {
		switch (number) {
			case 1:
				mTitle = getString(R.string.navigation_title_share_a_story);
				break;
			case 2:
				mTitle = getString(R.string.navigation_send_feedback);
				break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch (id) {
			case R.id.add:
				Intent i = new Intent(this, PostStoryActivity.class);
				startActivityForResult(i, 1);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
