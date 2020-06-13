package bhtweb.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

	public static String getStringFromDate (Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = simpleDateFormat.format(date);
		return result;
	}
	
	public static Timestamp getTimestamptFromDate (Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
	
}
