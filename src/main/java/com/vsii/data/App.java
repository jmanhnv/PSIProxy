package com.vsii.data;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.vsii.proxy.Const;

public class App {
	private static String getBitVal(final int index) {
		String bitVal = Const.EMPTY;

		// with index [1..8]
		switch (index) {
		case 1:
			bitVal = EBit.BIT_1.getValue();
			break;
		case 2:
			bitVal = EBit.BIT_2.getValue();
			break;
		case 3:
			bitVal = EBit.BIT_3.getValue();
			break;
		case 4:
			bitVal = EBit.BIT_4.getValue();
			break;
		case 5:
			bitVal = EBit.BIT_5.getValue();
			break;
		case 6:
			bitVal = EBit.BIT_6.getValue();
			break;
		case 7:
			bitVal = EBit.BIT_7.getValue();
			break;
		case 8:
			bitVal = EBit.BIT_8.getValue();
			break;
		default:
			break;
		}

		return bitVal;
	}

	private static Integer getLength(final String val) {
		if (!Strings.isNullOrEmpty(val)) return val.trim().length();
		return 0;
	}

	public static void main(String[] args) {
		Map<Integer, PSField> map = Maps.newHashMap();

		// "First Set of Bit-Mapped"
		// PSField field = new PSField(EByte.BYTE_01, EBit.BIT_1, Req.NO, HostField.HF_001, getBitVal(1), "Supervisor
		// ID",
		// "XXXX", null, getLength("XXXX"), null, "TLTSNR");

		// "Second Set of Bit-Mapped"
	}

}
