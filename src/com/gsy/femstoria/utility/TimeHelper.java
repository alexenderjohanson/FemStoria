package com.gsy.femstoria.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

import com.gsy.femstoria.ApplicationContext;
import com.gsy.femstoria.R;

public class TimeHelper {

	public static String getPassedTime(Date date) {
		
		Context context = ApplicationContext.getAppContext();
		
		Long checkinTime = date.getTime();
		Date dateNow = new Date();

		long secondsBetween = Math.abs(dateNow.getTime() / 1000 - (checkinTime / 1000 ));
		if (secondsBetween < 60) {
			return String.format(context.getString(R.string.general_seconds), String.valueOf(secondsBetween));
		}
		long minutesBetween = Math.abs(secondsBetween / 60);
		if (minutesBetween < 60) {
			return String.format(context.getString(R.string.general_minutes), String.valueOf(minutesBetween));
		}
		long hoursBetween = Math.abs(minutesBetween / 60);
		if (hoursBetween < 24) {
			return String.format(context.getString(R.string.general_hours), String.valueOf(hoursBetween));
		}
		long daysBetween = Math.abs(hoursBetween / 24);
		if (daysBetween < 7) {
			return String.format(context.getString(R.string.general_days), String.valueOf(daysBetween));
		}

		long weeksBetween = Math.abs(daysBetween / 7);
		if (weeksBetween < 4) {
			return String.format(context.getString(R.string.general_weeks), String.valueOf(weeksBetween));
		}

		long monthsBetween = Math.abs(daysBetween / 30);
		if (monthsBetween < 12) {
			if (monthsBetween < 1)
				monthsBetween = 1;

			return String.format(context.getString(R.string.general_months), String.valueOf(monthsBetween));
		}
		// sounds like more than a 12 months
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date(checkinTime));
	}
}
