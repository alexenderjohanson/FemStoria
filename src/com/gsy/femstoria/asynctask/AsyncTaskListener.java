package com.gsy.femstoria.asynctask;

public interface AsyncTaskListener<T> {
	
	void onPostExecuteCallBack(T result);
	
	void onPreExecuteCallBack();
}
