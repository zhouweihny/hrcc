package com.commons.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KeyWordsUtil {

	public static List<String> namewords = new ArrayList<String>();

	public static List<String> dosageform = new ArrayList<String>();

	public static List<String> factoryKeywords = new ArrayList<String>();

	public static List<String> segDrugname(String name, boolean flag) {
		List<String> words = new ArrayList<String>();
		if (flag) {
			for (String keyword : namewords) {
				if (name.contains(keyword)) {
					int i = 0;
					Iterator<String> it = words.iterator();
					while (it.hasNext()) {
						String word = it.next();
						if (word.contains(keyword)) {
							i = 1;
							break;
						} else if (keyword.contains(word)) {
							it.remove();
						}
					}
					if (i != 1) {
						words.add(keyword);
					}
				}
			}
		} else {
			for (String keyword : namewords) {
				if (name.contains(keyword)) {
					words.add(keyword);
				}
			}
		}
		return words;
	}

	public static List<String> segDosageform(String name) {
		ArrayList<String> words = new ArrayList<String>();
		for (String keyword : dosageform) {
			if (name.contains(keyword))
				words.add(keyword);
		}
		return words;
	}

	public static List<String> segFactoryKeywords(String name, boolean flag) {
		List<String> words = new ArrayList<String>();
		if (flag) {
			for (String keyword : factoryKeywords) {
				String[] keywords = keyword.split("%");
				boolean flag2 = true;
				for (String k : keywords) {
					if (!name.contains(k))
						flag2 = false;
				}

				if (flag2) {
					int i = 0;
					Iterator<String> it = words.iterator();
					while (it.hasNext()) {
						String word = it.next();
						if (word.contains(keyword)) {
							i = 1;
							break;
						} else if (keyword.contains(word)) {
							it.remove();
						}
					}
					if (i != 1) {
						words.add(keyword);
					}
				}
			}
		} else {
			for (String keyword : factoryKeywords) {
				String[] keywords = keyword.split("%");
				boolean flag2 = true;
				for (String k : keywords) {
					if (!name.contains(k))
						flag2 = false;
				}

				if (flag2) {
					words.add(keyword);
				}
			}
		}
		return words;
	}

	public static void main(String args[]) throws Exception {
		namewords.add("盐酸葡萄糖");
		namewords.add("葡萄糖");
		namewords.add("氯化钠");
		namewords.add("葡萄糖氯化钠");
		namewords.add("注射剂");

		for (String str : "adasd%ass".split("%")) {
			System.out.println(str);
		}
	}
}
