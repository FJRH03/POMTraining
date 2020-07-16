package data;

public class DataGenerator {
	
	private static final String ALPHABETIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVXYZ0123456789";
	private static final String NUMERIC_STRING = "0123456789";
	
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while(count-- != 0) {
			int character = (int) (Math.random() *  ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		
		return builder.toString();
	}
	
	
	public static String randomAlphabetic(int count) {
		StringBuilder builder = new StringBuilder();
		while(count-- != 0) {
			int character = (int) (Math.random() *  ALPHABETIC_STRING.length());
			builder.append(ALPHABETIC_STRING.charAt(character));
		}
		
		return builder.toString();
	}
	
	public static String randomNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while(count-- != 0) {
			int character = (int) (Math.random() * NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}
		
		return builder.toString();
	}

}
