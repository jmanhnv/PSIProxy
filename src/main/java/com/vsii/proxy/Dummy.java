package com.vsii.proxy;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Dummy class to execute program
 * 
 * @author manhnv
 */
public final class Dummy implements Const {
	/* Represent Bit [1..8] */
	enum EBIT {
		// x80,x40,x20,x10,x08,x04,x02,x01
		BIT_1("80"), BIT_2("40"), BIT_3("20"), BIT_4("10"), BIT_5("08"), BIT_6("04"), BIT_7("02"), BIT_8("01");

		String val;

		private EBIT(String val) {
			this.val = val;
		}

		public String getVal() {
			return val;
		}
	}

	/* Represent Byte [1..12] */
	enum EBYTE {
		// x=00 .. x=11
		BYTE_1("00"), BYTE_10("09"), BYTE_11("10"), BYTE_12("11"), BYTE_2("01"), BYTE_3("02"), BYTE_4("03"), BYTE_5(
				"04"), BYTE_6("05"), BYTE_7("06"), BYTE_8("07"), BYTE_9("08");

		String val;

		private EBYTE(String val) {
			this.val = val;
		}

		public String getVal() {
			return val;
		}
	}

	private static Map<BitMapped, Map<Integer, ByteItem>> map;

	/**
	 * Break range of field to lookup
	 * 
	 * @param bitMap
	 * @param fields
	 * @return
	 */
	private static List<Integer> breakFieldSet(final BitMapped bitMap, Integer... fields) {
		if (BitMapped.FIRST_SET.equals(bitMap)) // [First Set of Bit-Mapped] From [1] to [96]
			return Arrays.asList(fields).stream().filter(f -> f <= 96).collect(Collectors.toList());

		// [Second Set of Bit-Mapped] From [97] to [131]
		return Arrays.asList(fields).stream().filter(f -> f > 96).collect(Collectors.toList());
	}

	// First Set of Bit-Mapped
	private static void buildFirstBitMapped(final int bytes) {
		int fieldIdx = 1;
		Map<Integer, ByteItem> m = Maps.newHashMap();

		// current - first set includes 12 bytes
		for (int i = 1; i <= bytes; i++) {
			// each byte consists of eight bits
			List<BitItem> bitItems = Lists.newArrayList();

			for (int j = 0; j < EBIT.values().length; j++) {
				String fieldVal = getFieldVal(i, j + 1);
				BitItem bitItem = new BitItem(fieldIdx, fieldVal);

				bitItems.add(bitItem);

				fieldIdx++;
			}

			ByteItem byteItem = new ByteItem(i, bitItems);
			m.put(i, byteItem);
		}

		map.put(BitMapped.FIRST_SET, m);
	}

	// Second Set of Bit-Mapped
	private static void buildSecondBitMapped(final int bytes) {
		int fieldIdx = 1;
		// calculate field index at second bit-mapped
		for (Map.Entry<BitMapped, Map<Integer, ByteItem>> entry : map.entrySet()) {
			if (entry.getKey().equals(BitMapped.FIRST_SET)) {
				for (Map.Entry<Integer, ByteItem> m : entry.getValue().entrySet()) {
					ByteItem by = m.getValue();

					fieldIdx += by.getBitItems().size();
				}
			}
		}

		Map<Integer, ByteItem> m = Maps.newHashMap();
		// current - second set includes only 5 bytes
		for (int i = 1; i <= bytes; i++) {
			// each byte consists of eight bits
			List<BitItem> bitItems = Lists.newArrayList();
			if (i < bytes) {
				for (int j = 0; j < EBIT.values().length; j++) {
					String fieldVal = getFieldVal(i, j + 1);
					BitItem bitItem = new BitItem(fieldIdx, fieldVal);

					bitItems.add(bitItem);

					fieldIdx++;
				}
			} else {// At byte fifth only consists three bits
				for (int j = 0; j < EBIT.values().length; j++) {
					// BitItem bitItem;
					if (j < 3) {
						String fieldVal = getFieldVal(i, j + 1);
						BitItem bitItem = new BitItem(fieldIdx, fieldVal);

						bitItems.add(bitItem);

						fieldIdx++;
					}
				}
			}

			ByteItem byteItem = new ByteItem(i, bitItems);
			m.put(i, byteItem);
		}

		map.put(BitMapped.SECOND_SET, m);
	}

	///////////////////////////////////////////////////////////////////////////
	/**
	 * Method use to calculate the value of each byte on each bit-mapped.
	 * 
	 * @param bitMap
	 *            set of bit-mapped to be calculated
	 * @return result set
	 */
	public static Map<BitMapped, Map<Integer, Integer>> calcBitMapped(final BitMapped bitMap) {
		Map<BitMapped, Map<Integer, Integer>> rs = Maps.newHashMap();
		// FIXME - to be continued
		return rs;
	}

	/**
	 * @return length of message
	 */
	public static Integer calcMessageLength() {
		// FIXME - to be continued
		return 0;
	}
	///////////////////////////////////////////////////////////////////////////

	/**
	 * Method use to count all byte with frequency in each bit-mapped.
	 * 
	 * @param results
	 */
	public static void countByteInFrequency(final List<Result> results) {
		System.out.println("\n*** Count all with frequency");
		Set<Result> uniqueSet = Sets.newHashSet(results);
		for (Result rs : uniqueSet) {
			System.out.println(rs + ": " + Collections.frequency(results, rs));
		}
	}

	///////////////////// ---> START DESTROY
	/**
	 * Method use to reset all dummy data.
	 */
	public static void destroy() {
		if (map != null) map.clear();
	}
	///////////////////// ---> END DESTROY

	@SuppressWarnings("unused")
	private static List<Integer> findCommonItem(List<Integer> root, List<Integer> target) {
		List<Integer> commons = Lists.newArrayList();
		Set<Integer> rootArr = new LinkedHashSet<Integer>(root);
		Set<Integer> targetArr = new LinkedHashSet<Integer>(target);

		for (Integer _value : targetArr)
			if (!rootArr.add(_value)) commons.add(_value);

		return commons;
	}

	/**
	 * Get first bit-mapped or second bit-mapped bases on bit-mapped type provides.
	 * 
	 * @param bitMap
	 *            bit-mapped type
	 * @return
	 */
	private static Map<BitMapped, Map<Integer, ByteItem>> getCurrentBitMapped(final BitMapped bitMap) {
		if (BitMapped.FIRST_SET.equals(bitMap)) // GET FIRST MAP
			return map.entrySet().stream().filter(m -> BitMapped.FIRST_SET.equals(m.getKey()))
					.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

		// GET SECOND MAP
		return map.entrySet().stream().filter(m -> BitMapped.SECOND_SET.equals(m.getKey()))
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}

	// Collect all fields in first bit-mapped and second bit-mapped
	private static List<Integer> getFields(final BitMapped bitMap) {
		List<Integer> fields = Lists.newArrayList();
		Map<BitMapped, Map<Integer, ByteItem>> first = getCurrentBitMapped(bitMap);
		for (Map.Entry<BitMapped, Map<Integer, ByteItem>> m : first.entrySet()) {
			for (Map.Entry<Integer, ByteItem> v : m.getValue().entrySet()) {
				ByteItem byteItem = v.getValue();
				byteItem.getBitItems().stream().forEach(b -> {
					fields.add(b.getField());
				});
			}
		}

		return fields;
	}

	private static String getFieldVal(final int byteIdx, final int bitIdx) {
		// FIXME - uncomment if need
		String prefix = Const.EMPTY;// getPrefixVal(byteIdx);

		// with index [1..8]
		StringBuilder fieldVal = new StringBuilder(prefix);
		switch (bitIdx) {
		case 1:
			fieldVal = fieldVal.append(EBIT.BIT_1.getVal());
			break;
		case 2:
			fieldVal = fieldVal.append(EBIT.BIT_2.getVal());
			break;
		case 3:
			fieldVal = fieldVal.append(EBIT.BIT_3.getVal());
			break;
		case 4:
			fieldVal = fieldVal.append(EBIT.BIT_4.getVal());
			break;
		case 5:
			fieldVal = fieldVal.append(EBIT.BIT_5.getVal());
			break;
		case 6:
			fieldVal = fieldVal.append(EBIT.BIT_6.getVal());
			break;
		case 7:
			fieldVal = fieldVal.append(EBIT.BIT_7.getVal());
			break;
		case 8:
			fieldVal = fieldVal.append(EBIT.BIT_8.getVal());
			break;
		default:
			break;
		}

		return fieldVal.toString();
	}

	@SuppressWarnings("unused")
	private static String getPrefixVal(final int byteIdx) {
		String prefix = EMPTY;

		// with index [1..12]
		switch (byteIdx) {
		case 1:
			prefix = EBYTE.BYTE_1.getVal();
			break;
		case 2:
			prefix = EBYTE.BYTE_2.getVal();
			break;
		case 3:
			prefix = EBYTE.BYTE_3.getVal();
			break;
		case 4:
			prefix = EBYTE.BYTE_4.getVal();
			break;
		case 5:
			prefix = EBYTE.BYTE_5.getVal();
			break;
		case 6:
			prefix = EBYTE.BYTE_6.getVal();
			break;
		case 7:
			prefix = EBYTE.BYTE_7.getVal();
			break;
		case 8:
			prefix = EBYTE.BYTE_8.getVal();
			break;
		case 9:
			prefix = EBYTE.BYTE_9.getVal();
			break;
		case 10:
			prefix = EBYTE.BYTE_10.getVal();
			break;
		case 11:
			prefix = EBYTE.BYTE_11.getVal();
			break;
		case 12:
			prefix = EBYTE.BYTE_12.getVal();
			break;
		default: // never occur this case
			break;
		}

		return prefix;
	}

	/**
	 * Method use to get result after filter field.
	 * 
	 * @param bitMapped
	 * @param fields
	 * @return
	 */
	public static List<Result> getResults(final BitMapped bitMapped, final Integer... fields) {
		List<Result> results = Lists.newArrayList();
		List<Integer> fieldList = breakFieldSet(bitMapped, fields);
		if (fieldList != null && !fieldList.isEmpty()) {
			fieldList.stream().forEach(field -> {
				Result rs = lookup(bitMapped, field);
				if (rs != null) results.add(rs);
			});
		}

		return results;
	}

	///////////////////// ---> START INIT
	/**
	 * Method use to initialize data.
	 */
	public static void init() {
		// 0. init map
		map = Maps.newHashMap();

		// 1. build first bit-mapped (includes 12 bytes)
		buildFirstBitMapped(Dummy.EBYTE.values().length);

		// 2. build first bit-mapped (includes 5 bytes)
		buildSecondBitMapped(5);

		// 3. test set bit-mapped
		printBitMapped();
	}
	///////////////////// ---> END INIT

	/**
	 * Method return Byte Index, Field Index, Field Value
	 * 
	 * @param bitMap
	 * @param field
	 */
	private static Result lookup(final BitMapped bitMap, final int field) {
		Result rs = null;
		Map<BitMapped, Map<Integer, ByteItem>> m = getCurrentBitMapped(bitMap);

		// loop all byte in bit-mapped
		for (Map.Entry<Integer, ByteItem> entry : m.get(bitMap).entrySet()) {
			ByteItem byteItem = entry.getValue();

			// loop all bits in current byte and check field contains in current byte
			BitItem bitItem = byteItem.getBitItems().stream().filter(b -> b.getField().equals(field)).findFirst()
					.orElse(null); // because each byte at one time only have no duplicate field so then using
									// findFirst
			if (bitItem != null) {
				// Bit found is already set ON
				bitItem.setStatus(Status.ON);

				// Make result
				rs = new Result(bitMap, byteItem.getIndex(), bitItem.getField(), bitItem.getVal());
			}
		}

		return rs;
	}

	/**
	 * Method use to get list byteitem to view bytevalue
	 * 
	 * @param bitMap
	 * @param fields
	 * @return
	 */
	public static List<ByteItem> lookupByte(final BitMapped bitMap, final Integer... fields) {
		List<ByteItem> byteItems = Lists.newArrayList();
		Map<BitMapped, Map<Integer, ByteItem>> m = getCurrentBitMapped(bitMap);

		// loop all byte in bit-mapped
		for (Map.Entry<Integer, ByteItem> entry : m.get(bitMap).entrySet()) {
			ByteItem byteItem = entry.getValue();

			Arrays.stream(fields).forEach(f -> {
				// loop all bits in current byte and check field contains in current byte
				BitItem bitItem = byteItem.getBitItems().stream().filter(b -> b.getField().equals(f)).findFirst()
						.orElse(null); // because each byte at one time only have no duplicate field so then using
										// findFirst
				if (bitItem != null) {
					// Bit found is already set ON
					bitItem.setStatus(Status.ON);

					// Make result
					byteItems.add(byteItem);
				}
			});
		}

		return byteItems;
	}

	/**
	 * Method use to test set of bit-mapped
	 */
	private static void printBitMapped() {
		for (Map.Entry<BitMapped, Map<Integer, ByteItem>> entry : map.entrySet()) {
			System.out.println("*** " + entry.getKey() + " ***");
			for (Map.Entry<Integer, ByteItem> m : entry.getValue().entrySet()) {
				ByteItem by = m.getValue();
				List<BitItem> bi = by.getBitItems();

				bi.stream().forEach(b -> {
					System.out.println(String.format("Byte = [%d], Field = [%d], Bit Val. = [%s]", by.getIndex(),
							b.getField(), b.getVal()));
				});
			}
			System.out.println();
		}
	}

	/**
	 * Method use to show result out put to console.
	 * 
	 * @param bitMapped
	 * @param results
	 */
	public static void printResult(final BitMapped bitMapped, final List<Result> results) {
		System.out.println("\n*** Lookup in " + bitMapped + " ***");

		// Sorted result
		Collections.sort(results);

		// print out to console
		results.stream().forEach(rs -> {
			System.out.println(String.format("Byte [%d], Field [%d], Bit Val. [%s]", rs.getByteNo(), rs.getField(),
					rs.getBitVal()));
		});
	}

	@SuppressWarnings("unused")
	private static List<Integer> retainAll(List<Integer> root, List<Integer> target) {
		root.retainAll(target);

		return root;
	}

	/* use for testing only */
	public static void viewBreakField(final Integer... fields) {
		// [First Set of Bit-Mapped] From [1] to [96]
		List<Integer> fSetLookup = breakFieldSet(BitMapped.FIRST_SET, fields);
		String joinFields = fSetLookup.stream().map(i -> i.toString()).collect(Collectors.joining(COMMA));
		// fSetLookup.forEach(System.out::println);
		System.out.println("\nFirst Set of Fields-Present Bytes: " + joinFields);

		System.out.println("***");
		// [Second Set of Bit-Mapped] From [97] to [131]
		List<Integer> sSetLookup = breakFieldSet(BitMapped.SECOND_SET, fields);
		joinFields = sSetLookup.stream().map(i -> i.toString()).collect(Collectors.joining(COMMA));
		// sSetLookup.forEach(System.out::println);
		System.out.println("Second Set of Fields-Present Bytes: " + joinFields);
	}

	/* use for testing only */
	public static void viewRangeBitMapped() {
		List<Integer> fieldsInFirst = getFields(BitMapped.FIRST_SET);
		System.out.println(String.format("[%s] From [%d] to [%d]", BitMapped.FIRST_SET.toString(), fieldsInFirst.get(0),
				fieldsInFirst.get(fieldsInFirst.size() - 1)));

		List<Integer> fieldsInSecond = getFields(BitMapped.SECOND_SET);
		System.out.println(String.format("[%s] From [%d] to [%d]", BitMapped.SECOND_SET.toString(),
				fieldsInSecond.get(0), fieldsInSecond.get(fieldsInSecond.size() - 1)));
	}

}
