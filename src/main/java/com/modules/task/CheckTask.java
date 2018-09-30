package com.modules.task;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.KeyWordsUtil;
import com.modules.pojo.Drugikwords;
import com.modules.service.DrugikwordsService;

public class CheckTask {
	private static Logger logger = Logger.getLogger(CheckTask.class);

	@Autowired
	private DrugikwordsService drugikwordsService;

	public void checkKeyWords() {

		try {
			drugikwordsService.batchInsertFromDrug();
//			drugikwordsService.updateDelwords();
		} catch (Exception e) {
			logger.error(e);
		}

		HashMap<String, Object> params = new HashMap<String, Object>();
		StringBuffer words = new StringBuffer();
		List<String> keywords;
		PageResult<Drugikwords> data = null;
		int num = 0;
		int i = 1;
		while (true) {
			try {
				data = drugikwordsService.findList(params, new Page(i, 2000, false));
				if (data.getData().size() == 0)
					break;

				i++;

				for (Drugikwords d : data.getData()) {
					words.delete(0, words.length());
					num = 0;
					keywords = KeyWordsUtil.segDrugname(d.getName(), false);
					for (String word : keywords) {
						words.append(word).append("/");
						num++;
					}

					if (!StringUtils.isEmpty(words.toString())) {
						d.setWords(words.deleteCharAt(words.length() - 1).toString());
					}
					d.setNum(num);
					drugikwordsService.update(d);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

	}

}
