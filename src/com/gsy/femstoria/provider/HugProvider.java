package com.gsy.femstoria.provider;

import android.util.Log;

import com.gsy.femstoria.restful.RequestMethod;
import com.gsy.femstoria.restful.RestClientResponse;
import com.gsy.femstoria.restful.RestService;
import com.gsy.femstoria.restful.RestServiceFactory;
import com.gsy.femstoria.restful.ServiceRoute;

public class HugProvider {
	
	private String LOG_TAG = "hug_provider";
	
	public boolean hug(Boolean hug, String postId, String userId) throws Exception{
		try{
			RequestMethod method = hug ? RequestMethod.POST : RequestMethod.DELETE;
			RestService service = new RestService(String.format(RestServiceFactory.createPath(ServiceRoute.HUG), postId, userId), method);
			RestClientResponse response = service.executeWithResponseCode();
			
			if(response.responseCode != 200){
				return false;
			}
			
			return true;
			
		} catch(Exception ex){
			Log.e(LOG_TAG, Log.getStackTraceString(ex));
			throw ex;
		}
	}
}
