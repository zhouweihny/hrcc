package com.commons.sroure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Du.Jun
 *
 */
public class FreeMarkerDirectiveUtil {

	public static void modifiedSpringXml(String springXmlPath, String directiveName, String directiveClassName) {
		String key = "_" + directiveName.toLowerCase();
		
		StringBuilder sb = new StringBuilder(directiveClassName);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		SAXReader sr = new SAXReader();
		try {
			Document doc;
			try {
				doc = sr.read(new BufferedReader(new InputStreamReader(new FileInputStream(new File(springXmlPath)), "UTF-8")));
				Element root = doc.getRootElement();
				List<Element> beans = root.elements();
				for (Element bean : beans) {
					if ("freemarkerConfigurer".equals(bean.attributeValue("id"))) {
						List<Element> properties = bean.elements();
						for (Element property : properties) {
							if ("freemarkerVariables".equals(property.attributeValue("name"))) {
								Element map = (Element) property.elements().get(0);
								List<Element> entries = map.elements();
								Boolean flag = true;
								for (Element entry : entries) {
									if (entry.attributeValue("key").equals(key)) {
										entry.attributeValue("value-ref", sb.toString());
										flag = false;
									}
								}
								if (flag) {
									Element el = map.addElement("entry");
									el.addAttribute("key", key);
									el.addAttribute("value-ref", sb.toString());
								}
							}
						}
						break;
					}
				}
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				XMLWriter writer = new XMLWriter(new FileWriter(springXmlPath), format);
				writer.write(doc);
				writer.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
