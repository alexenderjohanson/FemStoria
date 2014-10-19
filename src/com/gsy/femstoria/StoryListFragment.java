package com.gsy.femstoria;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.gsy.femstoria.adapter.PostsAdapter;
import com.gsy.femstoria.asynctask.AsyncTaskListener;
import com.gsy.femstoria.asynctask.GetPostAsyncTask;
import com.gsy.femstoria.model.Post;

/**
 * A list fragment representing a list of Stories. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link StoryDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class StoryListFragment extends Fragment implements
		AsyncTaskListener<List<Post>> {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	private PostsAdapter STORY_ADAPTER;

	private boolean fetchFlag;
	private long lastFetchTime;
	private AsyncTaskListener<List<Post>> listener;
	private View footer;
    private ListView storyList;
    private RelativeLayout loadingLayout;
    private ImageView loading;
    
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public StoryListFragment() {
		listener = this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		STORY_ADAPTER = new PostsAdapter(getActivity());
		// getListView().setBackgroundResource(R.color.cell_backgrond);

//		setListAdapter(STORY_ADAPTER);

		// registerForContextMenu(this.getListView());
	}

	@Override
	public void onResume() {
		super.onResume();

		if(STORY_ADAPTER.getCount() < 1){
			new GetPostAsyncTask(getActivity(), this).execute(new Date().getTime());
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
        View rootView = inflater.inflate(R.layout.fragment_story_list, container, false);
        storyList = (ListView)rootView.findViewById(R.id.lvListView);
        
		footer = View.inflate(getActivity(), R.layout.story_list_footer, null);
		ImageView footerLoading = (ImageView)footer.findViewById(R.id.ivLoading);
		Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
		footerLoading.startAnimation(animation);
		
		//adding footer view to list before adapter is set then removing the footer view again
		//we're doing this because of some wired bug in Android 4.2.2
		//A bug where adding footer view after adapter was set will not show the footer view
        setListViewFooter(true);
		storyList.setAdapter(STORY_ADAPTER);
		setListViewFooter(false);
		
        storyList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Notify the active callbacks interface (the activity, if the
				// fragment is attached to one) that an item has been selected.
				Post post = (Post) STORY_ADAPTER.getItem(position);
				// In single-pane mode, simply start the detail activity
				// for the selected item ID.
				Intent detailIntent = new Intent(getActivity(), StoryDetailActivity.class);
				detailIntent.putExtra(StoryDetailFragment.ARG_POST, post);
				startActivity(detailIntent);
				
			}
        	
        });
        
        loadingLayout = (RelativeLayout)rootView.findViewById(R.id.rlLoading);
        loading = (ImageView)rootView.findViewById(R.id.ivLoading);
		
		setListViewFooter(true);
		storyList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				 if (totalItemCount - firstVisibleItem <= visibleItemCount + 3 && fetchFlag) {
					 fetchFlag = false;
					 new GetPostAsyncTask(getActivity(), listener).execute(lastFetchTime);
				 }
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

		});
		
		Animation listViewAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
		LayoutAnimationController controller = new LayoutAnimationController(listViewAnimation);
		storyList.setLayoutAnimation(controller);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
        
        return rootView;
	}
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.story_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		storyList.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		registerForContextMenu(storyList);
		storyList.setDivider(null);
		// Indicate that this fragment would like to influence the set of actions in the action bar.
		setHasOptionsMenu(true);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.add:
				Intent i = new Intent(getActivity(), PostStoryActivity.class);
				startActivityForResult(i, 1);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			storyList.setItemChecked(mActivatedPosition, false);
		} else {
			storyList.setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	@Override
	public void onPreExecuteCallBack() {

		if (STORY_ADAPTER.getCount() < 1) {
			loadingLayout.setVisibility(View.VISIBLE);
			storyList.setVisibility(View.GONE);
			
			Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
			loading.startAnimation(animation);
		}
	}

	@Override
	public void onPostExecuteCallBack(List<Post> result) {
		
		if(STORY_ADAPTER.getCount() < 1){
			loadingLayout.setVisibility(View.GONE);
			loading.clearAnimation();
			storyList.setVisibility(View.VISIBLE);
		}
		
		if (result == null || result.size() < 1) {
			setListViewFooter(false);
			return;
		}

		lastFetchTime = result.get(result.size() - 1).postedTime.getTime();
		fetchFlag = true;
		STORY_ADAPTER.AddPosts(result);
		STORY_ADAPTER.notifyDataSetChanged();
	}
	
	private void setListViewFooter(boolean isLoading){
		if(isLoading){
			storyList.addFooterView(footer);
		} else {
			storyList.removeFooterView(footer);
		}
	}
	
	public void reload(){
		STORY_ADAPTER.notifyDataSetInvalidated();
		STORY_ADAPTER = new PostsAdapter(getActivity());
		storyList.setAdapter(STORY_ADAPTER);
		fetchFlag = true;
		if(storyList.getFooterViewsCount() < 1){
			setListViewFooter(true);
		}
		
		new GetPostAsyncTask(getActivity(), this).execute(new Date().getTime());
	}
}
