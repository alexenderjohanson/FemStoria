package com.gsy.femstoria.provider;

import java.util.List;

import android.util.Log;

import com.gsy.femstoria.model.CreatePost;
import com.gsy.femstoria.model.Post;
import com.gsy.femstoria.restful.JsonHelper;
import com.gsy.femstoria.restful.RequestMethod;
import com.gsy.femstoria.restful.RestClientResponse;
import com.gsy.femstoria.restful.RestService;
import com.gsy.femstoria.restful.RestServiceFactory;
import com.gsy.femstoria.restful.ServiceRoute;

public class PostProvider {
	
	private String LOG_TAG = "post_provider";
	
	public List<Post> GetPosts(long epoch, String userId) throws Exception{
		
		try {
			RestService service = new RestService(String.format(RestServiceFactory.createPath(ServiceRoute.GET_POSTS), Long.toString(epoch/1000), userId), RequestMethod.GET);
			RestClientResponse response = service.executeWithResponseCode();

			if(response.responseCode != 200){
				//handle other reponse code
				return null;
			}
			List<Post> result = JsonHelper.fromJsonCollection(Post.class, response.response);

			return result;

		}
		catch (Exception ex) {
			Log.e(LOG_TAG, Log.getStackTraceString(ex));
			throw ex;
		}
	}
	
	public boolean CreatePost(CreatePost post) throws Exception{
		try{
			RestService service = new RestService(RestServiceFactory.createPath(ServiceRoute.CREATE_POST), RequestMethod.POST);
			service.setEntity(JsonHelper.toJson(post));
			RestClientResponse response = service.executeWithResponseCode();
			
			if(response.responseCode != 201){
				return false;
			}
			
			return true;
		} catch(Exception ex){
			Log.e(LOG_TAG, Log.getStackTraceString(ex));
			throw ex;
		}
	}
}
