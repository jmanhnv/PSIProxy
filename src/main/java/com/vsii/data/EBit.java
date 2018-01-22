package com.vsii.data;

/**
 * EBit represents bit [1..8].
 * 
 * @author manhnv
 */
public enum EBit {
	BIT_1("80"), BIT_2("40"), BIT_3("20"), BIT_4("10"), BIT_5("08"), BIT_6("04"), BIT_7("02"), BIT_8("01");

	private String value;

	private EBit(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
