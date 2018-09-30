/**
 * @Title: sdfadf.java
 * @Package com.util.weixin
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年9月30日 下午4:08:26
 * @version V1.0
 */

package com.commons.util.wx;

import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author Du.Jun
 *
 */
public class EventXmlUtil {
	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static <E> E parseXml(InputStream inputStream, Class<E> clazz) throws Exception {
		// 将解析结果存储在HashMap中
		E entity = clazz.newInstance();
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(inputStream);// 获得文档对象
		Element root = document.getRootElement();// 获得根节点
		@SuppressWarnings("unchecked")
		List<Element> list = root.getChildren();

		Field[] fields = clazz.getFields();
		for (Element e : list) {
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase(e.getName())) {
					field.setAccessible(true);
					field.set(entity, e.getText());
				}
			}
		}
		// 释放资源
		inputStream.close();
		return entity;
	}

	public static <E> E parseXml(String xml, Class<E> clazz) throws Exception {
		// 将解析结果存储在HashMap中
		E entity = clazz.newInstance();
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new StringReader(xml));
		Element root = document.getRootElement();// 获得根节点
		@SuppressWarnings("unchecked")
		List<Element> list = root.getChildren();

		Field[] fields = clazz.getFields();
		for (Element e : list) {
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase(e.getName())) {
					field.setAccessible(true);
					field.set(entity, e.getText());
				}
			}
		}
		return entity;
	}

}