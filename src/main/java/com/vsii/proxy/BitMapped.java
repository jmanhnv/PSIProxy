package com.vsii.proxy;

/**
 * Class defined for first bit-mapped and second.
 * 
 * @author manhnv
 */
public enum BitMapped {
	FIRST_SET("First Set of Bit-Mapped"), SECOND_SET("Second Set of Bit-Mapped");

	String type;

	private BitMapped(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

}
