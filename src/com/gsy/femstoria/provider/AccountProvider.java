package com.gsy.femstoria.provider;

import android.util.Log;

import com.gsy.femstoria.model.NewUser;
import com.gsy.femstoria.restful.JsonHelper;
import com.gsy.femstoria.restful.RequestMethod;
import com.gsy.femstoria.restful.RestClientResponse;
import com.gsy.femstoria.restful.RestService;
import com.gsy.femstoria.restful.RestServiceFactory;
import com.gsy.femstoria.restful.ServiceRoute;

public class AccountProvider {
	
	private String LOG_TAG = "account_provider";
	
	public boolean Register(NewUser newUser) throws Exception{
		
		try {
			RestService service = new RestService(RestServiceFactory.createPath(ServiceRoute.GET_POSTS), RequestMethod.POST);
			service.setEntity(JsonHelper.toJson(newUser));
			RestClientResponse response = service.executeWithResponseCode();

			if(response.responseCode != 200){
				return false;
			}
			
			return true;
		}
		catch (Exception ex) {
			Log.e(LOG_TAG, Log.getStackTraceString(ex));
			throw ex;
		}
	}
	
	//grant_type=password&username=Alice&password=password123
	public void Login(String userName, String password) throws Exception{
		try {
			RestService service = new RestService(RestServiceFactory.createPath(ServiceRoute.GET_POSTS), RequestMethod.POST);
			service.setEntity(String.format("grant_type=password&username=%s&password=%s", userName, password));
			RestClientResponse response = service.executeWithResponseCode();

//			if(response.responseCode != 200){
//				return false;
//			}
//			
//			return true;
		}
		catch (Exception ex) {
			Log.e(LOG_TAG, Log.getStackTraceString(ex));
			throw ex;
		}

	}
}
