/**
 * @Title: Menu.java
 * @Package com.util.weixin.menu
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年10月4日 上午10:21:13
 * @version V1.0
 */

package com.commons.util.wx.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Du.Jun
 *
 */
public class Menu {

	/**
	 * 
	 */
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param button
	 */
	public Menu(List<Button> button) {
		super();
		this.button = button;
	}

	List<Button> button;

	/**
	 * @return the button
	 */
	public List<Button> getButton() {
		return button;
	}

	/**
	 * @param button
	 *            the button to set
	 */
	public void setButton(List<Button> button) {
		this.button = button;
	}

	public void addButton(Button b) {
		if (this.button == null) {
			this.button = new ArrayList<Button>();
		}
		this.button.add(b);
	}

	public class Button {
		private List<SubButton> sub_button;
		private String type;
		private String name;
		private String key;
		private String url;
		private String media_id;

		public Button() {

		}

		/**
		 * @param sub_button
		 * @param type
		 * @param name
		 * @param key
		 * @param url
		 * @param media_id
		 */
		public Button(String type, String name, String key, String url, String media_id, List<SubButton> sub_button) {
			super();
			this.sub_button = sub_button;
			this.type = type;
			this.name = name;
			this.key = key;
			this.url = url;
			this.media_id = media_id;
		}

		/**
		 * @return the sub_button
		 */
		public List<SubButton> getSub_button() {
			return sub_button;
		}

		/**
		 * @param sub_button
		 *            the sub_button to set
		 */
		public void setSub_button(List<SubButton> sub_button) {
			this.sub_button = sub_button;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param key
		 *            the key to set
		 */
		public void setKey(String key) {
			this.key = key;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @param url
		 *            the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		/**
		 * @return the media_id
		 */
		public String getMedia_id() {
			return media_id;
		}

		/**
		 * @param media_id
		 *            the media_id to set
		 */
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

		public void addSubButton(SubButton b) {
			if (this.sub_button== null) {
				this.sub_button = new ArrayList<SubButton>();
			}
			this.sub_button.add(b);
		}
	}

	public class SubButton {

		private String type;
		private String name;
		private String key;
		private String url;
		private String media_id;

		public SubButton() {

		}

		/**
		 * @param type
		 * @param name
		 * @param key
		 * @param url
		 * @param media_id
		 */
		public SubButton(String type, String name, String key, String url, String media_id) {
			super();
			this.type = type;
			this.name = name;
			this.key = key;
			this.url = url;
			this.media_id = media_id;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param key
		 *            the key to set
		 */
		public void setKey(String key) {
			this.key = key;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @param url
		 *            the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		/**
		 * @return the media_id
		 */
		public String getMedia_id() {
			return media_id;
		}

		/**
		 * @param media_id
		 *            the media_id to set
		 */
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

	}

}
