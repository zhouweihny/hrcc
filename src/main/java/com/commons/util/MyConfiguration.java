package com.commons.util;

import java.util.List;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;

public class MyConfiguration implements Configuration {
 

	@Override
	public List<String> getExtDictionarys() {
		// TODO Auto-generated method stub
		return DefaultConfig.getInstance().getExtDictionarys();
	}

	@Override
	public List<String> getExtStopWordDictionarys() {
		// TODO Auto-generated method stub
		return DefaultConfig.getInstance().getExtStopWordDictionarys();
	}

	@Override
	public String getMainDictionary() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getQuantifierDicionary() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setUseSmart(boolean arg0) {
		// TODO Auto-generated method stub
		DefaultConfig.getInstance().setUseSmart(arg0);
	}

	@Override
	public boolean useSmart() {
		// TODO Auto-generated method stub
		return DefaultConfig.getInstance().useSmart();
	}

}
