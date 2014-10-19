package com.gsy.femstoria.asynctask;

import android.os.AsyncTask;

import com.gsy.femstoria.model.CreatePost;
import com.gsy.femstoria.provider.PostProvider;

public class PostAsyncTask extends AsyncTask<CreatePost, Void, Boolean>{

	private	AsyncTaskListener<Boolean> listener;
	
	public PostAsyncTask(com.gsy.femstoria.asynctask.AsyncTaskListener<Boolean> asyncTaskListener){
		this.listener = asyncTaskListener;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		if(listener != null){
			listener.onPreExecuteCallBack();
		}
	}

	@Override
	protected Boolean doInBackground(CreatePost... posts) {
		PostProvider provider = new PostProvider();
		try {
			boolean success = provider.CreatePost(posts[0]);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		if(listener != null){
			listener.onPostExecuteCallBack(result);
		}
	}

	
	

}
