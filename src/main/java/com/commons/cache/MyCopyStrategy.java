package com.commons.cache;

import java.io.Serializable;

import net.sf.ehcache.Element;
import net.sf.ehcache.store.compound.ReadWriteCopyStrategy;

/**
 * 序列化要缓存的对象，否则设置 copyOnRead="true" copyOnWrite="true" 缓存对象的时候可能报序列化错误
 *
 */
public class MyCopyStrategy implements ReadWriteCopyStrategy<Element> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 398424835714065859L;

	@Override
	public Element copyForWrite(Element value) {
		if (value != null) {
			// System.out.println("value==="+value);
			Object temp = (Serializable) value.getObjectValue();
			return new Element(value.getObjectKey(), temp);
		}
		return value;
	}

	@Override
	public Element copyForRead(Element storedValue) {
		if (storedValue != null) {
			// System.out.println("storedValue==="+storedValue);
			Object temp = (Serializable) storedValue.getObjectValue();
			return new Element(storedValue.getObjectKey(), temp);
		}
		return storedValue;
	}
}
