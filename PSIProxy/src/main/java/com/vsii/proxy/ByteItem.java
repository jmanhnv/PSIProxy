package com.vsii.proxy;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * ByteItem class represent.
 * 
 * <pre>
 * <code>Fields Present Byte</code>,
 * <code>Field</code>, <code>Bit Val.</code>
 * </pre>
 * 
 * @author manhnv
 */
public class ByteItem implements Serializable {
	private static final long serialVersionUID = 2537388510958091857L;

	/* Maximum each byte consists of eight bits */
	private List<BitItem> bitItems = Lists.newArrayList(); // Bits belong the current byte

	private Integer index; // At Byte number

	public ByteItem() {
	}

	public ByteItem(Integer index, List<BitItem> bitItems) {
		this.index = index;
		this.bitItems = bitItems;
	}

	/**
	 * Check field exist in current byte.
	 * 
	 * @param field
	 * @return true if exist, else false
	 */
	public boolean contains(final Integer field) {
		return bitItems.stream().anyMatch(b -> b.getField() != null && b.getField() == field);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ByteItem other = (ByteItem) obj;
		if (index == null) {
			if (other.index != null) return false;
		} else if (!index.equals(other.index)) return false;
		return true;
	}

	public List<BitItem> getBitItems() {
		return bitItems;
	}

	/**
	 * Format byte value as hex string
	 * 
	 * @return
	 */
	public String getByteAsHexVal() {
		// FIXME - uncomment if need
		return String.format("%02X", getByteValue());// Hex2Decimal.decimal2Hex(getByteValue());//
														// Hex2Decimal.formatHexFromDecimal(getByteValue());
	}

	/**
	 * Get value of current byte base on bit on in list
	 * 
	 * @return byte value
	 */
	public Integer getByteValue() {
		int byteValue = 0;

		// filter with exist bit & bit has status is Status.ON
		List<String> collect = bitItems.stream().filter(b -> b.getField() != null && Status.ON.equals(b.getStatus()))
				.map(BitItem::getVal).collect(Collectors.toList());

		// convert and get summary
		for (String s : collect)
			byteValue += Hex2Decimal.hex2Decimal(s);

		return byteValue;
	}

	public Integer getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		return result;
	}

	public void setBitItems(List<BitItem> bitItems) {
		this.bitItems = bitItems;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
