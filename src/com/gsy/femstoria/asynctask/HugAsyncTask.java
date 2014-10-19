package com.gsy.femstoria.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.gsy.femstoria.DataStore;
import com.gsy.femstoria.provider.HugProvider;

public class HugAsyncTask extends AsyncTask<String, Void, Boolean>{

	private Context _context;
	private AsyncTaskListener<Boolean> _listener;
	private Boolean _hug;
	
	public HugAsyncTask(Boolean hug, Context context, AsyncTaskListener<Boolean> listener){
		_context = context;
		_listener = listener;
		_hug = hug;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		DataStore dataStore = new DataStore(_context);
		String userId = dataStore.getUserId();
		
		HugProvider hugProvider = new HugProvider();
		try {
			hugProvider.hug(_hug, params[0], userId);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		if(_listener != null){
			_listener.onPreExecuteCallBack();
		}
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		if(_listener != null){
			_listener.onPostExecuteCallBack(result);
		}
	}
}
