package utils;

public class InputUtils {

	public static boolean isOnlyDigitsAndChars(String input){
		return input.matches("^[a-zA-Z0-9]*$");
	}
}
