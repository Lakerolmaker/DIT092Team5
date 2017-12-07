package program;

/**
 * A class to store general functions
 */

public class Functions {
	public static double SEARCH_PERCENT_ALLOWANCE = 0.75;
	
	/** Check if string is only digits (Integer) **/
	public static boolean isInt(String str) {
		try {
			int integer = Integer.parseInt(str);
			return true;
		}catch (NumberFormatException e) {
			//System.out.println("Error: '" + str + "' is not a number");
			return false;
		}
	}

	
	
	/** Compare String function **/
	public static boolean compareStrings(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		if (s1.length() > s2.length()) {
			String tmp = s1;
			s1 = s2;
			s2 = tmp;
		}
		double percent = 0;
		double tmpPercent = compToPercent(s1, s2);
		if (s1.length() <= s2.length() || s1.length() > 1) {
			for (int i = 0; i < s2.length() -1; i++) {
				if (s1.charAt(0) == s2.charAt(i) || s1.charAt(1) == s2.charAt(i)) {
					if (i + s1.length() <= s2.length()) {
						tmpPercent = compToPercent(s1, s2.substring(i, i + s1.length()));

					}else {
						tmpPercent = compToPercent(s1, s2.substring(i));
					}
					if (tmpPercent >= percent) {
						percent = tmpPercent;
					}
				}
			}
		}
		if (percent >= SEARCH_PERCENT_ALLOWANCE) {
			return true;
		}else {
			return false;
		}
	}
	
	public static double compToPercent(String s1, String s2) {
		if (s1.length() < s2.length()) { // Checks the string length before calling the Levenshtein function.
			String tmp = s1;
			s1 = s2;
			s2 = tmp;
		}
		double result = (s1.length() - distance(s1, s2)) / (double) s1.length(); // convert the results to percent
		return result;	
	}
	
	/** Levenshtein Distance
	*   This code is taken from: http://rosettacode.org/wiki/Levenshtein_distance#Java
	*/
	public static int distance(String a, String b) {
	    a = a.toLowerCase();
	    b = b.toLowerCase();
	    // i == 0
	    int [] costs = new int [b.length() + 1];
	    for (int j = 0; j < costs.length; j++)
	        costs[j] = j;
	    for (int i = 1; i <= a.length(); i++) {
	        // j == 0; nw = lev(i - 1, j)
	        costs[0] = i;
	        int nw = i - 1;
	        for (int j = 1; j <= b.length(); j++) {
	            int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
	            nw = costs[j];
	            costs[j] = cj;
	        }
	    }
	    return costs[b.length()];
	}

}
