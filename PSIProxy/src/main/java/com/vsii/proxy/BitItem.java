package com.vsii.proxy;

import java.io.Serializable;

/**
 * BitItem class represent.
 * 
 * <pre>
 * <code>Index</code>
 * <code>Bit Val.</code>
 * </pre>
 * 
 * @author manhnv
 */
public class BitItem implements Serializable {
	private static final long serialVersionUID = -997588572568640250L;

	private Integer field; // as Field
	private Status status = Status.OFF; // as ON-OFF
	private String val; // as Bit Val.

	public BitItem() {
	}

	public BitItem(Integer field, String val) {
		this.field = field;
		this.val = val;
	}

	public BitItem(Integer field, String val, Status status) {
		this.field = field;
		this.val = val;
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		BitItem other = (BitItem) obj;
		if (field == null) {
			if (other.field != null) return false;
		} else if (!field.equals(other.field)) return false;
		if (status != other.status) return false;
		if (val == null) {
			if (other.val != null) return false;
		} else if (!val.equals(other.val)) return false;
		return true;
	}

	public Integer getField() {
		return field;
	}

	public Status getStatus() {
		return status;
	}

	public String getVal() {
		return val;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((val == null) ? 0 : val.hashCode());
		return result;
	}

	public void setField(Integer field) {
		this.field = field;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
