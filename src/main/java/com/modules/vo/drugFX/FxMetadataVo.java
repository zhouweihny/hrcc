package com.modules.vo.drugFX;

import java.util.List;

import com.modules.vo.apipur.PurchaseDetailVo;

public class FxMetadataVo {

	
	  @com.commons.annotation.Column(value="id",isId=true, generateId = true)
		private  String   id; //id   
		private  String   treeid; //treeid   
		private  Double   avg; //avg   
		private  Integer   drugnum; //drugnum   
		private  String   classname; //classname  	
		private  String   methodname; //methodname   		
	//	private  String   operatorid; //operatorid   	
		private  String   name; //name   		
		private  String   userid; //userid   
		private  String   explain; //explain   
		
		private List<FxMetadataitemVo> items;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTreeid() {
			return treeid;
		}

		public void setTreeid(String treeid) {
			this.treeid = treeid;
		}

		public Double getAvg() {
			return avg;
		}

		public void setAvg(Double avg) {
			this.avg = avg;
		}

		public Integer getDrugnum() {
			return drugnum;
		}

		public void setDrugnum(Integer drugnum) {
			this.drugnum = drugnum;
		}

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}

		public String getMethodname() {
			return methodname;
		}

		public void setMethodname(String methodname) {
			this.methodname = methodname;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getExplain() {
			return explain;
		}

		public void setExplain(String explain) {
			this.explain = explain;
		}

		public List<FxMetadataitemVo> getItems() {
			return items;
		}

		public void setItems(List<FxMetadataitemVo> items) {
			this.items = items;
		}
		
		
		
}
