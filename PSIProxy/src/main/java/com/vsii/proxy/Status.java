package com.vsii.proxy;

/**
 * Bit status is either off (has a value of 0) or on (has a value of 1)
 * 
 * @author manhnv
 */
public enum Status {
	OFF(0), ON(1);

	private int status;

	private Status(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
