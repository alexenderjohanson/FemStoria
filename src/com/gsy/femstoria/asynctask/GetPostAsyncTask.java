package com.gsy.femstoria.asynctask;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.gsy.femstoria.DataStore;
import com.gsy.femstoria.model.Post;
import com.gsy.femstoria.provider.PostProvider;

public class GetPostAsyncTask extends AsyncTask<Long, Void, List<Post>>{
	
	private AsyncTaskListener<List<Post>> _listener;
	private Context _context;
	
	public GetPostAsyncTask(Context context, AsyncTaskListener<List<Post>> listener){
		_listener = listener;
		_context = context;
	}

	@Override
	protected void onPostExecute(List<Post> result) {
		super.onPostExecute(result);
		_listener.onPostExecuteCallBack(result);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		_listener.onPreExecuteCallBack();
	}

	@Override
	protected List<Post> doInBackground(Long... params) {
		
		List<Post> result = null;
		PostProvider provider = new PostProvider();
		
		DataStore dataStore = new DataStore(_context);
		String  userName = dataStore.getUserId();
		try {
			result = provider.GetPosts(params[0], userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
