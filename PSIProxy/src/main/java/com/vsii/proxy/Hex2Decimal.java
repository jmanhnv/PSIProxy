package com.vsii.proxy;

/******************************************************************************
 * Utility class use to convert hex to decimal end reversed
 * <p>
 * The hexadecimal numeral system, also known as just hex, is a numeral system
 * made up of 16 symbols (base 16). The standard numeral system is called
 * decimal (base 10) and uses ten symbols: 0,1,2,3,4,5,6,7,8,9. Hexadecimal uses
 * the decimal numbers and includes six extra symbols. There are no symbols that
 * mean ten, or eleven etc. so these symbols are letters taken from the English
 * alphabet: A, B, C, D, E and F. Hexadecimal A = decimal 10, and hexadecimal F
 * = decimal 15.
 * </p>
 * 
 * <pre>
 * Algorithm
 * <blockquote> Steps:
 * <ol>
 * <li>Get the last digit of the hex number, call this digit the currentDigit.
 * <li>Make a variable, let's call it power. Set the value to 0.
 * <li>Multiply the current digit with (16^power), store the result.
 * <li>Increment power by 1.
 * <li>Set the the currentDigit to the previous digit of the hex number.
 * <li>Repeat from step 3 until all digits have been multiplied.
 * <li>Sum the result of step 3 to get the answer number.
 * </ol>
 * </blockquote>
 * <pre>
 * 
 * <pre>
 * Example: Convert the number 1128 HEXADECIMAL to DECIMAL
 * <blockquote>
 * Result: 1x(16^3) + 1x(16^2) + 2x(16^1) + 8x(16^0) = (8+32+256+4096)=4392
 * </blockquote>
 * </pre>
 * 
 * @author manhnv
 ******************************************************************************/
public class Hex2Decimal implements Const {
	// Use the decimal value for each hexadecimal digit. For 0-9; A = 10, B = 11, C
	// = 12, D = 13, E = 14, and F = 15
	private static final String digits = "0123456789ABCDEF";

	/**
	 * Convert decimal to hex value. Note: decimal is a non-negative integer
	 * 
	 * @param decimal
	 *            to be converted
	 * @return hex
	 */
	public static String decimal2Hex(int decimal) {
		if (decimal == 0) return ZERO;

		String hex = EMPTY;
		while (decimal > 0) {
			int digit = decimal % 16; // right most digit
			hex = digits.charAt(digit) + hex; // string concatenation
			decimal = decimal / 16;
		}

		return hex;
	}

	/**
	 * Format hex value within leading zero with 4 characters length
	 * 
	 * @param decimal
	 * @return
	 */
	public static String formatHexFromDecimal(int decimal) {
		// System.out.printf("0x%02X", decimal);
		return String.format("%04X", decimal);
	}

	/**
	 * Convert hex string to decimal value.
	 * 
	 * @param hex
	 *            to be converted
	 * @return decimal
	 */
	public static int hex2Decimal(String hex) {
		hex = hex.toUpperCase();
		int val = 0;
		for (int i = 0; i < hex.length(); i++) {
			char c = hex.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		return val;
	}

	@SuppressWarnings("unused")
	private static void printHex() {
		for (int i = 0; i < 255; i++) {
			if (i % 16 == 0) {
				System.out.println();
			}
			System.out.printf("%04X ", i);
		}
	}
}
