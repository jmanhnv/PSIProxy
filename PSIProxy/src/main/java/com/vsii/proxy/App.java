package com.vsii.proxy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

/**
 * App class use to execute and test application.
 * 
 * @author manhnv
 */
public class App implements Const {
	@SuppressWarnings("unused")
	private static void execBreakField() {
		// Break field to lookup on FIRST_SET OR SECOND_SET
		Integer[] fields = { 9, 126, 127, 99, 31, 12, 41, 97, 130, 73, 40, 100, 42, 98, 51, 129 };

		// Get fields list
		Dummy.viewRangeBitMapped();

		// Break field range
		Dummy.viewBreakField(fields);

		// For first bit-mapped
		List<Result> firstResults = Dummy.getResults(BitMapped.FIRST_SET, fields);
		Dummy.printResult(BitMapped.FIRST_SET, firstResults);
		//
		Dummy.countByteInFrequency(firstResults);

		// For second bit-mapped
		List<Result> secondResults = Dummy.getResults(BitMapped.SECOND_SET, fields);
		Dummy.printResult(BitMapped.SECOND_SET, secondResults);
		//
		Dummy.countByteInFrequency(secondResults);
	}

	@SuppressWarnings("unused")
	private static void execHex2Decimal() {
		System.out.println("Hexadecimal -> Decimal: " + Hex2Decimal.hex2Decimal("1F"));
		System.out.println("Decimal -> Hexadecimal: " + Hex2Decimal.decimal2Hex(31));
	}

	public static void main(String[] args) {
		// Validate arguments
		if (args == null || args.length == 0) {
			System.err.println(
					"Provide the args, args must (e.g: [9 126 127 99 31 12 41 97 130 73 40 100 42 98 51 129])");
			System.exit(0);
		}

		// Pull to console
		// Arrays.stream(args).forEach(System.out::println);

		// Distinct arguments
		List<String> clones = Arrays.stream(args).distinct().collect(Collectors.toList());
		String joinFields = clones.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(COMMA));
		System.out.println(String.format("Fields filter: [%s]\n", joinFields));

		// Transfer arguments into fields
		Integer[] fields = new Integer[clones.size()];
		for (int i = 0; i < clones.size(); i++)
			fields[i] = Integer.valueOf(clones.get(i));

		// Initialize bit-mapped
		Dummy.init();

		//////////////////////// ---> START PROCESSING
		// For first bit-mapped
		List<Result> firstResults = Dummy.getResults(BitMapped.FIRST_SET, fields);
		Dummy.printResult(BitMapped.FIRST_SET, firstResults);
		//
		// Dummy.countByteInFrequency(firstResults);
		// Check byte value
		console(BitMapped.FIRST_SET, fields);

		// For second bit-mapped
		List<Result> secondResults = Dummy.getResults(BitMapped.SECOND_SET, fields);
		Dummy.printResult(BitMapped.SECOND_SET, secondResults);
		//
		// Dummy.countByteInFrequency(secondResults);
		// Check byte value
		console(BitMapped.SECOND_SET, fields);

		////////////////////// <--- END PROCESSING
		// Destroy bit-mapped
		Dummy.destroy();
	}

	private static void console(BitMapped bitMapped, Integer... fields) {
		System.out.println("\n*** Calculate value for " + bitMapped.toString() + " ***");
		Set<ByteItem> byteItems = Sets.newHashSet(Dummy.lookupByte(bitMapped, fields));
		byteItems.stream().forEach(f -> {
			System.out.println(String.format("Byte[%d], Value[%d], Value as Hex[%s]", f.getIndex(), f.getByteValue(),
					f.getByteAsHexVal(), null, null));
		});
	}

}
