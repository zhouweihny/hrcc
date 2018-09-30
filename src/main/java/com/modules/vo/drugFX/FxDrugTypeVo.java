package com.modules.vo.drugFX;

import java.util.List;

public class FxDrugTypeVo implements Comparable<FxDrugTypeVo>{

	private static final long serialVersionUID = -3245478690496182643L;
	
	private String treeid;
	private  String   treename1; 	
	private  String   treename2;  	
	private  String   treename3;  	
	private  String   treename4; 	
	private String meatadataName;
	private String factvalue;
	private String typevalue;
	private String messagers;
	private List<FxDrugTypeItemVo> items;
	private String metaid;
	
	
	public String getTreeid() {
		return treeid;
	}
	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}
	public String getMetaid() {
		return metaid;
	}
	public void setMetaid(String metaid) {
		this.metaid = metaid;
	}
	public String getTreename1() {
		return treename1;
	}
	public void setTreename1(String treename1) {
		this.treename1 = treename1;
	}
	public String getTreename2() {
		return treename2;
	}
	public void setTreename2(String treename2) {
		this.treename2 = treename2;
	}
	public String getTreename3() {
		return treename3;
	}
	public void setTreename3(String treename3) {
		this.treename3 = treename3;
	}
	public String getTreename4() {
		return treename4;
	}
	public void setTreename4(String treename4) {
		this.treename4 = treename4;
	}
	public String getMeatadataName() {
		return meatadataName;
	}
	public void setMeatadataName(String meatadataName) {
		this.meatadataName = meatadataName;
	}
	public String getFactvalue() {
		return factvalue;
	}
	public void setFactvalue(String factvalue) {
		this.factvalue = factvalue;
	}
	public String getTypevalue() {
		return typevalue;
	}
	public void setTypevalue(String typevalue) {
		this.typevalue = typevalue;
	}
	public String getMessagers() {
		return messagers;
	}
	public void setMessagers(String messagers) {
		this.messagers = messagers;
	}
	
	
	public List<FxDrugTypeItemVo> getItems() {
		return items;
	}
	public void setItems(List<FxDrugTypeItemVo> items) {
		this.items = items;
	}
	@Override
	public int compareTo(FxDrugTypeVo o) {
		// TODO Auto-generated method stub
		String str1=this.treename1+this.treename2+this.treename3;
		String str2=o.getTreename1()+o.getTreename2()+o.getTreename3();
		if(str1.compareTo(str2)>0){
            return 1;
        }else if(str1.compareTo(str2)<0){
            return -1;
        }else{
            return 0;
        }
	}
	
	
	
}
