package com.vsii.proxy;

/**
 * Transfer output class
 * 
 * @author manhnv
 */
public class Result implements Comparable<Result> {
	private BitMapped bitMapped;
	private String bitVal;
	private Integer byteNo;
	private Integer field;

	public Result(BitMapped bitMapped, Integer byteNo, Integer field, String bitVal) {
		this.bitMapped = bitMapped;
		this.byteNo = byteNo;
		this.field = field;
		this.bitVal = bitVal;
	}

	@Override
	public int compareTo(Result o) {
		// sort by field ascending
		return (this.field > o.getField() ? 1 : (this.field < o.getField() ? -1 : 0));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Result other = (Result) obj;
		if (bitMapped != other.bitMapped) return false;
		if (byteNo == null) {
			if (other.byteNo != null) return false;
		} else if (!byteNo.equals(other.byteNo)) return false;
		return true;
	}

	public BitMapped getBitMapped() {
		return bitMapped;
	}

	public String getBitVal() {
		return bitVal;
	}

	public Integer getByteNo() {
		return byteNo;
	}

	public Integer getField() {
		return field;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bitMapped == null) ? 0 : bitMapped.hashCode());
		result = prime * result + ((byteNo == null) ? 0 : byteNo.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Result [" + (bitMapped != null ? bitMapped + ", " : "")
				+ (byteNo != null ? "Byte=" + byteNo + ", " : "") + (field != null ? "Field=" + field + ", " : "")
				+ (bitVal != null ? "Bit Val.=" + bitVal : "") + "]";
	}

}
