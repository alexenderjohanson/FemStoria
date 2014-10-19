package com.gsy.femstoria.fb;


import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class SessionStatusCallback implements StatusCallback {


	@Override
	public void call(Session session, SessionState state, Exception exception) {
		if (session.isOpened()) {

			Log.d("facebook", session.getAccessToken());
			// make request to the /me API
			Request.newMeRequest(session, new Request.GraphUserCallback() {

						// callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response) {
							if (user != null) {
							}
						}
					}).executeAsync();
		}

	}

}
