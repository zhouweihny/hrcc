package com.commons.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;

public class IKUtil {

	public static Map<String, Integer> segString(String content,Boolean useSmart) {
		// 分词
		Reader input = new StringReader(content);
		// 智能分词关闭（对分词的精度影响很大）
		IKSegmenter iks = new IKSegmenter(input, useSmart);
		Lexeme lexeme = null;
		Map<String, Integer> words = new HashMap<String, Integer>();
		try {
			while ((lexeme = iks.next()) != null) {
				if (words.containsKey(lexeme.getLexemeText())) {
					words.put(lexeme.getLexemeText(), words.get(lexeme.getLexemeText()) + 1);
				} 
				else {
					words.put(lexeme.getLexemeText(), 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}

	public static void main(String[] args) {
//		Configuration cfg = DefaultConfig.getInstance();
		Configuration cfg = new MyConfiguration(); // 加载词库
		cfg.setUseSmart(false); // 设置智能分词
		Dictionary.initial(cfg);
		Dictionary dictionary = Dictionary.getSingleton();

		List<String> words = new ArrayList<String>();
		words.add("丙泊酚");
		words.add("注射液");
		words.add("丙泊酚注射液");
		dictionary.addWords(words); // 自动添加自定义词
//
//		System.out.println(cfg.getMainDictionary()); // 系统默认词库
//		System.out.println(cfg.getQuantifierDicionary());

		// TODO Auto-generated method stub
		Map<String, Integer> cmap = segString("丙泊酚注射液",false);

		for (Entry<String, Integer> entry : cmap.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
//			System.out.println(key + " : " + value);
		}
	}
}
