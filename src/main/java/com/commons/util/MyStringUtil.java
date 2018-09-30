package com.commons.util;

import java.util.ArrayList;
import java.util.List;

public class MyStringUtil {

	public static String[] lunits = { "µl/ul", "ml", "l" };
	public static String[] clunits = { "微升", "毫升", "升" };

	public static String[] gunits = { "μg/ug", "mg", "g", "kg" };
	public static String[] cgunits = { "微克", "毫克", "克", "千克" };

	public static List<String> findnums(String str) {
		List<String> nums = new ArrayList<String>();
		str = str.trim();
		if (str != null && !"".equals(str)) {
			int k = -2;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					if (k == i - 1) {
						String a = nums.get(nums.size() - 1) + str.charAt(i);
						nums.set(nums.size() - 1, a);
					} else {
						String a = "" + str.charAt(i);
						nums.add(a);
					}
					k = i;
				}
				if (String.valueOf(str.charAt(i)).equals(".")) {
					if (k == i - 1) {
						String a = nums.get(nums.size() - 1) + str.charAt(i);
						nums.set(nums.size() - 1, a);
						k = i;
					}
				}

			}
		}
		return nums;
	}

	public static List<String> findLunit(String str) {
		List<String> specifications = new ArrayList<String>();
		List<String> nums = findnums(str);
		for (String num : nums) {
			for (int i = 0; i < 3; i++) {
				for (String unit : lunits[i].split("/"))
					if (str.toLowerCase().indexOf(num + unit) > -1) {
						if (str.indexOf(num + unit) > 2 && String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(".")
								&& (str.charAt(str.indexOf(num + unit) - 2) <= 57 || str.charAt(str.indexOf(num + unit) - 2) >= 47))
							continue;
						if (str.indexOf(num + unit) > 1 && (String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("<") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(">")
								|| String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≥") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≤")))
							continue;
						for (int j = 0; j < 3; j++) {
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + lunits[j].split("/")[0]);
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + clunits[j].split("/")[0]);
						}
					}
				for (String unit : clunits[i].split("/"))
					if (str.toLowerCase().indexOf(num + unit) > -1) {
						if (str.indexOf(num + unit) > 2 && String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(".")
								&& (str.charAt(str.indexOf(num + unit) - 2) <= 57 || str.charAt(str.indexOf(num + unit) - 2) >= 47))
							continue;
						if (str.indexOf(num + unit) > 1 && (String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("<") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(">")
								|| String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≥") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≤")))
							continue;
						for (int j = 0; j < 3; j++) {
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + lunits[j].split("/")[0]);
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + clunits[j].split("/")[0]);
						}
					}
			}
		}
		return specifications;
	}

	public static List<String> findGunit(String str) {
		List<String> specifications = new ArrayList<String>();
		List<String> nums = findnums(str);
		for (String num : nums) {
			for (int i = 0; i < 3; i++) {
				for (String unit : gunits[i].split("/")) {
					if (str.toLowerCase().indexOf(num + unit) > -1) {
						if (str.indexOf(num + unit) > 2 && String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(".")
								&& (str.charAt(str.indexOf(num + unit) - 2) <= 57 || str.charAt(str.indexOf(num + unit) - 2) >= 47))
							continue;
						if (str.indexOf(num + unit) > 1 && (String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("<") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(">")
								|| String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≥") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≤")))
							continue;
						for (int j = 0; j < 4; j++) {
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + gunits[j].split("/")[0]);
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + cgunits[j].split("/")[0]);
						}
					}
				}

				for (String unit : cgunits[i].split("/")) {
					if (str.toLowerCase().indexOf(num + unit) > -1) {
						if (str.indexOf(num + unit) > 2 && String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(".")
								&& (str.charAt(str.indexOf(num + unit) - 2) <= 57 || str.charAt(str.indexOf(num + unit) - 2) >= 47))
							continue;
						if (str.indexOf(num + unit) > 1 && (String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("<") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals(">")
								|| String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≥") || String.valueOf(str.charAt(str.indexOf(num + unit) - 1)).equals("≤")))
							continue;
						for (int j = 0; j < 4; j++) {
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + gunits[j].split("/")[0]);
							specifications.add((Double.parseDouble(num) * Math.pow(10, (i - j) * 3)) + cgunits[j].split("/")[0]);
						}
					}
				}
			}
		}
		return specifications;
	}

	public static String filterString(String str) {
		StringBuffer newstr = new StringBuffer();
		char[] chars = ToDBC(str).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90) || (chars[i] == 40 || chars[i] == 41)) {
				newstr.append(chars[i]);
			}
		}
		return newstr.toString();
	}

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}

	public static void main(String args[]) throws Exception {

		// for (String str : findnums(" 1支*0.1mg*200吸/120箱")) {
		// System.out.println(str);
		// }
		// str.charAt(i) >= 48 && str.charAt(i) <= 57
		// String str = " 1支*0.1mg*200吸/120箱";

		// if (str.charAt(str.indexOf("1mg") - 1) > 57 ||
		// str.charAt(str.indexOf("1mg") - 1) < 47)
		// System.out.println("true");
		// if (String.valueOf(str.charAt(str.indexOf("0.1mg") - 1)).equals(".")
		// && (str.charAt(str.indexOf("0.1mg") - 2) <= 57 &&
		// str.charAt(str.indexOf("0.1mg") - 2) >= 47)) {
		// System.out.println("true");
		// }
		// for (String str : findGunit("320μg:9μg*60吸")) {
		//
		// System.out.println(str);
		// }
//		System.out.println(filterString("（      )"));

	}
}
