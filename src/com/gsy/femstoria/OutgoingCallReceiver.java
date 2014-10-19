package com.gsy.femstoria;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OutgoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (null == bundle)
            return;
        //String phoneNumber =intent.getStringExtra(Intent.action_n);
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        
        if(phoneNumber.equals("336786742"))
        {
        	this.setResultData(null);
        	Intent i = new Intent();
        	i.setClass(context, SplashActivity.class);
        	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	context.startActivity(i);
        }
	}

}