package com.vsii.data;

import java.io.Serializable;

/**
 * The following table shows the fields associated with each field-present byte and their formats.
 * 
 * @author manhnv
 */
public class PSField implements Serializable {
	private static final long serialVersionUID = -4315755602637986499L;

	private EByte byteNo;
	private EBit bitNo;
	private Req req;

	private Integer hostField;
	private String bitVal;
	private String fieldName;
	private String fieldFormat;
	private String dataType;
	private Integer length;
	private String desc;
	private String psb00301;

	public PSField() {
	}

	public PSField(EByte byteNo, EBit bitNo, Req req, Integer hostField, String bitVal, String fieldName,
			String fieldFormat, String dataType, Integer length, String desc, String psb00301) {
		this.byteNo = byteNo;
		this.bitNo = bitNo;
		this.req = req;
		this.hostField = hostField;
		this.bitVal = bitVal;
		this.fieldName = fieldName;
		this.fieldFormat = fieldFormat;
		this.dataType = dataType;
		this.length = length;
		this.desc = desc;
		this.psb00301 = psb00301;
	}

	public EByte getByteNo() {
		return byteNo;
	}

	public void setByteNo(EByte byteNo) {
		this.byteNo = byteNo;
	}

	public EBit getBitNo() {
		return bitNo;
	}

	public void setBitNo(EBit bitNo) {
		this.bitNo = bitNo;
	}

	public Integer getHostField() {
		return hostField;
	}

	public void setHostField(Integer hostField) {
		this.hostField = hostField;
	}

	public String getBitVal() {
		return bitVal;
	}

	public void setBitVal(String bitVal) {
		this.bitVal = bitVal;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Req getReq() {
		return req;
	}

	public void setReq(Req req) {
		this.req = req;
	}

	public String getFieldFormat() {
		return fieldFormat;
	}

	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPsb00301() {
		return psb00301;
	}

	public void setPsb00301(String psb00301) {
		this.psb00301 = psb00301;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
