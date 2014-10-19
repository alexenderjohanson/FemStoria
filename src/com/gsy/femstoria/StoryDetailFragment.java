package com.gsy.femstoria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gsy.femstoria.asynctask.AsyncTaskListener;
import com.gsy.femstoria.asynctask.HugAsyncTask;
import com.gsy.femstoria.model.Post;
import com.gsy.femstoria.utility.TimeHelper;

/**
 * A fragment representing a single Story detail screen.
 * This fragment is either contained in a {@link StoryListActivity}
 * in two-pane mode (on tablets) or a {@link StoryDetailActivity}
 * on handsets.
 */
public class StoryDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_POST = "post";

    /**
     * The dummy content this fragment is presenting.
     */
    public static Post ITEM;
    public static TextView _hug;
    private TextView _content;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StoryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_POST)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            ITEM = (Post)getArguments().getSerializable(ARG_POST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (ITEM != null) {
            ((TextView) rootView.findViewById(R.id.tvStoryDetail)).setText(ITEM.message);
            TextView datePosted = ((TextView) rootView.findViewById(R.id.tvDatePosted));
            TextView userName = (TextView) rootView.findViewById(R.id.tvUserName);
            
            _hug = (TextView) rootView.findViewById(R.id.tvHug);
            _content = (TextView) rootView.findViewById(R.id.tvStoryDetail);
            
            String userNameText = ITEM.userName;
            if(userNameText == null || userNameText.length() < 1){
            	userNameText = getActivity().getString(R.string.common_anonymous);
            }
            userName.setText(userNameText);
            
            String passedTime = TimeHelper.getPassedTime(ITEM.postedTime);
            datePosted.setText(passedTime);
           
            TextView share = (TextView) rootView.findViewById(R.id.tvShare);            
            share.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					Intent sendIntent = new Intent();
			        sendIntent.setAction(Intent.ACTION_SEND);
			        sendIntent.putExtra(Intent.EXTRA_TEXT, "FemStoria");
			        sendIntent.setType("text/plain");
			        
			        getActivity().startActivity(sendIntent);
				}
            	
            });
            
            _hug.setOnClickListener(new OnClickListener(){

    			@Override
    			public void onClick(View v) {
    				
    				HugAsyncTask asyncTask = new HugAsyncTask(!ITEM.huged, getActivity(), new AsyncTaskListener<Boolean>(){

    					@Override
    					public void onPostExecuteCallBack(Boolean success) {
    						if(success){
    							ITEM.huged = !ITEM.huged; 
    						} else {
    							if(ITEM.huged){
    								_hug.setText(R.string.common_hugged);
    							} else {
    								_hug.setText(R.string.common_hug);
    							}
    						}
    					}

    					@Override
    					public void onPreExecuteCallBack() {
    						if(ITEM.huged){
    							_hug.setText(R.string.common_hug);
    						} else {
    							_hug.setText(R.string.common_hugged);
    						}
    					}
    					
    				});
    				
    				asyncTask.execute(ITEM.postId);

    				String userNameText = ITEM.userName;
    	            if(userNameText == null || userNameText.length() < 1){
    	            	userNameText = getActivity().getString(R.string.common_anonymous);
    	            }
    				
    				Toast toast = Toast.makeText(getActivity(), "You Hugged " + userNameText, Toast.LENGTH_SHORT);
    				toast.show();
    				
    			}});
            
            if(ITEM.huged){
    			_hug.setText(R.string.common_hugged);    			
    		}
            
//            offerHelp.setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View v) {
//					Intent i = new Intent(StoryListActivity.CONTEXT, OfferHelpActivity.class);
//					StoryListActivity.CONTEXT.startActivity(i);
//				}
//            	
//            });
            
        }

        return rootView;
    }
}
