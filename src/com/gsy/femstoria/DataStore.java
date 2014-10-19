package com.gsy.femstoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataStore {
	
	private final static String SHARED_PREFERENCE_KEY = "watershortage";
	private Editor editor;
	private SharedPreferences pref;
	
	private static final String KEY_EMAIL = "email"; 
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_USERID = "userId";
	
	public DataStore(Context context){
		pref = context.getSharedPreferences(SHARED_PREFERENCE_KEY, context.MODE_PRIVATE);
		editor = pref.edit(); 
	}
	
	public String getUserName(){
		return pref.getString(KEY_EMAIL, "");
	}
	
	public void setUserName(String userName){
		editor.putString(KEY_EMAIL, userName);
		editor.commit();
	}
	
	public String getPassword(){
		return pref.getString(KEY_PASSWORD, "");
	}
	
	public void setPassword(String password){
		editor.putString(KEY_PASSWORD, password);
		editor.commit();
	}
	
	public String getUserId(){
		return pref.getString(KEY_USERID, "");
	}
	
	public void setUserId(String userId){
		editor.putString(KEY_USERID, userId);
		editor.commit();
	}
	
//	public boolean getSelectedZone(int zone){
//		
//		switch(zone){
//		case 1:
//			return pref.getBoolean(ZONE1_KEY, false);
//		case 2:
//			return pref.getBoolean(ZONE2_KEY, false);
//		case 3:
//			return pref.getBoolean(ZONE3_KEY, false);
//		case 4:
//			return pref.getBoolean(ZONE4_KEY, false);
//		}
//		
//		return false;
//	}
//	
//	private void setSelectedZone(int selectedZone, boolean enable){
//		
//		switch(selectedZone){
//		case 1:
//			editor.putBoolean(ZONE1_KEY, enable);
//			break;
//		case 2:
//			editor.putBoolean(ZONE2_KEY, enable);
//			break;
//		case 3:
//			editor.putBoolean(ZONE3_KEY, enable);
//			break;
//		case 4:
//			editor.putBoolean(ZONE4_KEY, enable);
//			break;
//		}
//		
//		editor.commit();
//	}
//	
//	public String getSelectedArea(int zone){
//		
//		switch(zone){
//		case 1:
//			return pref.getString(AREA1_KEY, "");
//		case 2:
//			return pref.getString(AREA2_KEY, "");
//		case 3:
//			return pref.getString(AREA3_KEY, "");
//		case 4:
//			return pref.getString(AREA4_KEY, "");
//		}
//		
//		return "";
//	}
//	
//	public void addSelectedArea(int selectedZone, String selectedNewArea){
//		
//		String selectedArea = getSelectedArea(selectedZone);
//		if(selectedArea.contains(selectedNewArea))
//			return;
//		
//		if(selectedArea.length() == 0){
//			selectedArea = selectedNewArea;
//		}else {
//			selectedArea += " ~ " + selectedNewArea;
//		}
//		
//		switch(selectedZone){
//		case 1:
//			editor.putString(AREA1_KEY, selectedArea);
//			break;
//		case 2:
//			editor.putString(AREA2_KEY, selectedArea);
//			break;
//		case 3:
//			editor.putString(AREA3_KEY, selectedArea);
//			break;
//		case 4:
//			editor.putString(AREA4_KEY, selectedArea);
//			break;
//		}
//		
//		setSelectedZone(selectedZone, true);
//		
//		editor.commit();
//		
//		Log.d("Data Store", selectedArea);
//		Log.d("Data Store", "Zone:" + selectedZone + " " + getSelectedZone(selectedZone));
//	}
//	
//	public void removeSelectedArea(int selectedZone, String selectedNewArea){
//		
//		String selectedArea = getSelectedArea(selectedZone);
//		if(!selectedArea.contains(selectedNewArea))
//			return;
//		
//		String[] splits = selectedArea.split("~");
//		
//		if(splits == null || splits.length == 0)
//			return;
//		
//		List<String> splitsList = Arrays.asList(splits);
//		List<String> resultList = new ArrayList<String>();
//		for (String string : splitsList) {
//			if(string.trim().equals(selectedNewArea.trim())){
//				continue;
//			}else{
//				resultList.add(string);
//			}
//		}
//		
//		String result = "";
//		for (String string : resultList) {
//			if(result.equals("")){
//				result = string.trim();
//			} else {
//				result += " ~ " + string.trim();
//			}
//		}
//		
//		switch(selectedZone){
//			case 1:
//				editor.putString(AREA1_KEY, result);
//				break;
//			case 2:
//				editor.putString(AREA2_KEY, result);
//				break;
//			case 3:
//				editor.putString(AREA3_KEY, result);
//				break;
//			case 4:
//				editor.putString(AREA4_KEY, result);
//				break;
//		}
//		
//		if(result.length() == 0){
//			setSelectedZone(selectedZone, false);
//		}
//		
//		Log.d("Data Store", result);
//		Log.d("Data Store", "Zone:" + selectedZone + " " + getSelectedZone(selectedZone));
//		
//		editor.commit();
//	}
}
