package program;

/**
 * A class to store general functions
 */

public class Functions {
	
	/** Check if string is only digits (Integer) **/
	public static boolean isInt(String str) {
		try {
			int integer = Integer.parseInt(str);
			return true;
		}catch (NumberFormatException e) {
			System.out.println("Error: '" + str + "' is not a number");
			return false;
		}
	}

	
	
	/** Compare String function **/
	public static boolean compareStrings(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		boolean result = false; // result
		if (s1.length() >= s2.length()) {
			for (int count = 0; count < s1.length(); count ++) {
					if (s1.charAt(count) == s2.charAt(0)){
						for (int count2 = 0; count2 < s2.length(); count2++){
							try {
								if (s1.charAt(count + count2) == s2.charAt(count2)) {
									if (count2 == s2.length() -1){ 
										result = true;
									}
								}
							}catch (Exception e) {}
							}								
						}
					}
				}
		return result;
	}
	

}
