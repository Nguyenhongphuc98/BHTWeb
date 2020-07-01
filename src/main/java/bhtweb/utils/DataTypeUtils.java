package bhtweb.utils;

public class DataTypeUtils{
	public static String getStringFromBoolean (Boolean condition) {
		if (condition)
			return "1";
		return "0";
	}
}