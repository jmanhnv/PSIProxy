package com.vsii.data;

/**
 * EByte represents byte [1..12].
 * 
 * @author manhnv
 */
public enum EByte {
	BYTE_01(1), BYTE_02(2), BYTE_03(3), BYTE_04(4), BYTE_05(5), BYTE_06(6), BYTE_07(7), BYTE_08(8), BYTE_09(9), BYTE_10(
			10), BYTE_11(11), BYTE_12(12);

	private int value;

	private EByte(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
