package batalha.utils;

public class StringUtils {
	
	public static int toAlphabeticNumber(String word) {
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < word.length(); ++i) {
			if (Character.isLetter(word.charAt(i))) {
				if (Character.isLowerCase(word.charAt(i))) {
					result.append((int)word.charAt(i) - (int)'a'+1);
				} else {
					result.append((int)word.charAt(i) - (int)'A' +1);
				}
			}
		}
		
		return Integer.valueOf(result.toString());
	}
	
}
