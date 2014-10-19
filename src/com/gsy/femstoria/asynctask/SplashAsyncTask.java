package com.gsy.femstoria.asynctask;

import java.util.UUID;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings.Secure;

import com.gsy.femstoria.DataStore;

public class SplashAsyncTask extends AsyncTask<Void, Void, Void>{

	private AsyncTaskListener<Void> _listener;
	private Context _context;
	
	public SplashAsyncTask(Context context, AsyncTaskListener<Void> listener){
		_listener = listener;
		_context = context;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		DataStore dataStore = new DataStore(_context);
		String userName = dataStore.getUserName();
		String password = dataStore.getPassword();
		
		if(dataStore.getUserId() == ""){
			String androidId = Secure.getString(_context.getContentResolver(), Secure.ANDROID_ID);
			
			if(androidId != null && androidId.length() > 0){
				dataStore.setUserId(androidId);
			} else {
				dataStore.setUserId(UUID.randomUUID().toString());
			}
		}
		
		if(userName.length() < 1 || password.length() < 1){
			return null;
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		
		if(_listener != null){
			_listener.onPostExecuteCallBack(result);
		}
	}
}
